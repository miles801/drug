package eccrm.base.drug.service;

import com.ycrl.core.pager.PageVo;
import eccrm.base.drug.bo.UserBo;
import eccrm.base.drug.domain.User;
import eccrm.base.drug.vo.UserVo;

/**
 * @author Rechried
 * 
 */
public interface UserService {

    /**
     * 保存
     */
    String save(User user);

    /**
     * 更新
     */
    void update(User user);

    /**
     * 分页查询
     */
    PageVo pageQuery(UserBo bo);

    /**
     * 根据ID查询对象的信息
     */
    UserVo findById(String id);

    /**
     * 批量删除
     */
    void deleteByIds(String[] ids);

    void addLog(String[] idArr, String flag);

    void saveUserFromExcel(String ids);

    void saveRelation(User user);

    PageVo pageQueryRelation(UserBo bo);

    PageVo pageQueryParent(UserBo bo);

    void resetRelation(String idArr);

    PageVo allPageQuery(UserBo bo);
}
