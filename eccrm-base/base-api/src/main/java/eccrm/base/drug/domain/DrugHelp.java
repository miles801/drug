package eccrm.base.drug.domain;

import com.michael.docs.annotations.Api;
import com.michael.docs.annotations.ApiField;
import com.ycrl.base.common.CommonDomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by wo on 2016/8/11.
 */
@Entity
@Table(name = "drug_drug_help")
@Api(value = "帮教详情")
public class DrugHelp extends CommonDomain {
    @ApiField(value = "村民ID")
    @Column(length = 40)
    private String userId;

    @ApiField(value = "帮教人")
    @Column(length = 40)
    private String helpName;

    @ApiField(value = "单位名称")
    @Column(length = 40)
    private String helpCopmany;

    @ApiField(value = "职位")
    @Column(length = 10)
    private String helpPosition;

    @ApiField(value = "联系方式")
    @Column(length = 11)
    private String helpPhone;

    @ApiField(value = "帮教时间")
    @Column
    private Date helpDate;

    @ApiField(value = "帮教情况")
    @Column
    private String helpStatus;

    @ApiField(value = "帮教备注")
    @Column
    private String context;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHelpName() {
        return helpName;
    }

    public void setHelpName(String helpName) {
        this.helpName = helpName;
    }

    public String getHelpCopmany() {
        return helpCopmany;
    }

    public void setHelpCopmany(String helpCopmany) {
        this.helpCopmany = helpCopmany;
    }

    public String getHelpPosition() {
        return helpPosition;
    }

    public void setHelpPosition(String helpPosition) {
        this.helpPosition = helpPosition;
    }

    public String getHelpPhone() {
        return helpPhone;
    }

    public void setHelpPhone(String helpPhone) {
        this.helpPhone = helpPhone;
    }

    public Date getHelpDate() {
        return helpDate;
    }

    public void setHelpDate(Date helpDate) {
        this.helpDate = helpDate;
    }

    public String getHelpStatus() {
        return helpStatus;
    }

    public void setHelpStatus(String helpStatus) {
        this.helpStatus = helpStatus;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
