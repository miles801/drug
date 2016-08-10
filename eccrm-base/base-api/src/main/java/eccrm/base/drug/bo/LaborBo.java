package eccrm.base.drug.bo;

import com.michael.docs.annotations.ApiField;
import com.ycrl.core.hibernate.criteria.BO;
import com.ycrl.core.hibernate.criteria.Condition;
import com.ycrl.core.hibernate.criteria.LikeModel;
import com.ycrl.core.hibernate.criteria.MatchModel;

/**
 * @author Rechried
 */
public class LaborBo implements BO{
    @Condition(matchMode = MatchModel.LIKE, likeMode = LikeModel.ANYWHERE)
    @ApiField(value = "姓名", desc = "like")
    private String laborName;

    public String getLaborName() {
        return laborName;
    }

    public void setLaborName(String laborName) {
        this.laborName = laborName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Condition
    @ApiField(value = "联系方式")
    private String phone;

    @Condition
    @ApiField(value = "所属组织")
    private String orgId;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
