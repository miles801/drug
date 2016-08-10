package eccrm.base.drug.domain;

import com.ycrl.base.common.CommonDomain;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 外出务工人员信息表
 *
 * @author Rechried
 */

@Entity
@Table(name = "drug_labor")
public class Labor extends CommonDomain {
    @Column(length = 10)
    private String laborName;// 姓名
    @Column(length = 10)
    private String sex;// 性别
    @Column(length = 40)
    private String orgId;// 组织ID
    @Column(length = 10)
    private String orgName;// 组织名称
    @Column(length = 10)
    private String nation;// 民族
    @Column(length = 18)
    private String idCard;// 身份证
    @Column
    private String home;// 家庭详细地址
    @Column
    private String workDress;// 务工地
    @Column(length = 20)
    private String position;// 从事职业
    @Column(length = 10)
    private String isDrug;// 是否涉毒等级人员
    @Column(length = 11)
    private String phone;// 联系方式
    @Column
    private String context;// 备注

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

    public String getLaborName() {
        return laborName;
    }

    public void setLaborName(String laborName) {
        this.laborName = laborName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getWorkDress() {
        return workDress;
    }

    public void setWorkDress(String workDress) {
        this.workDress = workDress;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getIsDrug() {
        return isDrug;
    }

    public void setIsDrug(String isDrug) {
        this.isDrug = isDrug;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
