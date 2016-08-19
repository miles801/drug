package eccrm.base.drug.dao.impl;

import com.michael.base.common.BaseParameter;
import com.ycrl.core.HibernateDaoHelper;
import com.ycrl.core.context.SecurityContext;
import com.ycrl.core.hibernate.criteria.CriteriaUtils;
import com.ycrl.core.pager.Pager;
import eccrm.base.drug.bo.DrugBo;
import eccrm.base.drug.dao.DrugDao;
import eccrm.base.drug.domain.Drug;
import eccrm.base.drug.domain.DrugHelp;
import eccrm.base.drug.domain.User;
import eccrm.base.drug.vo.DrugVo;
import eccrm.base.drug.vo.UserVo;
import eccrm.base.parameter.service.ParameterContainer;
import org.hibernate.Criteria;
import org.hibernate.Query;
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
@Repository("drugDao")
public class DrugDaoImpl extends HibernateDaoHelper implements DrugDao {

    @Override
    public String save(Drug drug) {
        return (String) getSession().save(drug);
    }

    @Override
    public void update(Drug drug) {
        getSession().update(drug);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DrugVo> query(DrugBo bo) {
        String orgId= SecurityContext.getOrgId();
        String ad="SELECT a.id FROM `sys_position_resource` a ,sys_position p,sys_resource r ,sys_position_emp e " +
                " where e.positionId=p.id and e.empId='"+SecurityContext.getEmpId()+"' " +
                "and a.positionId=p.id  and a.resourceId=r.id and r.`code`='IS_ADMIN' and p.`code`='superAdmin'";
        List<Object> lis=  getSession().createSQLQuery(ad).list();

        StringBuffer hql = new StringBuffer();
        hql.append("from User u,Drug l where u.id=l.userId ");
        if (!StringUtils.isEmpty(bo.getName())) {
            hql.append(" and u.name like '%" + bo.getName() + "%'");
        }
        if (!StringUtils.isEmpty(bo.getOrgId())) {
            hql.append(" and u.orgId='" + bo.getOrgId() + "'");
        }
        if (!StringUtils.isEmpty(bo.getDrugType())) {
            hql.append(" and l.drugType='" + bo.getDrugType() + "'");
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
        List<DrugVo> lists = new ArrayList();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Object[] o = (Object[]) iterator.next();
            User u = (User) o[0];
            UserVo v=new UserVo();
            BeanUtils.copyProperties(u,v);
            Drug l = (Drug) o[1];
            DrugVo drugVo=new DrugVo();
            BeanUtils.copyProperties(l,drugVo);
            DrugVo vo = new DrugVo();
            ParameterContainer container = ParameterContainer.getInstance();
            v.setSex(container.getBusinessName(BaseParameter.SEX, u.getSex()));
            v.setNation(container.getBusinessName("BP_NATION", u.getNation()));
            drugVo.setDrugType(container.getBusinessName("DRUG_TYPE", l.getDrugType()));
            drugVo.setDrugSorts(container.getBusinessName("DRUG_SORT", l.getDrugSorts()));
            drugVo.setControlXType(container.getBusinessName("CONTROL_XTYPE", l.getControlXType()));
            drugVo.setControlFType(container.getBusinessName("CONTROL_FTYPE", l.getControlFType()));
            BeanUtils.copyProperties(drugVo,vo);
            BeanUtils.copyProperties(v,vo);
            vo.setUser(v);
            vo.setDrug(drugVo);
            lists.add(vo);
        }
        return lists;
    }

    @Override
    public Long getTotal(DrugBo bo) {
        String orgId= SecurityContext.getOrgId();
        String ad="SELECT a.id FROM `sys_position_resource` a ,sys_position p,sys_resource r ,sys_position_emp e " +
                " where e.positionId=p.id and e.empId='"+SecurityContext.getEmpId()+"' " +
                "and a.positionId=p.id  and a.resourceId=r.id and r.`code`='IS_ADMIN' and p.`code`='superAdmin'";
        List<Object> lis=  getSession().createSQLQuery(ad).list();

        StringBuffer hql = new StringBuffer();
        hql.append("from User u,Drug l where u.id=l.userId ");
        if (!StringUtils.isEmpty(bo.getName())) {
            hql.append(" and u.name like '%" + bo.getName() + "%'");
        }
        if (!StringUtils.isEmpty(bo.getOrgId())) {
            hql.append(" and u.orgId='" + bo.getOrgId() + "'");
        }
        if (!StringUtils.isEmpty(bo.getDrugType())) {
            hql.append(" and l.drugType='" + bo.getDrugType() + "'");
        }
        if(lis!=null&&lis.size()==0){
            String sql="SELECT id FROM `sys_org` where id='"+orgId+"' or parentId='"+orgId+"'";
            List<Object> o=  getSession().createSQLQuery(sql).list();
            hql.append(" and u.orgId in (" + ListToStringUtil.listToString(o) + ")");
        }
        hql.append(" order by l.createdDatetime desc");
        Query query = getSession().createQuery(hql.toString());
        List list = query.list();
        return (long) (list != null ? list.size() : 0);
    }


    @Override
    public void deleteById(String id) {
        getSession().createQuery("delete from " + Drug.class.getName() + " e where e.id=?")
                .setParameter(0, id)
                .executeUpdate();
    }

    @Override
    public void delete(Drug drug) {
        Assert.notNull(drug, "要删除的对象不能为空!");
        getSession().delete(drug);
    }

    @Override
    public DrugVo findById(String id) {
        Assert.hasText(id, "ID不能为空!");
        String hql="from User u,Drug l where u.id=l.userId and l.id=? ";
        Query query = getSession().createQuery(hql.toString()).setParameter(0,id);
        List list = query.list();
        Iterator iterator = list.iterator();
        DrugVo vo=new DrugVo();
        while (iterator.hasNext()) {
            Object[] o = (Object[]) iterator.next();
            User u = (User) o[0];
            UserVo v=new UserVo();
            BeanUtils.copyProperties(u,v);
            Drug l = (Drug) o[1];
            vo.setUser(v);
            vo.setDrug(l);
        }
        String sql="from Drug d,DrugHelp p where d.id=p.userId and d.id=?";
        Query query2 = getSession().createQuery(sql.toString()).setParameter(0,id);
        List list2 = query2.list();
        List<DrugHelp> vos=new ArrayList<>();
        Iterator iterator2 = list2.iterator();
        while (iterator2.hasNext()) {
            Object[] o = (Object[]) iterator2.next();
            DrugHelp l = (DrugHelp) o[1];
            vos.add(l);
        }
        vo.setDrugs(vos);
        return vo;
    }

    private void initCriteria(Criteria criteria, DrugBo bo) {
        Assert.notNull(criteria, "criteria must not be null!");
        CriteriaUtils.addCondition(criteria, bo);
    }

}