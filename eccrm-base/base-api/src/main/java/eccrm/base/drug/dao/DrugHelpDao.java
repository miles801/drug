package eccrm.base.drug.dao;

import eccrm.base.drug.domain.DrugHelp;

/**
 * Created by wo on 2016/8/11.
 */
public interface DrugHelpDao {
    void saveOrUpdate(DrugHelp drugHelp);

    void delete(String id);

    void save(DrugHelp a);

    void update(DrugHelp a);
}
