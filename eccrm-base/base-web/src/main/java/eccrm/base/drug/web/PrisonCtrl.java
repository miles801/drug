package eccrm.base.drug.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.michael.poi.exp.BatchData;
import com.michael.poi.exp.DataInjector;
import com.michael.poi.exp.ExportEngine;
import com.ycrl.core.pager.Pager;
import com.ycrl.core.web.BaseController;
import com.ycrl.base.common.JspAccessType;
import com.ycrl.core.pager.PageVo;
import com.ycrl.utils.gson.DateStringConverter;
import com.ycrl.utils.gson.GsonUtils;
import eccrm.base.drug.bo.DopeBo;
import eccrm.base.drug.bo.PrisonBo;
import eccrm.base.drug.domain.Prison;
import eccrm.base.drug.service.PrisonService;
import eccrm.base.drug.vo.PrisonVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

/**
 * @author Rechried
 */
@Controller
@RequestMapping(value = {"/base/prison"})
public class PrisonCtrl extends BaseController {
    @Resource
    private PrisonService prisonService;
    @RequestMapping(value = {""}, method=RequestMethod.GET )
    public String toList() {
        return "drug/prison/list/prison_list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.ADD);
        return "drug/prison/edit/prison_edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public void save(HttpServletRequest request, HttpServletResponse response) {
        Prison prison = GsonUtils.wrapDataToEntity(request, Prison.class);
        prisonService.save(prison);
        GsonUtils.printSuccess(response);
    }
    @RequestMapping(value = "/modify", params = {"id"}, method = RequestMethod.GET)
    public String toModify(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.MODIFY);
        request.setAttribute("id", id);
        return "drug/prison/edit/prison_edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public void update(HttpServletRequest request, HttpServletResponse response) {
        Prison prison = GsonUtils.wrapDataToEntity(request, Prison.class);
        prisonService.update(prison);
        GsonUtils.printSuccess(response);
    }

    @RequestMapping(value = {"/detail"}, params = {"id"}, method = RequestMethod.GET)
    public String toDetail(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.DETAIL);
        request.setAttribute("id", id);
        return "drug/prison/edit/prison_edit";
    }

    @ResponseBody
    @RequestMapping(value = "/get", params = {"id"}, method = RequestMethod.GET)
    public void findById(@RequestParam String id, HttpServletResponse response) {
        PrisonVo vo = prisonService.findById(id);
        GsonUtils.printData(response, vo);
    }

    @ResponseBody
    @RequestMapping(value = "/pageQuery", method = RequestMethod.POST)
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) {
        PrisonBo bo = GsonUtils.wrapDataToEntity(request, PrisonBo.class);
        PageVo pageVo = prisonService.pageQuery(bo);
        GsonUtils.printData(response, pageVo);
    }

    @ResponseBody
    @RequestMapping(value = "/delete", params = {"ids"}, method = RequestMethod.DELETE)
    public void deleteByIds(@RequestParam String ids, HttpServletResponse response) {
        String[] idArr = ids.split(",");
        prisonService.deleteByIds(idArr);
        GsonUtils.printSuccess(response);
    }

    /**
     * 导出服刑人员表信息
     *
     * @param request
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "/exporPrisonExcel", method = RequestMethod.GET)
    public void exporPrisonExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final PrisonBo bo = GsonUtils.wrapDataToEntity(request, PrisonBo.class);
        PageVo vo = prisonService.pageQuery(bo);

        BatchData batchData = new BatchData();
        DataInjector dataInjector = new DataInjector() {
            @Override
            public JsonObject fetch(int start, int limit) {
                Pager.setStart(start);
                Pager.setLimit(limit);
                PageVo vo = prisonService.pageQuery(bo);
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
            disposition = "attachment;filename=" + URLEncoder.encode("export_prison_list.xlsx", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", disposition);
        exportEngine.export(response.getOutputStream(), this.getClass().getClassLoader().getResourceAsStream("export_prison_list.xlsx"), batchData);
    }
}
