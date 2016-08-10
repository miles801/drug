package eccrm.base.drug.service;

import com.ycrl.core.pager.PageVo;
import eccrm.base.drug.bo.MaybeDrugBo;
import eccrm.base.drug.domain.MaybeDrug;
import eccrm.base.drug.vo.MaybeDrugVo;

/**
 * @author Rechried
 * 
 */
public interface MaybeDrugService {

    /**
     * 保存
     */
    String save(MaybeDrug maybeDrug);

    /**
     * 更新
     */
    void update(MaybeDrug maybeDrug);

    /**
     * 分页查询
     */
    PageVo pageQuery(MaybeDrugBo bo);

    /**
     * 根据ID查询对象的信息
     */
    MaybeDrugVo findById(String id);

    /**
     * 批量删除
     */
    void deleteByIds(String[] ids);

}
