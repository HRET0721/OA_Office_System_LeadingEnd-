package org.hret.entity.personnel.documentation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@TableName(value = "whole_sect")
public class WholeSect extends Page implements Serializable {
    @Serial
    private static final long serialVersionUID = -49452775890884099L;
    @TableId(value = "whole_sect_id", type = IdType.AUTO)
    private Integer wholeSectId;

    /**
     * 全宗名称
     */
    private String wholeSectName;

    /**
     * 所属公司
     */
    private String affiliation;

    /**
     * 提交人
     */
    private String author;

    /**
     * 创建时间
     */
    private Date creationTime;

    /**
     * 更新时间
     */
    private Date updated;

    /**
     * 档案id
     */
    private Integer documentationId;

    @TableField(exist = false)
    private List<CaseLocker> caseLockers;
    @TableField(exist = false)
    private List<ArchivesDocumentation> archivesDocumentations;
    @TableField(exist = false)
    private Integer belongssFile;
}
