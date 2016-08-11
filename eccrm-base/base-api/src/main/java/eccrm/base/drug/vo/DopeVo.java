package eccrm.base.drug.vo;

import eccrm.base.drug.domain.Dope;

/**
 * @author Rechried
 */
public class DopeVo extends Dope {
    private UserVo user;
    private Dope dope;

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }

    public Dope getDope() {
        return dope;
    }

    public void setDope(Dope dope) {
        this.dope = dope;
    }
}
