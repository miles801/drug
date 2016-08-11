package eccrm.base.drug.service.impl;

import com.michael.base.common.BaseParameter;
import com.ycrl.core.pager.PageVo;
import com.ycrl.base.common.CommonStatus;
import eccrm.base.drug.bo.UserBo;
import eccrm.base.drug.dao.*;
import eccrm.base.drug.domain.*;
import eccrm.base.drug.vo.UserVo;
import eccrm.base.drug.service.UserService;
import eccrm.base.parameter.service.ParameterContainer;
import org.springframework.stereotype.Service;
import com.ycrl.core.beans.BeanWrapBuilder;
import com.ycrl.core.beans.BeanWrapCallback;
import com.ycrl.core.hibernate.validator.ValidatorUtils;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Rechried
 */
@Service("userService")
public class UserServiceImpl implements UserService, BeanWrapCallback<User, UserVo> {
    @Resource
    private UserDao userDao;
    @Resource
    private DopeDao dopeDao;
    @Resource
    private LaborDao laborDao;
    @Resource
    private MaybeDrugDao maybeDrugDao;
    @Resource
    private PrisonDao prisonDao;

    @Override
    public String save(User user) {
        ValidatorUtils.validate(user);
        String id = userDao.save(user);
        return id;
    }

    @Override
    public void update(User user) {
        ValidatorUtils.validate(user);
        userDao.update(user);
    }

    @Override
    public PageVo pageQuery(UserBo bo) {
        PageVo vo = new PageVo();
        Long total = userDao.getTotal(bo);
        vo.setTotal(total);
        if (total==null || total == 0) return vo;
        List<User> userList = userDao.query(bo);
        List<UserVo> vos = BeanWrapBuilder.newInstance()
            .setCallback(this)
            .wrapList(userList,UserVo.class);
        vo.setData(vos);
        return vo;
    }


    @Override
    public UserVo findById(String id) {
        User user = userDao.findById(id);
        return BeanWrapBuilder.newInstance()
            .wrap(user, UserVo.class);
    }

    @Override
    public void deleteByIds(String[] ids) {
        if (ids == null || ids.length == 0) return;
        for (String id : ids) {
            userDao.deleteById(id);
        }
    }

    @Override
    public void addLog(String[] ids, String flag) {
        if (ids == null || ids.length == 0) return;
        for (String id : ids) {
            if(flag.equals("1")){
                Labor labor=new Labor();
                labor.setUserId(id);
                labor.setIsDrug("1");
                laborDao.save(labor);
            }else if(flag.equals("2")){
                Dope dope=new Dope();
                dope.setUserId(id);
                dope.setRecord("1");
                dopeDao.save(dope);
            }else if(flag.equals("3")){
                MaybeDrug drug=new MaybeDrug();
                drug.setUserId(id);
                drug.setRecord("1");
                maybeDrugDao.save(drug);
            }else if(flag.equals("4")){
                Prison prison=new Prison();
                prison.setUserId(id);
                prisonDao.save(prison);
            }else if(flag.equals("5")){

            }else if(flag.equals("6")){

            }else if(flag.equals("7")){

            }else{

            }
        }
    }

    @Override
    public void doCallback(User user, UserVo vo) {
        ParameterContainer container = ParameterContainer.getInstance();
        vo.setSex(container.getBusinessName(BaseParameter.SEX, user.getSex()));
        vo.setNation(container.getBusinessName("BP_NATION", user.getNation()));
    }
}
