package eccrm.base.drug.domain;

import com.michael.docs.annotations.Api;
import com.michael.docs.annotations.ApiField;
import com.ycrl.base.common.CommonDomain;
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
public class User extends CommonDomain {
    @ApiField(value = "姓名")
    @Column(length = 10)
    private String name;

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

    @ApiField(value = "联系方式")
    @Column
    private String phone;

    @ApiField(value = " 备注")
    @Column
    private String context;

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
}
