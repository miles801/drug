package eccrm.base.drug.vo;

import eccrm.base.drug.domain.MaybeDrug;

/**
 * @author Rechried
 */
public class MaybeDrugVo extends MaybeDrug {
    private UserVo user;
    private MaybeDrug maybe;

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }

    public MaybeDrug getMaybe() {
        return maybe;
    }

    public void setMaybe(MaybeDrug maybe) {
        this.maybe = maybe;
    }
}
