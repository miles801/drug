package eccrm.base.drug.dao;

import eccrm.base.drug.bo.MaybeDrugBo;
import eccrm.base.drug.domain.MaybeDrug;
import eccrm.base.drug.vo.MaybeDrugVo;
import java.util.List;

/**
 * @author Rechried
 */
public interface MaybeDrugDao {

    String save(MaybeDrug maybeDrug);

    void update(MaybeDrug maybeDrug);

    /**
     * 高级查询接口
     */
    List<MaybeDrugVo> query(MaybeDrugBo bo);

    /**
     * 查询总记录数
     */
    Long getTotal(MaybeDrugBo bo);

    MaybeDrugVo findById(String id);

    void deleteById(String id);

    /**
     * 根据实体对象删除
     * 必须保证该实体是存在的（一般是get或者load得到的对象）
     */
    void delete(MaybeDrug maybeDrug);
}
