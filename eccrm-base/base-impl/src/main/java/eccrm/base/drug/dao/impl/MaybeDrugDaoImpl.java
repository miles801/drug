package eccrm.base.drug.dao.impl;

import com.michael.base.common.BaseParameter;
import com.ycrl.core.pager.Pager;
import eccrm.base.drug.bo.MaybeDrugBo;
import eccrm.base.drug.domain.Dope;
import eccrm.base.drug.domain.MaybeDrug;
import eccrm.base.drug.dao.MaybeDrugDao;
import com.ycrl.core.HibernateDaoHelper;
import eccrm.base.drug.domain.User;
import eccrm.base.drug.vo.DopeVo;
import eccrm.base.drug.vo.MaybeDrugVo;
import eccrm.base.drug.vo.UserVo;
import eccrm.base.parameter.service.ParameterContainer;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import com.ycrl.core.hibernate.criteria.CriteriaUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
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
@Repository("maybeDrugDao")
public class MaybeDrugDaoImpl extends HibernateDaoHelper implements MaybeDrugDao {

    @Override
    public String save(MaybeDrug maybeDrug) {
        return (String) getSession().save(maybeDrug);
    }

    @Override
    public void update(MaybeDrug maybeDrug) {
        getSession().update(maybeDrug);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<MaybeDrugVo> query(MaybeDrugBo bo) {
        StringBuffer hql = new StringBuffer();
        hql.append("from User u,MaybeDrug l where u.id=l.userId ");
        if (!StringUtils.isEmpty(bo.getName())) {
            hql.append(" and u.name like '%" + bo.getName() + "%'");
        }
        if (!StringUtils.isEmpty(bo.getOrgId())) {
            hql.append(" and u.orgId='" + bo.getOrgId() + "'");
        }
        hql.append(" order by l.createdDatetime desc");
        Query query = getSession().createQuery(hql.toString());
        if (!StringUtils.isEmpty(Pager.getStart())){
            query.setFirstResult(Pager.getStart());
            query.setMaxResults(Pager.getLimit());
        }
        List list = query.list();
        List<MaybeDrugVo> lists = new ArrayList();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Object[] o = (Object[]) iterator.next();
            User u = (User) o[0];
            UserVo v=new UserVo();
            BeanUtils.copyProperties(u,v);
            MaybeDrug l = (MaybeDrug) o[1];
            MaybeDrugVo vo = new MaybeDrugVo();
            ParameterContainer container = ParameterContainer.getInstance();
            v.setSex(container.getBusinessName(BaseParameter.SEX, u.getSex()));
            v.setNation(container.getBusinessName("BP_NATION", u.getNation()));
            vo.setUser(v);
            vo.setMaybe(l);
            lists.add(vo);
        }
        return lists;
    }

    @Override
    public Long getTotal(MaybeDrugBo bo) {
        StringBuffer hql = new StringBuffer();
        hql.append("from User u,MaybeDrug l where u.id=l.userId ");
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
        getSession().createQuery("delete from " + MaybeDrug.class.getName() + " e where e.id=?")
                .setParameter(0, id)
                .executeUpdate();
    }

    @Override
    public void delete(MaybeDrug maybeDrug) {
        Assert.notNull(maybeDrug, "要删除的对象不能为空!");
        getSession().delete(maybeDrug);
    }

    @Override
    public MaybeDrugVo findById(String id) {
        Assert.hasText(id, "ID不能为空!");
        String hql="from User u,MaybeDrug l where u.id=l.userId and l.id=? ";
        Query query = getSession().createQuery(hql.toString()).setParameter(0,id);
        List list = query.list();
        Iterator iterator = list.iterator();
        MaybeDrugVo vo=new MaybeDrugVo();
        while (iterator.hasNext()) {
            Object[] o = (Object[]) iterator.next();
            User u = (User) o[0];
            UserVo v=new UserVo();
            BeanUtils.copyProperties(u,v);
            MaybeDrug l = (MaybeDrug) o[1];
            vo.setUser(v);
            vo.setMaybe(l);
        }
        return vo;
    }

    private void initCriteria(Criteria criteria, MaybeDrugBo bo) {
        Assert.notNull(criteria, "criteria must not be null!");
        CriteriaUtils.addCondition(criteria, bo);
    }

}