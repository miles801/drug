package eccrm.base.drug.dao.impl;

import com.ycrl.core.pager.Pager;
import eccrm.base.drug.bo.UserBo;
import eccrm.base.drug.domain.User;
import eccrm.base.drug.dao.UserDao;
import com.ycrl.core.HibernateDaoHelper;
import eccrm.utils.codeutils.Page;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import com.ycrl.core.hibernate.criteria.CriteriaUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;


/**
 * @author Rechried
 */
@Repository("userDao")
public class UserDaoImpl extends HibernateDaoHelper implements UserDao {

    @Override
    public String save(User user) {
        return (String) getSession().save(user);
    }

    @Override
    public void update(User user) {
        getSession().update(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> query(UserBo bo) {
        Criteria criteria = createCriteria(User.class);
        initCriteria(criteria, bo);
        criteria.addOrder(Order.asc("name"));
        if(!StringUtils.isEmpty(Pager.getStart())){
            criteria.setFirstResult(Pager.getStart());
            criteria.setMaxResults(Pager.getLimit());
        }
        return criteria.list();
    }

    @Override
    public Long getTotal(UserBo bo) {
        Criteria criteria = createRowCountsCriteria(User.class);
        initCriteria(criteria, bo);
        return (Long) criteria.uniqueResult();
    }


    @Override
    public void deleteById(String id) {
        getSession().createQuery("delete from " + User.class.getName() + " e where e.id=?")
                .setParameter(0, id)
                .executeUpdate();
    }

    @Override
    public void delete(User user) {
        Assert.notNull(user, "要删除的对象不能为空!");
        getSession().delete(user);
    }

    @Override
    public User findUserByCard(String idCard) {
        Criteria criteria=getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("idCard",idCard));
        return (User) criteria.uniqueResult();
    }

    @Override
    public void saveOrUpdate(User users) {
        getSession().saveOrUpdate(users);
    }

    @Override
    public List<User> queryRelation(UserBo bo) {
        String hql=" from "+User.class.getName()+" u where u.isParent=? order by u.id asc";
        Query query=getSession().createQuery(hql).setParameter(0,bo.getId());
        query.setFirstResult(Pager.getStart());
        query.setMaxResults(Pager.getLimit());
        List<User> list=query.list();
        return list;
    }

    @Override
    public Long getRelationTotal(UserBo bo) {
        String hql=" from "+User.class.getName()+" u where u.isParent=? order by u.id asc";
        Query query=getSession().createQuery(hql).setParameter(0,bo.getId());
        List<User> list=query.list();
        return (long)(list!=null?list.size():0);
    }

    @Override
    public List<User> pageQueryParent(UserBo bo) {
        Criteria criteria=getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("isLeader","否"));
        criteria.add(Restrictions.isNull("isParent"));
        initCriteria(criteria, bo);
        return  criteria.list();
    }

    @Override
    public Long getParentTotal(UserBo bo) {
        String hql=" from "+User.class.getName()+" u where u.isLeader='否'and (u.isParent is null or u.isParent='') order by u.id asc";
        Query query=getSession().createQuery(hql);
        List<User> list=query.list();
        return (long)(list!=null?list.size():0);
    }

    @Override
    public User findById(String id) {
        Assert.hasText(id, "ID不能为空!");
        return (User) getSession().get(User.class, id);
    }

    private void initCriteria(Criteria criteria, UserBo bo) {
        Assert.notNull(criteria, "criteria must not be null!");
        CriteriaUtils.addCondition(criteria, bo);
    }

}