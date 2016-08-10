package eccrm.base.drug.dao.impl;

import eccrm.base.drug.bo.DopeBo;
import eccrm.base.drug.domain.Dope;
import eccrm.base.drug.dao.DopeDao;
import com.ycrl.core.HibernateDaoHelper;
import org.hibernate.criterion.Example;
import com.ycrl.core.hibernate.criteria.CriteriaUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
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
    public List<Dope> query(DopeBo bo) {
        Criteria criteria = createCriteria(Dope.class);
        initCriteria(criteria, bo);
        return criteria.list();
    }

    @Override
    public Long getTotal(DopeBo bo) {
        Criteria criteria = createRowCountsCriteria(Dope.class);
        initCriteria(criteria, bo);
        return (Long) criteria.uniqueResult();
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
    public Dope findById(String id) {
        Assert.hasText(id, "ID不能为空!");
        return (Dope) getSession().get(Dope.class, id);
    }

    private void initCriteria(Criteria criteria, DopeBo bo) {
        Assert.notNull(criteria, "criteria must not be null!");
        CriteriaUtils.addCondition(criteria, bo);
    }

}