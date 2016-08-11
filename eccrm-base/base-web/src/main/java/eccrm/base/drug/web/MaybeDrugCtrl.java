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
import eccrm.base.drug.bo.LaborBo;
import eccrm.base.drug.bo.MaybeDrugBo;
import eccrm.base.drug.domain.MaybeDrug;
import eccrm.base.drug.service.MaybeDrugService;
import eccrm.base.drug.vo.MaybeDrugVo;
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
@RequestMapping(value = {"/base/maybeDrug"})
public class MaybeDrugCtrl extends BaseController {
    @Resource
    private MaybeDrugService maybeDrugService;
    @RequestMapping(value = {""}, method=RequestMethod.GET )
    public String toList() {
        return "drug/maybeDrug/list/maybeDrug_list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.ADD);
        return "drug/maybeDrug/edit/maybeDrug_edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public void save(HttpServletRequest request, HttpServletResponse response) {
        MaybeDrug maybeDrug = GsonUtils.wrapDataToEntity(request, MaybeDrug.class);
        maybeDrugService.save(maybeDrug);
        GsonUtils.printSuccess(response);
    }
    @RequestMapping(value = "/modify", params = {"id"}, method = RequestMethod.GET)
    public String toModify(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.MODIFY);
        request.setAttribute("id", id);
        return "drug/maybeDrug/edit/maybeDrug_edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public void update(HttpServletRequest request, HttpServletResponse response) {
        MaybeDrug maybeDrug = GsonUtils.wrapDataToEntity(request, MaybeDrug.class);
        maybeDrugService.update(maybeDrug);
        GsonUtils.printSuccess(response);
    }

    @RequestMapping(value = {"/detail"}, params = {"id"}, method = RequestMethod.GET)
    public String toDetail(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.DETAIL);
        request.setAttribute("id", id);
        return "drug/maybeDrug/edit/maybeDrug_edit";
    }

    @ResponseBody
    @RequestMapping(value = "/get", params = {"id"}, method = RequestMethod.GET)
    public void findById(@RequestParam String id, HttpServletResponse response) {
        MaybeDrugVo vo = maybeDrugService.findById(id);
        GsonUtils.printData(response, vo);
    }

    @ResponseBody
    @RequestMapping(value = "/pageQuery", method = RequestMethod.POST)
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) {
        MaybeDrugBo bo = GsonUtils.wrapDataToEntity(request, MaybeDrugBo.class);
        PageVo pageVo = maybeDrugService.pageQuery(bo);
        GsonUtils.printData(response, pageVo);
    }

    @ResponseBody
    @RequestMapping(value = "/delete", params = {"ids"}, method = RequestMethod.DELETE)
    public void deleteByIds(@RequestParam String ids, HttpServletResponse response) {
        String[] idArr = ids.split(",");
        maybeDrugService.deleteByIds(idArr);
        GsonUtils.printSuccess(response);
    }
    /**
     * 导出吸毒可疑人员表信息
     *
     * @param request
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "/exporMaybeDrugExcel", method = RequestMethod.GET)
    public void exporMaybeDrugExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
       final  MaybeDrugBo bo = GsonUtils.wrapDataToEntity(request, MaybeDrugBo.class);
        PageVo vo = maybeDrugService.pageQuery(bo);
        BatchData batchData = new BatchData();
        DataInjector dataInjector = new DataInjector() {
            @Override
            public JsonObject fetch(int start, int limit) {
                Pager.setStart(start);
                Pager.setLimit(limit);
                PageVo vo = maybeDrugService.pageQuery(bo);
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
            disposition = "attachment;filename=" + URLEncoder.encode("export_maybe_drug_list.xlsx", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", disposition);
        exportEngine.export(response.getOutputStream(), this.getClass().getClassLoader().getResourceAsStream("export_maybe_drug_list.xlsx"), batchData);
    }

}
