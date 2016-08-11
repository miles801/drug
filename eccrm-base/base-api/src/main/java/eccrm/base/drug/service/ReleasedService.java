package eccrm.base.drug.service;

import com.ycrl.core.pager.PageVo;
import eccrm.base.drug.bo.ReleasedBo;
import eccrm.base.drug.domain.Released;
import eccrm.base.drug.vo.ReleasedVo;

/**
 * @author Rechried
 * 
 */
public interface ReleasedService {

    /**
     * 保存
     */
    String save(Released released);

    /**
     * 更新
     */
    void update(Released released);

    /**
     * 分页查询
     */
    PageVo pageQuery(ReleasedBo bo);

    /**
     * 根据ID查询对象的信息
     */
    ReleasedVo findById(String id);

    /**
     * 批量删除
     */
    void deleteByIds(String[] ids);

}
