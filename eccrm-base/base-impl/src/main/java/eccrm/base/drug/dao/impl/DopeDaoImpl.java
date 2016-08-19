package eccrm.base.drug.dao.impl;

import com.michael.base.common.BaseParameter;
import com.ycrl.core.HibernateDaoHelper;
import com.ycrl.core.context.SecurityContext;
import com.ycrl.core.hibernate.criteria.CriteriaUtils;
import com.ycrl.core.pager.Pager;
import eccrm.base.drug.bo.DopeBo;
import eccrm.base.drug.dao.DopeDao;
import eccrm.base.drug.domain.Dope;
import eccrm.base.drug.domain.Labor;
import eccrm.base.drug.domain.User;
import eccrm.base.drug.vo.DopeVo;
import eccrm.base.drug.vo.LaborVo;
import eccrm.base.drug.vo.UserVo;
import eccrm.base.parameter.service.ParameterContainer;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Property;
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
@Repository("dopeDao")
public class DopeDaoImpl extends HibernateDaoHelper implements DopeDao {

    @Override
    public String save(Dope dope) {
        return (String) getSession().save(dope);
    }

    @Override
    public void update(Dope dope) {
        getSession().update(dope);
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<DopeVo> query(DopeBo bo) {
        String orgId= SecurityContext.getOrgId();
        String ad="SELECT a.id FROM `sys_position_resource` a ,sys_position p,sys_resource r ,sys_position_emp e " +
                " where e.positionId=p.id and e.empId='"+SecurityContext.getEmpId()+"' " +
                "and a.positionId=p.id  and a.resourceId=r.id and r.`code`='IS_ADMIN' and p.`code`='superAdmin'";
        List<Object> lis=  getSession().createSQLQuery(ad).list();


        StringBuffer hql = new StringBuffer();
        hql.append("from User u,Dope l where u.id=l.userId ");
        if (!StringUtils.isEmpty(bo.getName())) {
            hql.append(" and u.name like '%" + bo.getName() + "%'");
        }
        if (!StringUtils.isEmpty(bo.getOrgId())) {
            hql.append(" and u.orgId='" + bo.getOrgId() + "'");
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
        List<DopeVo> lists = new ArrayList();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Object[] o = (Object[]) iterator.next();
            User u = (User) o[0];
            UserVo v=new UserVo();
            BeanUtils.copyProperties(u,v);
            Dope l = (Dope) o[1];
            DopeVo vo = new DopeVo();
            ParameterContainer container = ParameterContainer.getInstance();
            v.setSex(container.getBusinessName(BaseParameter.SEX, u.getSex()));
            v.setNation(container.getBusinessName("BP_NATION", u.getNation()));
            BeanUtils.copyProperties(l,vo);
            if(vo.getRecord().equals("1")){
                vo.setRecord("否");
            }
            if(vo.getRecord().equals("2")){
                vo.setRecord("是");
            }
            BeanUtils.copyProperties(v,vo);
            vo.setUser(v);
            vo.setDope(l);
            lists.add(vo);
        }
        return lists;
    }

    @Override
    public Long getTotal(DopeBo bo) {
        StringBuffer hql = new StringBuffer();
        hql.append("from User u,Dope l where u.id=l.userId ");
        if (!StringUtils.isEmpty(bo.getName())) {
            hql.append(" and u.name like '%" + bo.getName() + "%'");
        }
        if (!StringUtils.isEmpty(bo.getOrgId())) {
            hql.append(" and u.orgId='" + bo.getOrgId() + "'");
        }
        hql.append(" order by l.createdDatetime desc");
        Query query = getSession().createQuery(hql.toString());
        List list = query.list();
        return (long) (list != null ? list.size() : 0);
    }


    @Override
    public void deleteById(String id) {
        getSession().createQuery("delete from " + Dope.class.getName() + " e where e.id=?")
                .setParameter(0, id)
                .executeUpdate();
    }

    @Override
    public void delete(Dope dope) {
        Assert.notNull(dope, "要删除的对象不能为空!");
        getSession().delete(dope);
    }

    @Override
    public DopeVo findById(String id) {
        Assert.hasText(id, "ID不能为空!");
        String hql="from User u,Dope l where u.id=l.userId and l.id=? ";
        Query query = getSession().createQuery(hql.toString()).setParameter(0,id);
        List list = query.list();
        Iterator iterator = list.iterator();
        DopeVo vo=new DopeVo();
        while (iterator.hasNext()) {
            Object[] o = (Object[]) iterator.next();
            User u = (User) o[0];
            UserVo v=new UserVo();
            BeanUtils.copyProperties(u,v);
            Dope l = (Dope) o[1];
            vo.setUser(v);
            vo.setDope(l);
        }
        return vo;
    }

    private void initCriteria(Criteria criteria, DopeBo bo) {
        Assert.notNull(criteria, "criteria must not be null!");
        CriteriaUtils.addCondition(criteria, bo);
    }

}