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
import eccrm.base.drug.bo.DrugBo;
import eccrm.base.drug.domain.Drug;
import eccrm.base.drug.domain.DrugHelp;
import eccrm.base.drug.service.DrugService;
import eccrm.base.drug.vo.DrugVo;
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
import java.util.List;

/**
 * @author Rechried
 */
@Controller
@RequestMapping(value = {"/base/drug"})
public class DrugCtrl extends BaseController {
    @Resource
    private DrugService drugService;
    @RequestMapping(value = {""}, method=RequestMethod.GET )
    public String toList(HttpServletRequest request) {
        HttpSession session=request.getSession();
        session.setAttribute("bo",null);
        return "drug/drug/list/drug_list";
    }

    @RequestMapping(value = "/print", method=RequestMethod.GET )
    public String print(HttpServletRequest request) {
        DrugBo bo = GsonUtils.wrapDataToEntity(request, DrugBo.class);
        HttpSession session=request.getSession();
        session.setAttribute("bo",bo);
        return "drug/drug/list/print";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.ADD);
        return "drug/drug/edit/drug_edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public void save(HttpServletRequest request, HttpServletResponse response) {
        Drug drug = GsonUtils.wrapDataToEntity(request, Drug.class);
        drugService.save(drug);
        GsonUtils.printSuccess(response);
    }
    @RequestMapping(value = "/addDrugHelp", method = RequestMethod.POST)
    @ResponseBody
    public void addDrugHelp(HttpServletRequest request, HttpServletResponse response) {
        DrugHelp drug = GsonUtils.wrapDataToEntity(request, DrugHelp.class);
        drugService.addDrugHelp(drug);
        GsonUtils.printSuccess(response);
    }
    @RequestMapping(value = "/modify", params = {"id"}, method = RequestMethod.GET)
    public String toModify(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.MODIFY);
        request.setAttribute("id", id);
        return "drug/drug/edit/drug_edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public void update(HttpServletRequest request, HttpServletResponse response) {
        DrugVo vo = GsonUtils.wrapDataToEntity(request, DrugVo.class);
        Drug drug=vo.getDrug();
        List<DrugHelp> list=vo.getDrugs();
        drugService.update(drug,list);
        GsonUtils.printSuccess(response);
    }

    @RequestMapping(value = {"/detail"}, params = {"id"}, method = RequestMethod.GET)
    public String toDetail(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.DETAIL);
        request.setAttribute("id", id);
        return "drug/drug/edit/drug_edit";
    }

    @ResponseBody
    @RequestMapping(value = "/get", params = {"id"}, method = RequestMethod.GET)
    public void findById(@RequestParam String id, HttpServletResponse response) {
        DrugVo vo = drugService.findById(id);
        GsonUtils.printData(response, vo);
    }

    @ResponseBody
    @RequestMapping(value = "/pageQuery", method = RequestMethod.POST)
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) {
        DrugBo bo = GsonUtils.wrapDataToEntity(request, DrugBo.class);
        HttpSession session=request.getSession();
        DrugBo b = (DrugBo) session.getAttribute("bo");
        if(b!=null){
            bo=b;
            Pager.setLimit(1000);
            session.setAttribute("bo",null);
        }
        PageVo pageVo = drugService.pageQuery(bo);
        GsonUtils.printData(response, pageVo);
    }

    @ResponseBody
    @RequestMapping(value = "/delete", params = {"ids"}, method = RequestMethod.DELETE)
    public void deleteByIds(@RequestParam String ids, HttpServletResponse response) {
        String[] idArr = ids.split(",");
        drugService.deleteByIds(idArr);
        GsonUtils.printSuccess(response);
    }

    /**
     * 导出外出务工表信息
     *
     * @param request
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "/exportDrugExcel", method = RequestMethod.GET)
    public void exportDrugExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final DrugBo bo = GsonUtils.wrapDataToEntity(request, DrugBo.class);
        PageVo vo = drugService.pageQuery(bo);
        BatchData batchData = new BatchData();
        DataInjector dataInjector = new DataInjector() {
            @Override
            public JsonObject fetch(int start, int limit) {
                Pager.setStart(start);
                Pager.setLimit(limit);
                PageVo vo = drugService.pageQuery(bo);
                Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateStringConverter("yyyy-MM-dd HH:mm:ss"))
                        .create();
                String json = gson.toJson(vo.getData());
                JsonElement element = gson.fromJson(json, JsonElement.class);
                JsonObject o = new JsonObject();
                o.add("c", element);
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
            disposition = "attachment;filename=" + URLEncoder.encode("export_drug_list.xlsx", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", disposition);
        exportEngine.export(response.getOutputStream(), this.getClass().getClassLoader().getResourceAsStream("export_drug_list.xlsx"), batchData);
    }

}
