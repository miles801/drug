package eccrm.base.drug.domain;

import com.michael.docs.annotations.Api;
import com.michael.docs.annotations.ApiField;
import com.ycrl.base.common.CommonDomain;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Rechried
 */

@Entity
@Table(name = "drug_drug")
@Api(value = "涉毒人员")
public class Drug extends CommonDomain {
    @ApiField(value = "村民ID")
    @Column(length = 40)
    private String userId;

    @ApiField(value = "别名")
    @Column(length = 40)
    private String userName;

    @ApiField(value = "出生日期")
    @Column
    private Date brithDate;

    @ApiField(value = "人员现状")
    @Column(length = 40)
    private String userStatus;

    @ApiField(value = "居住地祥址")
    @Column(length = 100)
    private String address;

    @ApiField(value = "涉毒类型")
    @Column(length = 40)
    private String drugType;

    @ApiField(value = "涉毒种类")
    @Column(length = 40)
    private String drugSorts;

    @ApiField(value = "初次查获时间")
    @Column
    private Date firstTime;

    @ApiField(value = "查获地区")
    @Column(length = 50)
    private String drugArea;

    @ApiField(value = "查获单位")
    @Column(length = 50)
    private String drugCompany;

    @ApiField(value = "处置情况")
    @Column(length = 100)
    private String deal;

    @ApiField(value = "处置时间")
    @Column
    private Date dealDate;

    @ApiField(value = "查获地址")
    @Column(length = 50)
    private String drugAdress;

    @ApiField(value = "违法事实")
    @Column
    private String illegalFacts;

    @ApiField(value = "吸毒管控类型")
    @Column(length = 20)
    private String controlXType;

    @ApiField(value = "贩毒管控类型")
    @Column(length = 20)
    private String controlFType;

    @ApiField(value = "帮教ID")
    @Column(length = 40)
    private String helpId;

    @ApiField(value = "帮服情况")
    @Column(length = 50)
    private String helpStatus;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getBrithDate() {
        return brithDate;
    }

    public void setBrithDate(Date brithDate) {
        this.brithDate = brithDate;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDrugType() {
        return drugType;
    }

    public void setDrugType(String drugType) {
        this.drugType = drugType;
    }

    public String getDrugSorts() {
        return drugSorts;
    }

    public void setDrugSorts(String drugSorts) {
        this.drugSorts = drugSorts;
    }

    public Date getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(Date firstTime) {
        this.firstTime = firstTime;
    }

    public String getDrugArea() {
        return drugArea;
    }

    public void setDrugArea(String drugArea) {
        this.drugArea = drugArea;
    }

    public String getDrugCompany() {
        return drugCompany;
    }

    public void setDrugCompany(String drugCompany) {
        this.drugCompany = drugCompany;
    }

    public String getDeal() {
        return deal;
    }

    public void setDeal(String deal) {
        this.deal = deal;
    }

    public Date getDealDate() {
        return dealDate;
    }

    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }

    public String getDrugAdress() {
        return drugAdress;
    }

    public void setDrugAdress(String drugAdress) {
        this.drugAdress = drugAdress;
    }

    public String getIllegalFacts() {
        return illegalFacts;
    }

    public void setIllegalFacts(String illegalFacts) {
        this.illegalFacts = illegalFacts;
    }

    public String getControlXType() {
        return controlXType;
    }

    public void setControlXType(String controlXType) {
        this.controlXType = controlXType;
    }

    public String getControlFType() {
        return controlFType;
    }

    public void setControlFType(String controlFType) {
        this.controlFType = controlFType;
    }

    public String getHelpId() {
        return helpId;
    }

    public void setHelpId(String helpId) {
        this.helpId = helpId;
    }

    public String getHelpStatus() {
        return helpStatus;
    }

    public void setHelpStatus(String helpStatus) {
        this.helpStatus = helpStatus;
    }
}
