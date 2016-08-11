package eccrm.base.drug.vo;

import eccrm.base.drug.domain.Prison;

/**
 * @author Rechried
 */
public class PrisonVo extends Prison {
    private UserVo user;
    private Prison prison;

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }

    public Prison getPrison() {
        return prison;
    }

    public void setPrison(Prison prison) {
        this.prison = prison;
    }
}
