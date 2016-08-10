package eccrm.base.drug.dao.impl;

import eccrm.base.drug.bo.MaybeDrugBo;
import eccrm.base.drug.domain.MaybeDrug;
import eccrm.base.drug.dao.MaybeDrugDao;
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
    public List<MaybeDrug> query(MaybeDrugBo bo) {
        Criteria criteria = createCriteria(MaybeDrug.class);
        initCriteria(criteria, bo);
        return criteria.list();
    }

    @Override
    public Long getTotal(MaybeDrugBo bo) {
        Criteria criteria = createRowCountsCriteria(MaybeDrug.class);
        initCriteria(criteria, bo);
        return (Long) criteria.uniqueResult();
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
    public MaybeDrug findById(String id) {
        Assert.hasText(id, "ID不能为空!");
        return (MaybeDrug) getSession().get(MaybeDrug.class, id);
    }

    private void initCriteria(Criteria criteria, MaybeDrugBo bo) {
        Assert.notNull(criteria, "criteria must not be null!");
        CriteriaUtils.addCondition(criteria, bo);
    }

}