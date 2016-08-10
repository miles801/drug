package eccrm.base.drug.service;

import com.ycrl.core.pager.PageVo;
import eccrm.base.drug.bo.LaborBo;
import eccrm.base.drug.domain.Labor;
import eccrm.base.drug.vo.LaborVo;

/**
 * @author Rechried
 * 
 */
public interface LaborService {

    /**
     * 保存
     */
    String save(Labor labor);

    /**
     * 更新
     */
    void update(Labor labor);

    /**
     * 分页查询
     */
    PageVo pageQuery(LaborBo bo);

    /**
     * 根据ID查询对象的信息
     */
    LaborVo findById(String id);

    /**
     * 批量删除
     */
    void deleteByIds(String[] ids);

}
