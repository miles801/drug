package eccrm.base.drug.dao;

import eccrm.base.drug.bo.UserBo;
import eccrm.base.drug.domain.User;
import eccrm.base.drug.vo.UserVo;
import java.util.List;

/**
 * @author Rechried
 */
public interface UserDao {

    String save(User user);

    void update(User user);

    /**
     * 高级查询接口
     */
    List<User> query(UserBo bo);

    /**
     * 查询总记录数
     */
    Long getTotal(UserBo bo);

    User findById(String id);

    void deleteById(String id);

    /**
     * 根据实体对象删除
     * 必须保证该实体是存在的（一般是get或者load得到的对象）
     */
    void delete(User user);

    User findUserByCard(String idCard);

    void saveOrUpdate(User users);
}
