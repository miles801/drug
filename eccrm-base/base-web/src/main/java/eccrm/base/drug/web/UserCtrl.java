package eccrm.base.drug.web;

import com.ycrl.core.web.BaseController;
import com.ycrl.base.common.JspAccessType;
import com.ycrl.core.pager.PageVo;
import com.ycrl.utils.gson.GsonUtils;
import eccrm.base.drug.bo.UserBo;
import eccrm.base.drug.domain.User;
import eccrm.base.drug.service.UserService;
import eccrm.base.drug.vo.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    @RequestMapping(value = "/modify", params = {"id"}, method = RequestMethod.GET)
    public String toModify(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.MODIFY);
        request.setAttribute("id", id);
        return "drug/user/edit/user_edit";
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
    @RequestMapping(value = "/delete", params = {"ids"}, method = RequestMethod.DELETE)
    public void deleteByIds(@RequestParam String ids, HttpServletResponse response) {
        String[] idArr = ids.split(",");
        userService.deleteByIds(idArr);
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



}
