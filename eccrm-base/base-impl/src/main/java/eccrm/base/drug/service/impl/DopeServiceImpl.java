package eccrm.base.drug.service.impl;

import com.michael.base.common.BaseParameter;
import com.ycrl.core.pager.PageVo;
import com.ycrl.base.common.CommonStatus;
import eccrm.base.drug.bo.DopeBo;
import eccrm.base.drug.domain.Dope;
import eccrm.base.drug.vo.DopeVo;
import eccrm.base.drug.dao.DopeDao;
import eccrm.base.drug.service.DopeService;
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
@Service("dopeService")
public class DopeServiceImpl implements DopeService, BeanWrapCallback<Dope, DopeVo> {
    @Resource
    private DopeDao dopeDao;

    @Override
    public String save(Dope dope) {
        ValidatorUtils.validate(dope);
        String id = dopeDao.save(dope);
        return id;
    }

    @Override
    public void update(Dope dope) {
        ValidatorUtils.validate(dope);
        dopeDao.update(dope);
    }

    @Override
    public PageVo pageQuery(DopeBo bo) {
        PageVo vo = new PageVo();
        Long total = dopeDao.getTotal(bo);
        vo.setTotal(total);
        if (total==null || total == 0) return vo;
        List<Dope> dopeList = dopeDao.query(bo);
        List<DopeVo> vos = BeanWrapBuilder.newInstance()
            .setCallback(this)
            .wrapList(dopeList,DopeVo.class);
        vo.setData(vos);
        return vo;
    }


    @Override
    public DopeVo findById(String id) {
        Dope dope = dopeDao.findById(id);
        return BeanWrapBuilder.newInstance()
            .wrap(dope, DopeVo.class);
    }

    @Override
    public void deleteByIds(String[] ids) {
        if (ids == null || ids.length == 0) return;
        for (String id : ids) {
            dopeDao.deleteById(id);
        }
    }

    @Override
    public void doCallback(Dope dope, DopeVo vo) {
        ParameterContainer container = ParameterContainer.getInstance();
        vo.setSex(container.getBusinessName(BaseParameter.SEX, dope.getSex()));
        vo.setNation(container.getBusinessName("BP_NATION", dope.getNation()));
        vo.setDegree(container.getBusinessName("BP_XW", dope.getDegree()));
        if(dope.getRecord().equals("1")){
            vo.setRecord("否");
        }
        if(dope.getRecord().equals("2")){
            vo.setRecord("是");
        }
    }
}
