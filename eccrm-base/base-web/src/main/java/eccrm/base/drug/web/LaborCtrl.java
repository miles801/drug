package eccrm.base.drug.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.michael.poi.exp.BatchData;
import com.michael.poi.exp.DataInjector;
import com.michael.poi.exp.ExportEngine;
import com.ycrl.base.common.JspAccessType;
import com.ycrl.core.pager.PageVo;
import com.ycrl.core.pager.Pager;
import com.ycrl.core.web.BaseController;
import com.ycrl.utils.gson.DateStringConverter;
import com.ycrl.utils.gson.GsonUtils;
import eccrm.base.drug.bo.LaborBo;
import eccrm.base.drug.domain.Labor;
import eccrm.base.drug.service.LaborService;
import eccrm.base.drug.vo.LaborVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

/**
 * @author Rechried
 */
@Controller
@RequestMapping(value = {"/base/labor"})
public class LaborCtrl extends BaseController {
    @Resource
    private LaborService laborService;
    @RequestMapping(value = {""}, method=RequestMethod.GET )
    public String toList(HttpServletRequest request) {
        HttpSession session=request.getSession();
        session.setAttribute("bo",null);
        return "drug/labor/list/labor_list";
    }

    @RequestMapping(value = "/print", method=RequestMethod.GET )
    public String print(HttpServletRequest request) {
        LaborBo bo = GsonUtils.wrapDataToEntity(request, LaborBo.class);
        HttpSession session=request.getSession();
        session.setAttribute("bo",bo);
        return "drug/labor/list/print";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.ADD);
        return "drug/labor/edit/labor_edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public void save(HttpServletRequest request, HttpServletResponse response) {
        Labor labor = GsonUtils.wrapDataToEntity(request, Labor.class);
        laborService.save(labor);
        GsonUtils.printSuccess(response);
    }
    @RequestMapping(value = "/modify", params = {"id"}, method = RequestMethod.GET)
    public String toModify(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.MODIFY);
        request.setAttribute("id", id);
        return "drug/labor/edit/labor_edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public void update(HttpServletRequest request, HttpServletResponse response) {
        Labor labor = GsonUtils.wrapDataToEntity(request, Labor.class);
        laborService.update(labor);
        GsonUtils.printSuccess(response);
    }

    @RequestMapping(value = {"/detail"}, params = {"id"}, method = RequestMethod.GET)
    public String toDetail(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.DETAIL);
        request.setAttribute("id", id);
        return "drug/labor/edit/labor_edit";
    }

    @ResponseBody
    @RequestMapping(value = "/get", params = {"id"}, method = RequestMethod.GET)
    public void findById(@RequestParam String id, HttpServletResponse response) {
        LaborVo vo = laborService.findById(id);
        GsonUtils.printData(response, vo);
    }

    @ResponseBody
    @RequestMapping(value = "/pageQuery", method = RequestMethod.POST)
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) {
        LaborBo bo = GsonUtils.wrapDataToEntity(request, LaborBo.class);
        HttpSession session=request.getSession();
        LaborBo b = (LaborBo) session.getAttribute("bo");
        if(b!=null){
            bo=b;
            Pager.setLimit(1000);
        }
        PageVo pageVo = laborService.pageQuery(bo);
        GsonUtils.printData(response, pageVo);
    }

    @ResponseBody
    @RequestMapping(value = "/delete", params = {"ids"}, method = RequestMethod.DELETE)
    public void deleteByIds(@RequestParam String ids, HttpServletResponse response) {
        String[] idArr = ids.split(",");
        laborService.deleteByIds(idArr);
        GsonUtils.printSuccess(response);
    }
    /**
     * 导出外出务工表信息
     *
     * @param request
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "/exportLaborExcel", method = RequestMethod.GET)
    public void exportLaborExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final LaborBo bo = GsonUtils.wrapDataToEntity(request, LaborBo.class);
        PageVo vo = laborService.pageQuery(bo);
        BatchData batchData = new BatchData();
        DataInjector dataInjector = new DataInjector() {
            @Override
            public JsonObject fetch(int start, int limit) {
                Pager.setStart(start);
                Pager.setLimit(limit);
                PageVo vo = laborService.pageQuery(bo);
                Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateStringConverter("yyyy-MM-dd HH:mm:ss"))
                        .create();
                String json = gson.toJson(vo.getData());
                JsonElement element = gson.fromJson(json, JsonElement.class);
                JsonObject o = new JsonObject();
                o.add("u", element);
                return o;
            }
        };
        batchData.setStart(0);
        batchData.setTotal(Integer.parseInt(vo.getTotal() + ""));
        batchData.setBatch(true);
        batchData.setLimit(50);
        batchData.setDataInjector(dataInjector);

        ExportEngine exportEngine = new ExportEngine();
        String disposition = null;
        try {
            disposition = "attachment;filename=" + URLEncoder.encode("export_labor_list.xlsx", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", disposition);
        exportEngine.export(response.getOutputStream(), this.getClass().getClassLoader().getResourceAsStream("export_labor_list.xlsx"), batchData);
    }

    }
