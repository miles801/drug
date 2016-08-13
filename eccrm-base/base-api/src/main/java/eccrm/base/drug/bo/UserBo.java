package eccrm.base.drug.bo;

import com.michael.docs.annotations.ApiField;
import com.ycrl.core.hibernate.criteria.BO;
import com.ycrl.core.hibernate.criteria.Condition;
import com.ycrl.core.hibernate.criteria.LikeModel;
import com.ycrl.core.hibernate.criteria.MatchModel;

import javax.persistence.Column;

/**
 * @author Rechried
 */
public class UserBo implements BO{
    @Condition
    @ApiField(value = "所属组织")
    private String orgId;
    @Condition
    @ApiField()
    private String id;

    @Condition
    @ApiField(value = "是否是户主")
    private String isLeader;

    @Condition
    @ApiField(value = "是否外出务工")
    private String isLabor;

    @ApiField(value = "是否吸毒可疑")
    @Condition
    private String isXDrug;
    @Condition
    @ApiField(value = "是否贩毒可疑")
    private String isFDrug;
    @Condition
    @ApiField(value = "是否服刑")
    private String isPrison;
    @Condition
    @ApiField(value = "是否刑满释放")
    private String isReleased;
    @Condition
    @ApiField(value = "是否涉毒")
    private String isDrugs;


    @Condition(matchMode = MatchModel.LIKE, likeMode = LikeModel.ANYWHERE)
    @ApiField(value = "姓名", desc = "like")
    private String name;

    public String getIsLeader() {
        return isLeader;
    }

    public void setIsLeader(String isLeader) {
        this.isLeader = isLeader;
    }

    public String getIsLabor() {
        return isLabor;
    }

    public void setIsLabor(String isLabor) {
        this.isLabor = isLabor;
    }

    public String getIsXDrug() {
        return isXDrug;
    }

    public void setIsXDrug(String isXDrug) {
        this.isXDrug = isXDrug;
    }

    public String getIsFDrug() {
        return isFDrug;
    }

    public void setIsFDrug(String isFDrug) {
        this.isFDrug = isFDrug;
    }

    public String getIsPrison() {
        return isPrison;
    }

    public void setIsPrison(String isPrison) {
        this.isPrison = isPrison;
    }

    public String getIsReleased() {
        return isReleased;
    }

    public void setIsReleased(String isReleased) {
        this.isReleased = isReleased;
    }

    public String getIsDrugs() {
        return isDrugs;
    }

    public void setIsDrugs(String isDrugs) {
        this.isDrugs = isDrugs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
