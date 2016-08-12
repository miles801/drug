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
}
