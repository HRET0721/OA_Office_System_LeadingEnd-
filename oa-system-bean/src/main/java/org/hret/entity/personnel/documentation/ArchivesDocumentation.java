package org.hret.entity.personnel.documentation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hret.entity.personnel.leave.HolidayDalance;
import org.hret.entity.utils.query.Page;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@TableName(value = "archives_documentation")
public class ArchivesDocumentation extends Page implements Serializable {
    @Serial
    private static final long serialVersionUID = -49452775890884099L;
    @TableId(value = "documentation_id", type = IdType.AUTO)
    private Integer documentationId;

    /**
     * 档案号
     */
    private Integer fileNumber;

    /**
     * 文件标题
     */
    private String fileTitle;

    /**
     * 文号
     */
    private Integer documentNumber;

    /**
     * 归属全宗
     */
    private String ascriptionSect;

    /**
     * 全宗号
     */
    private Integer sectNumber;

    /**
     * 所属案卷
     */
    private Integer belongssFile;

    /**
     * 案卷号
     */
    private Integer fileId;

    /**
     * 案卷名称
     */
    private String fileName;

    /**
     * 提交人
     */
    private String author;

    /**
     * 保密等级
     */
    private String secrecyGrade;

    /**
     * 借用状态(1(已借出2(未接出)))
     */
    private String states;

    /**
     * 创建时间
     */
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationTime;

    /**
     * 更新时间
     */
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updated;

    /**
     * 销毁状态(1(未销毁2(已销毁)))
     */
    private String smallStates;

    /**
     * 库号
     */
    private Integer libraryNumber;

    /**
     * 档案摘要
     */
    private String summary;

    /**
     * 销毁时间
     */
    private Date destructionTime;

    private Integer lockerId;

    private Integer wholeSectId;


    @TableField(exist = false)
    private List<Borrowing> borrowings;
    @TableField(exist = false)
    private List<Returns> returns;
    @TableField(exist = false)
    private List<WholeSect> wholeSects;
    @TableField(exist = false)
    private List<CaseLocker> caseLockers;
    @TableField(exist = false)
    private String wholeSectName;
    @TableField(exist = false)
    private String lockName;


}
