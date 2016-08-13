package eccrm.base.drug.service.impl;

import com.ycrl.core.pager.PageVo;
import com.ycrl.base.common.CommonStatus;
import eccrm.base.drug.bo.PrisonBo;
import eccrm.base.drug.dao.UserDao;
import eccrm.base.drug.domain.Prison;
import eccrm.base.drug.domain.User;
import eccrm.base.drug.vo.MaybeDrugVo;
import eccrm.base.drug.vo.PrisonVo;
import eccrm.base.drug.dao.PrisonDao;
import eccrm.base.drug.service.PrisonService;
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
@Service("prisonService")
public class PrisonServiceImpl implements PrisonService, BeanWrapCallback<Prison, PrisonVo> {
    @Resource
    private PrisonDao prisonDao;
    @Resource
    private UserDao userDao;

    @Override
    public String save(Prison prison) {
        ValidatorUtils.validate(prison);
        String id = prisonDao.save(prison);
        return id;
    }

    @Override
    public void update(Prison prison) {
        ValidatorUtils.validate(prison);
        prisonDao.update(prison);
    }

    @Override
    public PageVo pageQuery(PrisonBo bo) {
        PageVo vo = new PageVo();
        Long total = prisonDao.getTotal(bo);
        vo.setTotal(total);
        if (total==null || total == 0) return vo;
        List<PrisonVo> prisonList = prisonDao.query(bo);
        vo.setData(prisonList);
        return vo;
    }


    @Override
    public PrisonVo findById(String id) {
        PrisonVo prison = prisonDao.findById(id);
        return prison;
    }

    @Override
    public void deleteByIds(String[] ids) {
        if (ids == null || ids.length == 0) return;
        for (String id : ids) {
            PrisonVo vo=prisonDao.findById(id);
            User user=userDao.findById(vo.getUser().getId());
            user.setIsPrison("否");
            userDao.update(user);
            prisonDao.deleteById(id);
        }
    }

    @Override
    public void doCallback(Prison prison, PrisonVo vo) {
    }
}
