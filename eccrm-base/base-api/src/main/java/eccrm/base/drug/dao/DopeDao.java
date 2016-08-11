package eccrm.base.drug.dao;

import eccrm.base.drug.bo.DopeBo;
import eccrm.base.drug.domain.Dope;
import eccrm.base.drug.vo.DopeVo;
import java.util.List;

/**
 * @author Rechried
 */
public interface DopeDao {

    String save(Dope dope);

    void update(Dope dope);

    /**
     * 高级查询接口
     */
    List<DopeVo> query(DopeBo bo);

    /**
     * 查询总记录数
     */
    Long getTotal(DopeBo bo);

    DopeVo findById(String id);

    void deleteById(String id);

    /**
     * 根据实体对象删除
     * 必须保证该实体是存在的（一般是get或者load得到的对象）
     */
    void delete(Dope dope);
}
