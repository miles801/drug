package eccrm.base.drug.dao;

import eccrm.base.drug.bo.DrugBo;
import eccrm.base.drug.domain.Drug;
import eccrm.base.drug.vo.DrugVo;
import java.util.List;

/**
 * @author Rechried
 */
public interface DrugDao {

    String save(Drug drug);

    void update(Drug drug);

    /**
     * 高级查询接口
     */
    List<DrugVo> query(DrugBo bo);

    /**
     * 查询总记录数
     */
    Long getTotal(DrugBo bo);

    DrugVo findById(String id);

    void deleteById(String id);

    /**
     * 根据实体对象删除
     * 必须保证该实体是存在的（一般是get或者load得到的对象）
     */
    void delete(Drug drug);
}
