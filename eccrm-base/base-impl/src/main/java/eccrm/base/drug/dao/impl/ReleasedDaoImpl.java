package eccrm.base.drug.dao.impl;

import com.michael.base.common.BaseParameter;
import com.ycrl.core.pager.Pager;
import eccrm.base.drug.bo.ReleasedBo;
import eccrm.base.drug.domain.Prison;
import eccrm.base.drug.domain.Released;
import eccrm.base.drug.dao.ReleasedDao;
import com.ycrl.core.HibernateDaoHelper;
import eccrm.base.drug.domain.User;
import eccrm.base.drug.vo.PrisonVo;
import eccrm.base.drug.vo.ReleasedVo;
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
@Repository("releasedDao")
public class ReleasedDaoImpl extends HibernateDaoHelper implements ReleasedDao {

    @Override
    public String save(Released released) {
        return (String) getSession().save(released);
    }

    @Override
    public void update(Released released) {
        getSession().update(released);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ReleasedVo> query(ReleasedBo bo) {
        StringBuffer hql = new StringBuffer();
        hql.append("from User u,Released l where u.id=l.userId ");
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
        List<ReleasedVo> lists = new ArrayList();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Object[] o = (Object[]) iterator.next();
            User u = (User) o[0];
            UserVo v=new UserVo();
            BeanUtils.copyProperties(u,v);
            Released l = (Released) o[1];
            ReleasedVo vo = new ReleasedVo();
            ParameterContainer container = ParameterContainer.getInstance();
            v.setSex(container.getBusinessName(BaseParameter.SEX, u.getSex()));
            v.setNation(container.getBusinessName("BP_NATION", u.getNation()));
            vo.setUser(v);
            vo.setReleased(l);
            lists.add(vo);
        }
        return lists;
    }

    @Override
    public Long getTotal(ReleasedBo bo) {
        StringBuffer hql = new StringBuffer();
        hql.append("from User u,Released l where u.id=l.userId ");
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
        getSession().createQuery("delete from " + Released.class.getName() + " e where e.id=?")
                .setParameter(0, id)
                .executeUpdate();
    }

    @Override
    public void delete(Released released) {
        Assert.notNull(released, "要删除的对象不能为空!");
        getSession().delete(released);
    }

    @Override
    public ReleasedVo findById(String id) {
        Assert.hasText(id, "ID不能为空!");
        String hql="from User u,Released l where u.id=l.userId and l.id=? ";
        Query query = getSession().createQuery(hql.toString()).setParameter(0,id);
        List list = query.list();
        Iterator iterator = list.iterator();
        ReleasedVo vo=new ReleasedVo();
        while (iterator.hasNext()) {
            Object[] o = (Object[]) iterator.next();
            User u = (User) o[0];
            UserVo v=new UserVo();
            BeanUtils.copyProperties(u,v);
            Released l = (Released) o[1];
            vo.setUser(v);
            vo.setReleased(l);
        }
        return vo;
    }

    private void initCriteria(Criteria criteria, ReleasedBo bo) {
        Assert.notNull(criteria, "criteria must not be null!");
        CriteriaUtils.addCondition(criteria, bo);
    }

}