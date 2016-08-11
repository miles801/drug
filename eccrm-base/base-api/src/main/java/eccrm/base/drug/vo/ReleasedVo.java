package eccrm.base.drug.vo;

import eccrm.base.drug.domain.Released;

/**
 * @author Rechried
 */
public class ReleasedVo extends Released {
    private UserVo user;
    private Released released;

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }

    public Released getReleased() {
        return released;
    }

    public void setReleased(Released released) {
        this.released = released;
    }
}
