package eccrm.base.drug.service;

import com.ycrl.core.pager.PageVo;
import eccrm.base.drug.bo.DopeBo;
import eccrm.base.drug.domain.Dope;
import eccrm.base.drug.vo.DopeVo;

/**
 * @author Rechried
 * 
 */
public interface DopeService {

    /**
     * 保存
     */
    String save(Dope dope);

    /**
     * 更新
     */
    void update(Dope dope);

    /**
     * 分页查询
     */
    PageVo pageQuery(DopeBo bo);

    /**
     * 根据ID查询对象的信息
     */
    DopeVo findById(String id);

    /**
     * 批量删除
     */
    void deleteByIds(String[] ids);

}
