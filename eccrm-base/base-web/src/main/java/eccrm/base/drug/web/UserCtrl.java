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
import eccrm.base.drug.bo.UserBo;
import eccrm.base.drug.domain.User;
import eccrm.base.drug.service.UserService;
import eccrm.base.drug.vo.UserVo;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

/**
 * @author Rechried
 */
@Controller
@RequestMapping(value = {"/base/user"})
public class UserCtrl extends BaseController {
    @Resource
    private UserService userService;
    @RequestMapping(value = {""}, method=RequestMethod.GET )
    public String toList() {
        return "drug/user/list/user_list";
    }

    @RequestMapping(value = "/allDrug", method=RequestMethod.GET )
    public String allDrug() {
        return "drug/allDrug/list/allDrug_list";
    }

    /**
     * 打印
     * @return
     */
    @RequestMapping(value = "/allDrug/print", method=RequestMethod.GET )
    public String print() {
        return "drug/allDrug/list/print";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.ADD);
        return "drug/user/edit/user_edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public void save(HttpServletRequest request, HttpServletResponse response) {
        User user = GsonUtils.wrapDataToEntity(request, User.class);
        userService.save(user);
        GsonUtils.printSuccess(response);
    }

    /**
     * 设置关系
     * @param request
     * @param response
     */
    @RequestMapping(value = "/saveRelation", method = RequestMethod.POST)
    @ResponseBody
    public void saveRelation(HttpServletRequest request, HttpServletResponse response) {
        User user = GsonUtils.wrapDataToEntity(request, User.class);
        userService.saveRelation(user);
        GsonUtils.printSuccess(response);
    }
    @RequestMapping(value = "/modify", params = {"id"}, method = RequestMethod.GET)
    public String toModify(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.MODIFY);
        request.setAttribute("id", id);
        return "drug/user/edit/user_edit";
    }
    @RequestMapping(value = "/setRelation", params = {"id"}, method = RequestMethod.GET)
    public String setRelation(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute("id", id);
        return "drug/relation/list/relation_list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public void update(HttpServletRequest request, HttpServletResponse response) {
        User user = GsonUtils.wrapDataToEntity(request, User.class);
        userService.update(user);
        GsonUtils.printSuccess(response);
    }

    @RequestMapping(value = {"/detail"}, params = {"id"}, method = RequestMethod.GET)
    public String toDetail(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.DETAIL);
        request.setAttribute("id", id);
        return "drug/user/edit/user_edit";
    }

    @ResponseBody
    @RequestMapping(value = "/get", params = {"id"}, method = RequestMethod.GET)
    public void findById(@RequestParam String id, HttpServletResponse response) {
        UserVo vo = userService.findById(id);
        GsonUtils.printData(response, vo);
    }

    @ResponseBody
    @RequestMapping(value = "/pageQuery", method = RequestMethod.POST)
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) {
        UserBo bo = GsonUtils.wrapDataToEntity(request, UserBo.class);
        PageVo pageVo = userService.pageQuery(bo);
        GsonUtils.printData(response, pageVo);
    }
    @ResponseBody
    @RequestMapping(value = "/pageQueryParent", method = RequestMethod.POST)
    public void pageQueryParent(HttpServletRequest request, HttpServletResponse response) {
        UserBo bo = GsonUtils.wrapDataToEntity(request, UserBo.class);
        PageVo pageVo = userService.pageQueryParent(bo);
        GsonUtils.printData(response, pageVo);
    }

    /**
     * 汇总数据
     * @param request
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "/allDrug/pageQuery", method = RequestMethod.POST)
    public void allPageQuery(HttpServletRequest request, HttpServletResponse response) {
        UserBo bo = GsonUtils.wrapDataToEntity(request, UserBo.class);
        PageVo pageVo = userService.allPageQuery(bo);
        GsonUtils.printData(response, pageVo);
    }

    @ResponseBody
    @RequestMapping(value = "/pageQueryRelation", method = RequestMethod.POST)
    public void pageQueryRelation(HttpServletRequest request, HttpServletResponse response) {
        UserBo bo = GsonUtils.wrapDataToEntity(request, UserBo.class);
        PageVo pageVo = userService.pageQueryRelation(bo);
        GsonUtils.printData(response, pageVo);
    }

    @ResponseBody
    @RequestMapping(value = "/delete", params = {"ids"}, method = RequestMethod.DELETE)
    public void deleteByIds(@RequestParam String ids, HttpServletResponse response) {
        String[] idArr = ids.split(",");
        userService.deleteByIds(idArr);
        GsonUtils.printSuccess(response);
    }
    @ResponseBody
    @RequestMapping(value = "/resetRelation", params = {"ids"}, method = RequestMethod.GET)
    public void resetRelation(@RequestParam String ids, HttpServletResponse response) {
        userService.resetRelation(ids);
        GsonUtils.printSuccess(response);
    }

    /**
     * 新增记录
     * @param ids
     * @param flag
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "/addLog", params = {"ids","flag"}, method = RequestMethod.GET)
    public void addLog(@RequestParam String ids,@RequestParam String flag ,HttpServletResponse response) {
        String[] idArr = ids.split(",");
        userService.addLog(idArr,flag);
        GsonUtils.printSuccess(response);
    }

    /**
     * 导出外出务工表信息
     *
     * @param request
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "/exportUserExcel", method = RequestMethod.GET)
    public void exportLaborExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final UserBo bo = GsonUtils.wrapDataToEntity(request, UserBo.class);
        PageVo vo = userService.pageQuery(bo);
        BatchData batchData = new BatchData();
        DataInjector dataInjector = new DataInjector() {
            @Override
            public JsonObject fetch(int start, int limit) {
                Pager.setStart(start);
                Pager.setLimit(limit);
                PageVo vo = userService.pageQuery(bo);
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
            disposition = "attachment;filename=" + URLEncoder.encode("export_user_list.xlsx", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", disposition);
        exportEngine.export(response.getOutputStream(), this.getClass().getClassLoader().getResourceAsStream("export_user_list.xlsx"), batchData);
    }
    /**
     * 通过导入村民档案，保存
     *
     * @param ids
     * @param response
     * @author dufan
     */
    @RequestMapping(value = "saveUserFromExcel", params = {"ids"}, method = RequestMethod.POST)
    @ResponseBody
    public void saveUserFromExcel(@RequestParam String ids, HttpServletResponse response) {
        userService.saveUserFromExcel(ids);
        GsonUtils.printSuccess(response);
    }

    /**
     * 下载模板
     * @param request
     * @param response
     * @throws FileNotFoundException
     */
    @RequestMapping(value = "/exportModel", method = RequestMethod.GET)
    public void exportModel(HttpServletRequest request, final HttpServletResponse response) throws FileNotFoundException {
        String disposition = null;
        try {
            disposition = "attachment;filename=" + URLEncoder.encode("user.xlsx", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", disposition);
        try {
            IOUtils.copy(this.getClass().getClassLoader().getResourceAsStream("user.xlsx"), response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
