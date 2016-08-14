package eccrm.base.drug.dao.impl;

import com.michael.base.org.domain.Org;
import com.ycrl.core.HibernateDaoHelper;
import com.ycrl.core.hibernate.criteria.CriteriaUtils;
import com.ycrl.core.pager.Pager;
import eccrm.base.drug.bo.UserBo;
import eccrm.base.drug.dao.UserDao;
import eccrm.base.drug.domain.AllDrug;
import eccrm.base.drug.domain.User;
import eccrm.base.drug.vo.AllDrugVo;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
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
    public Long getAllTotal(UserBo bo) {
        Criteria criteria=createRowCountsCriteria(Org.class);
        criteria.add(Restrictions.eq("deleted",false));
        criteria.addOrder(Order.asc("id"));
        return (Long) criteria.uniqueResult();
    }

    @Override
    public List<AllDrugVo> allPageQuery(UserBo bo) {
        Criteria criteria=createCriteria(Org.class);
        criteria.add(Restrictions.eq("deleted",false));
        criteria.addOrder(Order.asc("id"));
        List<Org> list=criteria.list();
        List<AllDrugVo> voList=new ArrayList<>();
        for (Org org:list) {
            AllDrugVo vo = new AllDrugVo();
            vo.setOrgName(org.getName());
            String orgId = org.getId();
            String sql = "SELECT count(u.id) coutParent FROM `drug_user` u LEFT JOIN sys_org o ON o.id=u.orgId where isLeader='是' and u.orgId='"+orgId+"'";
            String sql2 = "select COUNT(u.id) from  drug_user u,sys_org o where  o.id=u.orgId and u.orgId='" + orgId + "'" ;
            String sql3 = "select count(f.id) from drug_dope f , drug_user u ,sys_org o where f.userId=u.id and o.id=u.orgId and u.orgId='" + orgId + "'" ;
            String sql4 = "select count(x.id) from drug_maybe_drug x, drug_user u ,sys_org o where x.userId=u.id and o.id=u.orgId and u.orgId='"+orgId+"'" ;
            String sql5 = "select count(p.id) from drug_prison p,drug_user u ,sys_org o where p.userId=u.id and o.id=u.orgId and u.orgId='"+orgId+"'" ;
            String sql6 =  "select count(r.id) from drug_released r,drug_user u ,sys_org o where r.userId=u.id and o.id=u.orgId and u.orgId='"+orgId+"'" ;
            String sql7 =   "select count(r.id) from drug_drug r ,drug_user u ,sys_org o where r.userId=u.id and o.id=u.orgId and u.orgId='"+orgId+"' and controlXType=1 " ;
            String sql8 =   "select count(r.id) from drug_drug r ,drug_user u ,sys_org o where r.userId=u.id and o.id=u.orgId and u.orgId='"+orgId+"' and r.controlXType=4" ;
            String sql9 =   "select count(r.id) from drug_drug r ,drug_user u ,sys_org o where r.userId=u.id and o.id=u.orgId and u.orgId='"+orgId+"' and r.controlXType=3" ;
            String sql10 =  "select count(r.id) from drug_drug r ,drug_user u ,sys_org o where r.userId=u.id and o.id=u.orgId and u.orgId='"+orgId+"' and r.controlXType=6" ;
            String sql11 = "select count(r.id) from drug_drug r ,drug_user u ,sys_org o where r.userId=u.id and o.id=u.orgId and u.orgId='"+orgId+"' and r.controlXType=2" ;
            String sql12 =  "select count(r.id) from drug_drug r ,drug_user u ,sys_org o where r.userId=u.id and o.id=u.orgId and u.orgId='"+orgId+"' and r.controlXType=5" ;
            String sql13=  "select count(r.id) from drug_drug r ,drug_user u ,sys_org o where r.userId=u.id and o.id=u.orgId and u.orgId='"+orgId+"' and r.controlXType=7" ;
            List<Object> o=  getSession().createSQLQuery(sql).list();
            List<Object> o2=  getSession().createSQLQuery(sql2).list();
            List<Object> o3=  getSession().createSQLQuery(sql3).list();
            List<Object> o4=  getSession().createSQLQuery(sql4).list();
            List<Object> o5=  getSession().createSQLQuery(sql5).list();
            List<Object> o6=  getSession().createSQLQuery(sql6).list();
            List<Object> o7=  getSession().createSQLQuery(sql7).list();
            List<Object> o8=  getSession().createSQLQuery(sql8).list();
            List<Object> o9=  getSession().createSQLQuery(sql9).list();
            List<Object> o10=  getSession().createSQLQuery(sql10).list();
            List<Object> o11=  getSession().createSQLQuery(sql11).list();
            List<Object> o12=  getSession().createSQLQuery(sql12).list();
            List<Object> o13=  getSession().createSQLQuery(sql13).list();
            AllDrugVo vos=new AllDrugVo() ;
            vo.setCountPeople(o2.get(0)+"");
            vo.setCoutParent(o.get(0)+"");
            vo.setfDrug(o3.get(0)+"");
            vo.setxDrug(o4.get(0)+"");
            vo.setPrison(o5.get(0)+"");
            vo.setReleased(o6.get(0)+"");
            vo.setJiedu(o7.get(0)+"");
            vo.setYaowu(o8.get(0)+"");
            vo.setJiya(o9.get(0)+"");
            vo.setJieduan(o10.get(0)+"");
            vo.setShequjiedu(o11.get(0)+"");
            vo.setShequkangfu(o12.get(0)+"");
            vo.setOutOfControl(o13.get(0)+"");
            voList.add(vo);
        }
        return voList;
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