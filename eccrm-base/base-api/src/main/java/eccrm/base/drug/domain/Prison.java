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
@Table(name = "drug_prison")
@Api(value = "服刑人员记录")
public class Prison extends CommonDomain {
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

    @ApiField(value = "服刑监管地点")
    @Column(length = 40)
    private String prisonDress;

    @ApiField(value = "备注")
    @Column
    private String context;

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

    public String getPrisonDress() {
        return prisonDress;
    }

    public void setPrisonDress(String prisonDress) {
        this.prisonDress = prisonDress;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
