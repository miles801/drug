package eccrm.base.drug.dao;

import eccrm.base.drug.bo.LaborBo;
import eccrm.base.drug.domain.Labor;
import eccrm.base.drug.vo.LaborVo;
import java.util.List;

/**
 * @author Rechried
 */
public interface LaborDao {

    String save(Labor labor);

    void update(Labor labor);

    /**
     * 高级查询接口
     */
    List<LaborVo> query(LaborBo bo);

    /**
     * 查询总记录数
     */
    Long getTotal(LaborBo bo);

    LaborVo findById(String id);

    void deleteById(String id);

    /**
     * 根据实体对象删除
     * 必须保证该实体是存在的（一般是get或者load得到的对象）
     */
    void delete(Labor labor);

}
