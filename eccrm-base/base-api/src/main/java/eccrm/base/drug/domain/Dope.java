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
@Table(name = "drug_dope")
@Api(value = "贩毒可疑人员")
public class Dope extends CommonDomain {

    @ApiField(value = "村民ID")
    @Column(length = 40)
    private String userId;


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

    @ApiField(value = "文件程度")
    @Column(length = 11)
    private String degree;

    @ApiField(value = " 备注")
    @Column
    private String context;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
