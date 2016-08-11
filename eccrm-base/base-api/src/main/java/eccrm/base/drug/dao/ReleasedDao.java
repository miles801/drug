package eccrm.base.drug.dao;

import eccrm.base.drug.bo.ReleasedBo;
import eccrm.base.drug.domain.Released;
import eccrm.base.drug.vo.ReleasedVo;
import java.util.List;

/**
 * @author Rechried
 */
public interface ReleasedDao {

    String save(Released released);

    void update(Released released);

    /**
     * 高级查询接口
     */
    List<ReleasedVo> query(ReleasedBo bo);

    /**
     * 查询总记录数
     */
    Long getTotal(ReleasedBo bo);

    ReleasedVo findById(String id);

    void deleteById(String id);

    /**
     * 根据实体对象删除
     * 必须保证该实体是存在的（一般是get或者load得到的对象）
     */
    void delete(Released released);
}
