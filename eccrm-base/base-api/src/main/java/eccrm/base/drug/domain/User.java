package eccrm.base.drug.domain;

import com.michael.docs.annotations.Api;
import com.michael.docs.annotations.ApiField;
import com.ycrl.base.common.CommonDomain;
import eccrm.base.attachment.AttachmentSymbol;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Date;
/**
 * 
 * @author Rechried
 */

@Entity
@Table(name = "drug_user")
@Api(value = "村民基础数据")
public class User extends CommonDomain implements AttachmentSymbol {
    @ApiField(value = "姓名")
    @Column(length = 10)
    private String name;
    @ApiField(value = "头像")
    @Column
    private String icon;

    @ApiField(value = "性别")
    @Column(length = 10)
    private String sex;

    @ApiField(value = "组织ID")
    @Column(length = 40)
    private String orgId;

    @ApiField(value = "组织名称")
    @Column(length = 10)
    private String orgName;

    @ApiField(value = "民族")
    @Column(length = 10)
    private String nation;

    @ApiField(value = "身份证")
    @Column(length = 18)
    private String idCard;

    @ApiField(value = "家庭详细地址姓名")
    @Column
    private String home;

    @ApiField(value = "是否是户主")
    @Column
    private String isLeader;

    @ApiField(value = "是否外出务工")
    @Column
    private String isLabor;

    @ApiField(value = "是否吸毒可疑")
    @Column
    private String isXDrug;

    @ApiField(value = "是否贩毒可疑")
    @Column
    private String isFDrug;

    @ApiField(value = "是否服刑")
    @Column
    private String isPrison;
    @ApiField(value = "是否刑满释放")
    @Column
    private String isReleased;

    @ApiField(value = "联系方式")
    @Column(length = 11)
    private String phone;

    @ApiField(value = "文化程度")
    @Column(length = 11)
    private String degrees;

    @ApiField(value = "务工状况")
    @Column(length = 40)
    private String workStatus;

    @ApiField(value = "务工地点")
    @Column(length = 11)
    private String workAdress;

    @ApiField(value = "是否涉毒")
    @Column(length = 11)
    private String isDrugs;

    @ApiField(value = "与户主关系")
    @Column(length = 11)
    private String relation;


    @ApiField(value = " 备注")
    @Column
    private String context;

    @ApiField(value = "父节点")
    @Column
    private String isParent;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public String getIsParent() {
        return isParent;
    }

    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getDegrees() {
        return degrees;
    }

    public void setDegrees(String degrees) {
        this.degrees = degrees;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    public String getWorkAdress() {
        return workAdress;
    }

    public void setWorkAdress(String workAdress) {
        this.workAdress = workAdress;
    }

    public String getIsDrugs() {
        return isDrugs;
    }

    public void setIsDrugs(String isDrugs) {
        this.isDrugs = isDrugs;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getIsLeader() {
        return isLeader;
    }

    public void setIsLeader(String isLeader) {
        this.isLeader = isLeader;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String businessId() {
        return getId();
    }
}
