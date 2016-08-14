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
    @Column(length = 40)
    private String userId;
    @Column
    private String workArea;// 工作区域
    @Column
    private String workDress;// 务工地
    @Column(length = 20)
    private String position;// 从事职业
    @Column(length = 10)
    private String isDrug;// 是否涉毒等级人员
    @Column
    private String context;// 备注

    public String getWorkArea() {
        return workArea;
    }

    public void setWorkArea(String workArea) {
        this.workArea = workArea;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
