package eccrm.base.drug.service.impl;

import com.michael.base.common.BaseParameter;
import com.ycrl.core.pager.PageVo;
import com.ycrl.base.common.CommonStatus;
import eccrm.base.drug.bo.LaborBo;
import eccrm.base.drug.domain.Labor;
import eccrm.base.drug.vo.LaborVo;
import eccrm.base.drug.dao.LaborDao;
import eccrm.base.drug.service.LaborService;
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
@Service("laborService")
public class LaborServiceImpl implements LaborService, BeanWrapCallback<Labor, LaborVo> {
    @Resource
    private LaborDao laborDao;

    @Override
    public String save(Labor labor) {
        ValidatorUtils.validate(labor);
        String id = laborDao.save(labor);
        return id;
    }

    @Override
    public void update(Labor labor) {
        ValidatorUtils.validate(labor);
        laborDao.update(labor);
    }

    @Override
    public PageVo pageQuery(LaborBo bo) {
        PageVo vo = new PageVo();
        Long total = laborDao.getTotal(bo);
        vo.setTotal(total);
        if (total==null || total == 0) return vo;
        List<LaborVo> laborList = laborDao.query(bo);
        vo.setData(laborList);
        return vo;
    }


    @Override
    public LaborVo findById(String id) {
        LaborVo labor = laborDao.findById(id);
        return labor;
    }

    @Override
    public void deleteByIds(String[] ids) {
        if (ids == null || ids.length == 0) return;
        for (String id : ids) {
            laborDao.deleteById(id);
        }
    }

    @Override
    public void doCallback(Labor labor, LaborVo vo) {
//        ParameterContainer container = ParameterContainer.getInstance();
//        vo.setSex(container.getBusinessName(BaseParameter.SEX, labor.getSex()));
//        vo.setNation(container.getBusinessName("BP_NATION", labor.getNation()));
//        if(labor.getIsDrug().equals("1")){
//            vo.setIsDrug("否");
//        }
//        if(labor.getIsDrug().equals("2")){
//            vo.setIsDrug("是");
//        }
    }
}
