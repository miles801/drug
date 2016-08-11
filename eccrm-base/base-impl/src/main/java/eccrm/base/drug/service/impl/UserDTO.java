package eccrm.base.drug.service.impl;

import com.michael.poi.annotation.Col;
import com.michael.poi.annotation.ImportConfig;
import com.michael.poi.core.DTO;


/**
 * Created by wo on 2016/8/11.
 */
@ImportConfig(file = "", startRow = 1,sheets = 0)
public class UserDTO implements DTO {
    @Col(index = 1)
    private String name;
    @Col(index = 2)
    private String sex;
    @Col(index = 3)
    private String nation;
    @Col(index = 4)
    private String idCard;
    @Col(index = 5)
    private String home;
    @Col(index = 6)
    private String isLeader;
    @Col(index = 7)
    private String phone;
    @Col(index = 8)
    private String context;

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

    public String getIsLeader() {
        return isLeader;
    }

    public void setIsLeader(String isLeader) {
        this.isLeader = isLeader;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
