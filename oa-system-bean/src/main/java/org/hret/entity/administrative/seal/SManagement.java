package org.hret.entity.administrative.seal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hret.entity.utils.query.Page;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.io.Serializable;

/**
 * (SManagement)实体类
 *
 * @author makejava
 * @since 2024-04-15 09:53:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class SManagement extends Page implements Serializable {
    private static final long serialVersionUID = 422825294053631267L;
/**
     * 工号
     */
@TableId(value = "s_id", type = IdType.AUTO)
    private Integer sId;
/**
     * 用章流水号
     */
    private Integer serial;
/**
     * 用章人
     */
    private String sPeople;
/**
     * 印章名称
     */
    private String sName;
/**
     * 归还人
     */
    private String returnPerson;
/**
     * 手机号
     */
    private Integer phone;
/**
     * 状态
     */
    private Integer state;
/**
     * 用章类型
     */
    private String sType;
/**
     * 盖章文件份数
     */
    private Integer stamp;
/**
     * 盖章文件
     */
    private String sFile;
/**
     * 用章说明
     */
    private String sIllustrate;
/**
     * 邮箱
     */
    private String mail;
/**
     * 备注
     */
    private String remark;
/**
     * 申请日期
     */
    private Date applicationTime;
/**
     * 提交时间
     */
    private Date submitTime;
/**
     * 归还日期
     */
    private Date returnTime;


    public Integer getSId() {
        return sId;
    }

    public void setSId(Integer sId) {
        this.sId = sId;
    }

    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    public String getSPeople() {
        return sPeople;
    }

    public void setSPeople(String sPeople) {
        this.sPeople = sPeople;
    }

    public String getSName() {
        return sName;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

    public String getReturnPerson() {
        return returnPerson;
    }

    public void setReturnPerson(String returnPerson) {
        this.returnPerson = returnPerson;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getSType() {
        return sType;
    }

    public void setSType(String sType) {
        this.sType = sType;
    }

    public Integer getStamp() {
        return stamp;
    }

    public void setStamp(Integer stamp) {
        this.stamp = stamp;
    }

    public String getSFile() {
        return sFile;
    }

    public void setSFile(String sFile) {
        this.sFile = sFile;
    }

    public String getSIllustrate() {
        return sIllustrate;
    }

    public void setSIllustrate(String sIllustrate) {
        this.sIllustrate = sIllustrate;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(Date applicationTime) {
        this.applicationTime = applicationTime;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

}

