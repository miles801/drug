package eccrm.base.drug.service;

import com.ycrl.core.pager.PageVo;
import eccrm.base.drug.bo.PrisonBo;
import eccrm.base.drug.domain.Prison;
import eccrm.base.drug.vo.PrisonVo;

/**
 * @author Rechried
 * 
 */
public interface PrisonService {

    /**
     * 保存
     */
    String save(Prison prison);

    /**
     * 更新
     */
    void update(Prison prison);

    /**
     * 分页查询
     */
    PageVo pageQuery(PrisonBo bo);

    /**
     * 根据ID查询对象的信息
     */
    PrisonVo findById(String id);

    /**
     * 批量删除
     */
    void deleteByIds(String[] ids);

}
