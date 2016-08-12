package eccrm.base.drug.service.impl;

import com.michael.base.common.BaseParameter;
import com.michael.poi.adapter.AnnotationCfgAdapter;
import com.michael.poi.core.Handler;
import com.michael.poi.core.ImportEngine;
import com.michael.poi.imp.cfg.Configuration;
import com.ycrl.core.beans.BeanWrapBuilder;
import com.ycrl.core.beans.BeanWrapCallback;
import com.ycrl.core.context.SecurityContext;
import com.ycrl.core.hibernate.validator.ValidatorUtils;
import com.ycrl.core.pager.PageVo;
import eccrm.base.attachment.utils.AttachmentHolder;
import eccrm.base.drug.bo.UserBo;
import eccrm.base.drug.dao.*;
import eccrm.base.drug.domain.*;
import eccrm.base.drug.service.UserService;
import eccrm.base.drug.vo.UserVo;
import eccrm.base.parameter.service.ParameterContainer;
import eccrm.base.parameter.vo.BusinessParamItemVo;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
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
    @Resource
    private ReleasedDao releasedDao;
    @Resource
    private DrugDao drugDao;



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
                Released released=new Released();
                released.setUserId(id);
                releasedDao.save(released);
            }else {
                Drug drug=new Drug();
                drug.setUserId(id);
                drugDao.save(drug);
            }
        }
    }

    @Override
    public void saveUserFromExcel(String id) {
        Assert.hasText(id, "导入失败!未获取到数据文件!");
        File file = AttachmentHolder.newInstance().getTempFile(id);
        File destFile = new File(file.getAbsolutePath() + ".xlsx");
        try {
            FileUtils.copyFile(file, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final Configuration config = new AnnotationCfgAdapter(UserDTO.class).parse();
        config.setPath(destFile.getPath());
        config.setHandler(new Handler<UserDTO>() {
            @Override
            public void execute(UserDTO dto) {
                if(StringUtils.isEmpty(dto.getName())){
                    return;
                }
                User users=userDao.findUserByCard(dto.getIdCard());
                User u=new User();
                if(StringUtils.isEmpty(users)){
                    BeanUtils.copyProperties(dto,u);
                    users=u;
                }else{
                    BeanUtils.copyProperties(dto,users);
                }
                users.setNation(getValueByType("BP_NATION",dto.getNation()));
                users.setSex(getValueByType("BP_SEX",dto.getSex()));
                users.setOrgId(SecurityContext.getOrgId());
                users.setOrgName(SecurityContext.getOrgName());
                userDao.saveOrUpdate(users);
            }
        });
        ImportEngine engine = new ImportEngine(config);
        engine.execute();
        destFile.delete();
    }

    /**
     * 通过业务参数的类型以及名称找到对应value
     *
     * @param type 业务参数
     * @param name 中文名称
     * @return 对应的值
     */
    private String getValueByType(String type, String name) {
        ParameterContainer container = ParameterContainer.getInstance();
        List<BusinessParamItemVo> items = container.getBusinessItems(type);
        for (BusinessParamItemVo vo : items) {
            if (vo.getName().equals(name)) {
                return vo.getValue();
            }
        }
        return null;
    }

    @Override
    public void doCallback(User user, UserVo vo) {
        ParameterContainer container = ParameterContainer.getInstance();
        vo.setSex(container.getBusinessName(BaseParameter.SEX, user.getSex()));
        vo.setNation(container.getBusinessName("BP_NATION", user.getNation()));
    }
}
