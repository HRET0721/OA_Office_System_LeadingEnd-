package org.hret.entity.personnel.leave;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hret.entity.utils.query.Page;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@TableName(value = "seal_management")
public class SealManagement extends Page implements Serializable {
    @Serial
    private static final long serialVersionUID = -49452775890884099L;
    /**
     * 用章流水号
     */
    @TableId(value = "seal_id", type = IdType.AUTO)
    private Integer sealId;

    /**
     * 用章人
     */
    private String sealName;

    /**
     * 用章名称
     */
    private String sealTitle;

    /**
     * 申请日期
     */
    private Date dateOfPplication;

    /**
     * 用章状态(0(审批中)1(已同意)2(已拒绝)3(已撤销))印章状态(4(审批中)5(已同意)6(已拒绝)7(已撤销))
     */
    private String state;

    /**
     * 提交时间
     */
    private Date submissionTime;

    /**
     * 用章类型(0(外带)1(公司印章))
     */
    private String sealType;

    /**
     * 选择印章(1(法人章)2(合同章)3(项目章)4(财务章))
     */
    private String sealChoosesss;

    /**
     * 盖章文件份数
     */
    private String sealNumberss;

    /**
     * 盖章文件
     */
    private String stampedDocuments;

    /**
     * 印章说明
     */
    private String sealIllustrate;

    /**
     * 归还日期
     */
    private Date returnDate;
}
