package eccrm.base.drug.dao.impl;

import com.ycrl.core.HibernateDaoHelper;
import eccrm.base.drug.dao.DrugHelpDao;
import eccrm.base.drug.domain.DrugHelp;
import org.springframework.stereotype.Repository;

/**
 * Created by wo on 2016/8/11.
 */
@Repository("drugHelpDao")
public class DrugHelpDaoImpl extends HibernateDaoHelper implements DrugHelpDao {
    @Override
    public void saveOrUpdate(DrugHelp drugHelp) {
        getSession().saveOrUpdate(drugHelp);
    }

    @Override
    public void delete(String id) {
        String sql="delete from "+DrugHelp.class.getName()+" d where d.userId=?";
        getSession().createQuery(sql).setParameter(0,id).executeUpdate();
    }

    @Override
    public void save(DrugHelp a) {
        getSession().save(a);
    }

    @Override
    public void update(DrugHelp a) {
        getSession().update(a);
    }
}
