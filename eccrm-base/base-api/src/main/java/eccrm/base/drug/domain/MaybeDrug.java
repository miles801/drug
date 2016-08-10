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
@Table(name = "drug_maybe_drug")
@Api(value = "吸毒可疑人员信息")
public class MaybeDrug extends CommonDomain {

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

    @ApiField(value = "是否有前科")
    @Column(length = 10)
    private String record;

    @ApiField(value = "涉及罪名或违法行为")
    @Column(length = 100)
    private String illegal;

    @ApiField(value = "主要活动区域")
    @Column(length = 100)
    private String area;

    @ApiField(value = "认定嫌疑理由")
    @Column
    private String reason;

    @ApiField(value = "主要经济来源")
    @Column(length = 20)
    private String income;

    @ApiField(value = "文件程度")
    @Column(length = 11)
    private String degree;

    @ApiField(value = " 备注")
    @Column
    private String context;

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

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getIllegal() {
        return illegal;
    }

    public void setIllegal(String illegal) {
        this.illegal = illegal;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
