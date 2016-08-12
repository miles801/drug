package eccrm.base.drug.service;

import com.ycrl.core.pager.PageVo;
import eccrm.base.drug.bo.DrugBo;
import eccrm.base.drug.domain.Drug;
import eccrm.base.drug.domain.DrugHelp;
import eccrm.base.drug.vo.DrugVo;

/**
 * @author Rechried
 * 
 */
public interface DrugService {

    /**
     * 保存
     */
    String save(Drug drug);

    /**
     * 更新
     */
    void update(Drug drug);

    /**
     * 分页查询
     */
    PageVo pageQuery(DrugBo bo);

    /**
     * 根据ID查询对象的信息
     */
    DrugVo findById(String id);

    /**
     * 批量删除
     */
    void deleteByIds(String[] ids);

    void addDrugHelp(DrugHelp drug);
}
