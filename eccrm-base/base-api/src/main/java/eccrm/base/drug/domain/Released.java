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
@Table(name = "drug_released")
@Api(value = "刑满释放人员")
public class Released extends CommonDomain {

    @ApiField(value = "村民ID")
    @Column(length = 40)
    private String userId;

    @ApiField(value = "打击时间")
    @Column
    private Date inStartTime;

    @ApiField(value = "违法犯罪类型")
    @Column(length = 40)
    private String illegalType;

    @ApiField(value = "抓获地区")
    @Column(length = 40)
    private String area;

    @ApiField(value = "判刑年限")
    @Column(length = 3)
    private String prisonDate;

    @ApiField(value = "释放时间")
    @Column
    private Date releasedDate;

    @ApiField(value = "现从事职业")
    @Column(length = 40)
    private String nowWork;

    @ApiField(value = "现从事职业地点")
    @Column(length = 40)
    private String nowWorkDress;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getInStartTime() {
        return inStartTime;
    }

    public void setInStartTime(Date inStartTime) {
        this.inStartTime = inStartTime;
    }

    public String getIllegalType() {
        return illegalType;
    }

    public void setIllegalType(String illegalType) {
        this.illegalType = illegalType;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPrisonDate() {
        return prisonDate;
    }

    public void setPrisonDate(String prisonDate) {
        this.prisonDate = prisonDate;
    }

    public Date getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(Date releasedDate) {
        this.releasedDate = releasedDate;
    }

    public String getNowWork() {
        return nowWork;
    }

    public void setNowWork(String nowWork) {
        this.nowWork = nowWork;
    }

    public String getNowWorkDress() {
        return nowWorkDress;
    }

    public void setNowWorkDress(String nowWorkDress) {
        this.nowWorkDress = nowWorkDress;
    }
}
