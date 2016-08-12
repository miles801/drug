package eccrm.base.drug.vo;

import eccrm.base.drug.domain.Drug;

/**
 * @author Rechried
 */
public class DrugVo extends Drug {
    private UserVo user;
    private Drug drug;
    private String name;
    private String sex;
    private String orgId;
    private String orgName;
    private String nation;
    private String idCard;
    private String home;
    private String phone;
    private String drugTypes;
    private String drugSortss;
    private String controlXTypes;
    private String controlFTypes;

    public String getDrugTypes() {
        return drugTypes;
    }

    public void setDrugTypes(String drugTypes) {
        this.drugTypes = drugTypes;
    }

    public String getDrugSortss() {
        return drugSortss;
    }

    public void setDrugSortss(String drugSortss) {
        this.drugSortss = drugSortss;
    }

    public String getControlXTypes() {
        return controlXTypes;
    }

    public void setControlXTypes(String controlXTypes) {
        this.controlXTypes = controlXTypes;
    }

    public String getControlFTypes() {
        return controlFTypes;
    }

    public void setControlFTypes(String controlFTypes) {
        this.controlFTypes = controlFTypes;
    }

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
