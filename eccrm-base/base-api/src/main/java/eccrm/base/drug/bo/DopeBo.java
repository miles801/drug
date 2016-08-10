package eccrm.base.drug.bo;

import com.michael.docs.annotations.ApiField;
import com.ycrl.core.hibernate.criteria.BO;
import com.ycrl.core.hibernate.criteria.Condition;
import com.ycrl.core.hibernate.criteria.LikeModel;
import com.ycrl.core.hibernate.criteria.MatchModel;

/**
 * @author Rechried
 */
public class DopeBo implements BO{
    @Condition
    @ApiField(value = "所属组织")
    private String orgId;

    @Condition(matchMode = MatchModel.LIKE, likeMode = LikeModel.ANYWHERE)
    @ApiField(value = "姓名", desc = "like")
    private String name;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
