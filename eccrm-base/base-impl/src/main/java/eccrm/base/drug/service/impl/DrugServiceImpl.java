package eccrm.base.drug.service.impl;

import com.ycrl.core.pager.PageVo;
import com.ycrl.base.common.CommonStatus;
import eccrm.base.drug.bo.DrugBo;
import eccrm.base.drug.dao.DrugHelpDao;
import eccrm.base.drug.domain.Drug;
import eccrm.base.drug.domain.DrugHelp;
import eccrm.base.drug.vo.DrugVo;
import eccrm.base.drug.dao.DrugDao;
import eccrm.base.drug.service.DrugService;
import org.springframework.stereotype.Service;
import com.ycrl.core.beans.BeanWrapBuilder;
import com.ycrl.core.beans.BeanWrapCallback;
import com.ycrl.core.hibernate.validator.ValidatorUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Rechried
 */
@Service("drugService")
public class DrugServiceImpl implements DrugService, BeanWrapCallback<Drug, DrugVo> {
    @Resource
    private DrugDao drugDao;
    @Resource
    private DrugHelpDao drugHelpDao;

    @Override
    public String save(Drug drug) {
        ValidatorUtils.validate(drug);
        String id = drugDao.save(drug);
        return id;
    }

    @Override
    public void update(Drug drug,List<DrugHelp> list) {
        ValidatorUtils.validate(drug);
        drugDao.update(drug);
        drugHelpDao.delete(drug.getId());
        if (!StringUtils.isEmpty(list)){
            for (int i=0;i<list.size();i++) {
                DrugHelp a = list.get(i);
                a.setId(null);
                drugHelpDao.save(a);
            }
        }
    }

    @Override
    public PageVo pageQuery(DrugBo bo) {
        PageVo vo = new PageVo();
        Long total = drugDao.getTotal(bo);
        vo.setTotal(total);
        if (total==null || total == 0) return vo;
        List<DrugVo> drugList = drugDao.query(bo);
        vo.setData(drugList);
        return vo;
    }


    @Override
    public DrugVo findById(String id) {
        DrugVo drug = drugDao.findById(id);
        return drug;
    }

    @Override
    public void deleteByIds(String[] ids) {
        if (ids == null || ids.length == 0) return;
        for (String id : ids) {
            drugDao.deleteById(id);
        }
    }

    @Override
    public void addDrugHelp(DrugHelp drug) {
        drugHelpDao.saveOrUpdate(drug);
    }

    @Override
    public void doCallback(Drug drug, DrugVo vo) {
    }
}
