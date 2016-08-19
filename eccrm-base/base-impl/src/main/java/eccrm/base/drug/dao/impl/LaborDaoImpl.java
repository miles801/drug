package eccrm.base.drug.dao.impl;

import com.michael.base.common.BaseParameter;
import com.ycrl.core.context.SecurityContext;
import com.ycrl.core.pager.Pager;
import eccrm.base.drug.bo.LaborBo;
import eccrm.base.drug.domain.Labor;
import eccrm.base.drug.dao.LaborDao;
import com.ycrl.core.HibernateDaoHelper;
import eccrm.base.drug.domain.User;
import eccrm.base.drug.vo.LaborVo;
import eccrm.base.drug.vo.UserVo;
import eccrm.base.parameter.service.ParameterContainer;
import eccrm.utils.codeutils.Page;
import org.hibernate.Query;
import org.hibernate.criterion.*;
import com.ycrl.core.hibernate.criteria.CriteriaUtils;
import org.hibernate.Criteria;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @author Rechried
 */
@Repository("laborDao")
public class LaborDaoImpl extends HibernateDaoHelper implements LaborDao {

    @Override
    public String save(Labor labor) {
        return (String) getSession().save(labor);
    }

    @Override
    public void update(Labor labor) {
        getSession().update(labor);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<LaborVo> query(LaborBo bo) {
        String orgId= SecurityContext.getOrgId();
        String ad="SELECT a.id FROM `sys_position_resource` a ,sys_position p,sys_resource r ,sys_position_emp e " +
                " where e.positionId=p.id and e.empId='"+SecurityContext.getEmpId()+"' " +
                "and a.positionId=p.id  and a.resourceId=r.id and r.`code`='IS_ADMIN' and p.`code`='superAdmin'";
        List<Object> lis=  getSession().createSQLQuery(ad).list();

        StringBuffer hql = new StringBuffer();
        hql.append("from User u,Labor l where u.id=l.userId ");
        if (!StringUtils.isEmpty(bo.getName())) {
            hql.append(" and u.name like '%" + bo.getName() + "%'");
        }
        if (!StringUtils.isEmpty(bo.getOrgId())) {
            hql.append(" and u.orgId='" + bo.getOrgId() + "'");
        }
        if (!StringUtils.isEmpty(bo.getPhone())) {
            hql.append(" and u.phone='" + bo.getPhone() + "'");
        }
        if(lis!=null&&lis.size()==0){
            String sql="SELECT id FROM `sys_org` where id='"+orgId+"' or parentId='"+orgId+"'";
            List<Object> o=  getSession().createSQLQuery(sql).list();
            hql.append(" and u.orgId in (" + ListToStringUtil.listToString(o) + ")");
        }
        hql.append(" order by l.createdDatetime desc");
        Query query = getSession().createQuery(hql.toString());
        if (!StringUtils.isEmpty(Pager.getStart())){
            query.setFirstResult(Pager.getStart());
            query.setMaxResults(Pager.getLimit());
        }
        List list = query.list();
        List<LaborVo> lists = new ArrayList();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Object[] o = (Object[]) iterator.next();
            User u = (User) o[0];
            UserVo v=new UserVo();
            BeanUtils.copyProperties(u,v);
            Labor l = (Labor) o[1];
            LaborVo vo = new LaborVo();
            ParameterContainer container = ParameterContainer.getInstance();
            v.setSex(container.getBusinessName(BaseParameter.SEX, u.getSex()));
            v.setNation(container.getBusinessName("BP_NATION", u.getNation()));
            BeanUtils.copyProperties(l,vo);
            if(vo.getIsDrug().equals("1")){
                vo.setIsDrug("否");
            }
            if(vo.getIsDrug().equals("2")){
                vo.setIsDrug("是");
            }
            BeanUtils.copyProperties(v,vo);
            vo.setUser(v);
            vo.setLabor(l);
            lists.add(vo);
        }
        return lists;
    }

    @Override
    public Long getTotal(LaborBo bo) {
        String orgId= SecurityContext.getOrgId();
        String ad="SELECT a.id FROM `sys_position_resource` a ,sys_position p,sys_resource r ,sys_position_emp e " +
                " where e.positionId=p.id and e.empId='"+SecurityContext.getEmpId()+"' " +
                "and a.positionId=p.id  and a.resourceId=r.id and r.`code`='IS_ADMIN' and p.`code`='superAdmin'";
        List<Object> lis=  getSession().createSQLQuery(ad).list();

        StringBuffer hql = new StringBuffer();
        hql.append("from User u,Labor l where u.id=l.userId ");
        if (!StringUtils.isEmpty(bo.getName())) {
            hql.append(" and u.name like '%" + bo.getName() + "%'");
        }
        if (!StringUtils.isEmpty(bo.getOrgId())) {
            hql.append(" and u.orgId='" + bo.getOrgId() + "'");
        }
        if (!StringUtils.isEmpty(bo.getPhone())) {
            hql.append(" and u.phone='" + bo.getPhone() + "'");
        }
        if(lis!=null&&lis.size()==0){
            String sql="SELECT id FROM `sys_org` where id='"+orgId+"' or parentId='"+orgId+"'";
            List<Object> o=  getSession().createSQLQuery(sql).list();
            hql.append(" and u.orgId in (" + ListToStringUtil.listToString(o) + ")");
        }
        hql.append(" order by l.createdDatetime desc");
        List lists = getSession().createQuery(hql.toString()).list();
        return (long) (lists != null ? lists.size() : 0);
    }


    @Override
    public void deleteById(String id) {
        getSession().createQuery("delete from " + Labor.class.getName() + " e where e.id=?")
                .setParameter(0, id)
                .executeUpdate();
    }

    @Override
    public void delete(Labor labor) {
        Assert.notNull(labor, "要删除的对象不能为空!");
        getSession().delete(labor);
    }

    @Override
    public User findLaborByUserId(String id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Labor.class);
        detachedCriteria.setProjection(Projections.property("userId"));
        detachedCriteria.add(Restrictions.eq("userId",id));
        Criteria criteria=createCriteria(User.class);
        criteria.add(Property.forName("id").in(detachedCriteria));
        return (User) criteria.uniqueResult();
    }


    @Override
    public LaborVo findById(String id) {
        Assert.hasText(id, "ID不能为空!");
        String hql="from User u,Labor l where u.id=l.userId and l.id=? ";
        Query query = getSession().createQuery(hql.toString()).setParameter(0,id);
        List list = query.list();
        Iterator iterator = list.iterator();
        LaborVo vo=new LaborVo();
        while (iterator.hasNext()) {
            Object[] o = (Object[]) iterator.next();
            User u = (User) o[0];
            UserVo v=new UserVo();
            BeanUtils.copyProperties(u,v);
            Labor l = (Labor) o[1];
            vo.setUser(v);
            vo.setLabor(l);
        }
        return vo;
    }

    private void initCriteria(Criteria criteria, LaborBo bo) {
        Assert.notNull(criteria, "criteria must not be null!");
        CriteriaUtils.addCondition(criteria, bo);
    }

}