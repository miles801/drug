package eccrm.base.drug.vo;

import eccrm.base.drug.domain.Labor;
import eccrm.base.drug.domain.User;

/**
 * @author Rechried
 */
public class LaborVo extends Labor {
    private UserVo user;
    private Labor labor;

    public Labor getLabor() {
        return labor;
    }

    public void setLabor(Labor labor) {
        this.labor = labor;
    }

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }
}
