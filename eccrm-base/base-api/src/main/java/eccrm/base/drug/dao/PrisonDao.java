package eccrm.base.drug.dao;

import eccrm.base.drug.bo.PrisonBo;
import eccrm.base.drug.domain.Prison;
import eccrm.base.drug.vo.PrisonVo;
import java.util.List;

/**
 * @author Rechried
 */
public interface PrisonDao {

    String save(Prison prison);

    void update(Prison prison);

    /**
     * 高级查询接口
     */
    List<PrisonVo> query(PrisonBo bo);

    /**
     * 查询总记录数
     */
    Long getTotal(PrisonBo bo);

    PrisonVo findById(String id);

    void deleteById(String id);

    /**
     * 根据实体对象删除
     * 必须保证该实体是存在的（一般是get或者load得到的对象）
     */
    void delete(Prison prison);
}
