package eccrm.base.drug.service.impl;

import com.ycrl.core.pager.PageVo;
import com.ycrl.base.common.CommonStatus;
import eccrm.base.drug.bo.ReleasedBo;
import eccrm.base.drug.domain.Released;
import eccrm.base.drug.vo.ReleasedVo;
import eccrm.base.drug.dao.ReleasedDao;
import eccrm.base.drug.service.ReleasedService;
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
@Service("releasedService")
public class ReleasedServiceImpl implements ReleasedService, BeanWrapCallback<Released, ReleasedVo> {
    @Resource
    private ReleasedDao releasedDao;

    @Override
    public String save(Released released) {
        ValidatorUtils.validate(released);
        String id = releasedDao.save(released);
        return id;
    }

    @Override
    public void update(Released released) {
        ValidatorUtils.validate(released);
        releasedDao.update(released);
    }

    @Override
    public PageVo pageQuery(ReleasedBo bo) {
        PageVo vo = new PageVo();
        Long total = releasedDao.getTotal(bo);
        vo.setTotal(total);
        if (total==null || total == 0) return vo;
        List<ReleasedVo> releasedList = releasedDao.query(bo);
        vo.setData(releasedList);
        return vo;
    }


    @Override
    public ReleasedVo findById(String id) {
        ReleasedVo released = releasedDao.findById(id);
        return released;
    }

    @Override
    public void deleteByIds(String[] ids) {
        if (ids == null || ids.length == 0) return;
        for (String id : ids) {
            releasedDao.deleteById(id);
        }
    }

    @Override
    public void doCallback(Released released, ReleasedVo vo) {
    }
}
