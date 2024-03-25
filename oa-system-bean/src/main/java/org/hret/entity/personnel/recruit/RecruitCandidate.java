package org.hret.entity.personnel.recruit;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.hret.entity.utils.query.Page;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.util.Date;
import java.io.Serializable;

/**
 * 招聘-候选人(RecruitCandidate)表实体类
 *
 * @author makejava
 * @since 2024-03-21 16:11:59
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("recruit_candidate")
@ApiModel("候选人实体类")
public class RecruitCandidate extends Page implements Serializable {
    @Serial
    private static final long serialVersionUID = -2028512447875688222L;

    //招聘主键id
    @TableId(type = IdType.AUTO)
    @Excel(name = "候选人id", width = 20, isImportField = "true")
    private Integer candidateId;

    //职位关联id
    @Excel(name = "职位关联id", width = 20, isImportField = "true")
    private Integer jobId;

    @Excel(name = "职位名称", width = 20, isImportField = "true")
    //流程(1.等待面试，2.面试中，3.面试通过)
    private String candidateStatus;

    @Excel(name = "姓名", width = 20, isImportField = "true")
    //姓名
    private String candidateName;

    @Excel(name = "性别", width = 20, isImportField = "true")
    //性别
    private String candidateSex;

    @Excel(name = "手机号", width = 20, isImportField = "true")
    //手机号
    private String candidatePhoneNumber;

    @Excel(name = "学历", width = 20, isImportField = "true")
    //学历要求(1.博士及以上2.硕士3.本科4.大专5.高中6.不限)
    private String candidateEducation;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Excel(name = "出生日期", width = 20, isImportField = "true")
    //出生日期
    private Date candidateDateBirth;

    @Excel(name = "邮箱", width = 20, isImportField = "true")
    //邮箱
    private String candidateMailbox;

    @Excel(name = "民族", width = 20, isImportField = "true")
    //民族
    private String candidateNation;

    @Excel(name = "婚姻状况", width = 20, isImportField = "true")
    //婚姻状况
    private String candidateMarriage;

    @Excel(name = "籍贯", width = 20, isImportField = "true")
    //籍贯
    private String candidateBirthplace;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Excel(name = "工作时间", width = 20, isImportField = "true")
    //工作时间
    private Date candidateWorkDate;

    @Excel(name = "优势", width = 20, isImportField = "true")
    //优点
    private String candidateMerit;

    @Excel(name = "期望职位", width = 20, isImportField = "true")
    //期待职位
    private String candidateExpect;

    @Excel(name = "期望薪资", width = 20, isImportField = "true")
    //期望薪资
    private String candidateExpectPay;

    @Excel(name = "工作地点", width = 20, isImportField = "true")
    //工作地点
    private String candidateWorkAddres;

    @Excel(name = "工作性质", width = 20, isImportField = "true")
    //工作性质（1.全职 2.兼职3.实习4.外派5.退休）
    private String candidateNature;

    @Excel(name = "到岗期限", width = 20, isImportField = "true")
    //到岗期限
    private String candidateExpire;

    @Excel(name = "工作经历", width = 20, isImportField = "true")
    //工作经历
    private String superiority1;

    @Excel(name = "项目经验", width = 20, isImportField = "true")
    //项目经验
    private String superiority2;

    @Excel(name = "培训经历", width = 20, isImportField = "true")
    //培训经历
    private String superiority3;

    @Excel(name = "外语能力", width = 20, isImportField = "true")
    //外语能力
    private String superiority4;

    @Excel(name = "获奖经历", width = 20, isImportField = "true")
    //获奖经历
    private String superiority5;

    @Excel(name = "社交经历", width = 20, isImportField = "true")
    //社交经历
    private String superiority6;

    @Excel(name = "申请时间", width = 20, isImportField = "true")
    //申请时间
    private String candidateTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Excel(name = "入库时间", width = 20, isImportField = "true")
    //入库时间
    private Date candidateStorage;

    @Excel(name = "入库原因", width = 20, isImportField = "true")
    //入库原因(1.能力不足，2个人原因.)
    private Integer candidateStorageReason;

//
    @Excel(name = "状态", width = 20, isImportField = "true")
    //状态(1.进入候选人，2.进入人才库)
    private Integer candidateState;

    @Excel(name = "部门", width = 20, isImportField = "true")
    //部门(1.技术部，2.产品部，3.销售部，4.行政部)
    private String candidateDepartment;

    /**
     * 开始时间
     */
    @TableField(exist = false)
    private String candidateStartTime;

    /**
     * 结束时间
     */
    @TableField(exist = false)
    private String candidateEndTime;

    /**
     * 职位实体类
     */
    @TableField(exist = false)
    private RecruitJob recruitJob;
}
