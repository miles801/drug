package eccrm.base.drug.dao.impl;

import eccrm.base.drug.bo.LaborBo;
import eccrm.base.drug.domain.Labor;
import eccrm.base.drug.dao.LaborDao;
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
    public List<Labor> query(LaborBo bo) {
        Criteria criteria = createCriteria(Labor.class);
        initCriteria(criteria, bo);
        return criteria.list();
    }

    @Override
    public Long getTotal(LaborBo bo) {
        Criteria criteria = createRowCountsCriteria(Labor.class);
        initCriteria(criteria, bo);
        return (Long) criteria.uniqueResult();
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
    public Labor findById(String id) {
        Assert.hasText(id, "ID不能为空!");
        return (Labor) getSession().get(Labor.class, id);
    }

    private void initCriteria(Criteria criteria, LaborBo bo) {
        Assert.notNull(criteria, "criteria must not be null!");
        CriteriaUtils.addCondition(criteria, bo);
    }

}