package eccrm.base.drug.service.impl;

import com.michael.base.common.BaseParameter;
import com.ycrl.core.pager.PageVo;
import com.ycrl.base.common.CommonStatus;
import eccrm.base.drug.bo.MaybeDrugBo;
import eccrm.base.drug.domain.MaybeDrug;
import eccrm.base.drug.vo.MaybeDrugVo;
import eccrm.base.drug.dao.MaybeDrugDao;
import eccrm.base.drug.service.MaybeDrugService;
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
@Service("maybeDrugService")
public class MaybeDrugServiceImpl implements MaybeDrugService, BeanWrapCallback<MaybeDrug, MaybeDrugVo> {
    @Resource
    private MaybeDrugDao maybeDrugDao;

    @Override
    public String save(MaybeDrug maybeDrug) {
        ValidatorUtils.validate(maybeDrug);
        String id = maybeDrugDao.save(maybeDrug);
        return id;
    }

    @Override
    public void update(MaybeDrug maybeDrug) {
        ValidatorUtils.validate(maybeDrug);
        maybeDrugDao.update(maybeDrug);
    }

    @Override
    public PageVo pageQuery(MaybeDrugBo bo) {
        PageVo vo = new PageVo();
        Long total = maybeDrugDao.getTotal(bo);
        vo.setTotal(total);
        if (total==null || total == 0) return vo;
        List<MaybeDrugVo> maybeDrugList = maybeDrugDao.query(bo);
        vo.setData(maybeDrugList);
        return vo;
    }


    @Override
    public MaybeDrugVo findById(String id) {
        MaybeDrugVo maybeDrug = maybeDrugDao.findById(id);
        return maybeDrug;
    }

    @Override
    public void deleteByIds(String[] ids) {
        if (ids == null || ids.length == 0) return;
        for (String id : ids) {
            maybeDrugDao.deleteById(id);
        }
    }

    @Override
    public void doCallback(MaybeDrug maybeDrug, MaybeDrugVo vo) {
//        ParameterContainer container = ParameterContainer.getInstance();
//        vo.setSex(container.getBusinessName(BaseParameter.SEX, maybeDrug.getSex()));
//        vo.setNation(container.getBusinessName("BP_NATION", maybeDrug.getNation()));
//        vo.setDegree(container.getBusinessName("BP_XW", maybeDrug.getDegree()));
//        if(maybeDrug.getRecord().equals("1")){
//            vo.setRecord("否");
//        }
//        if(maybeDrug.getRecord().equals("2")){
//            vo.setRecord("是");
//        }
    }
}
