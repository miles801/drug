package eccrm.base.drug.domain;

import com.ycrl.base.common.CommonDomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by wo on 2016/8/14.
 */
@Entity
@Table(name = "drug_all_drug")
public class AllDrug extends CommonDomain{
    @Column
    private String orgName;
    @Column
    private String coutParent;// 总户数
    @Column
    private String countPeople;// 已入户
    @Column
    private String outSheng;//
    @Column
    private String inSheng;
    @Column
    private String inZhou;
    @Column
    private String inXian;
    @Column
    private String fDrug;
    @Column
    private String xDrug;
    @Column
    private String prison;
    @Column
    private String released;
    @Column
    private String jiedu;
    @Column
    private String prisoning;
    @Column
    private String yaowu;
    @Column
    private String jiya;
    @Column
    private String jieduan;
    @Column
    private String shequjiedu;
    @Column
    private String shequkangfu;
    @Column
    private String outOfControl;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getCoutParent() {
        return coutParent;
    }

    public void setCoutParent(String coutParent) {
        this.coutParent = coutParent;
    }

    public String getCountPeople() {
        return countPeople;
    }

    public void setCountPeople(String countPeople) {
        this.countPeople = countPeople;
    }

    public String getOutSheng() {
        return outSheng;
    }

    public void setOutSheng(String outSheng) {
        this.outSheng = outSheng;
    }

    public String getInSheng() {
        return inSheng;
    }

    public void setInSheng(String inSheng) {
        this.inSheng = inSheng;
    }

    public String getInZhou() {
        return inZhou;
    }

    public void setInZhou(String inZhou) {
        this.inZhou = inZhou;
    }

    public String getInXian() {
        return inXian;
    }

    public void setInXian(String inXian) {
        this.inXian = inXian;
    }

    public String getfDrug() {
        return fDrug;
    }

    public void setfDrug(String fDrug) {
        this.fDrug = fDrug;
    }

    public String getxDrug() {
        return xDrug;
    }

    public void setxDrug(String xDrug) {
        this.xDrug = xDrug;
    }

    public String getPrison() {
        return prison;
    }

    public void setPrison(String prison) {
        this.prison = prison;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getJiedu() {
        return jiedu;
    }

    public void setJiedu(String jiedu) {
        this.jiedu = jiedu;
    }

    public String getPrisoning() {
        return prisoning;
    }

    public void setPrisoning(String prisoning) {
        this.prisoning = prisoning;
    }

    public String getYaowu() {
        return yaowu;
    }

    public void setYaowu(String yaowu) {
        this.yaowu = yaowu;
    }

    public String getJiya() {
        return jiya;
    }

    public void setJiya(String jiya) {
        this.jiya = jiya;
    }

    public String getJieduan() {
        return jieduan;
    }

    public void setJieduan(String jieduan) {
        this.jieduan = jieduan;
    }

    public String getShequjiedu() {
        return shequjiedu;
    }

    public void setShequjiedu(String shequjiedu) {
        this.shequjiedu = shequjiedu;
    }

    public String getShequkangfu() {
        return shequkangfu;
    }

    public void setShequkangfu(String shequkangfu) {
        this.shequkangfu = shequkangfu;
    }

    public String getOutOfControl() {
        return outOfControl;
    }

    public void setOutOfControl(String outOfControl) {
        this.outOfControl = outOfControl;
    }
}
