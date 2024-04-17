package org.hret.entity.personnel.documentation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.hret.entity.utils.query.Page;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@TableName(value = "case_locker")
@AllArgsConstructor
@Component
@Data
@NoArgsConstructor
public class CaseLocker extends Page implements Serializable {
    @Serial
    private static final long serialVersionUID = -49452775890884099L;
    @TableId(value = "locker_id", type = IdType.AUTO)
    private Integer lockerId;

    /**
     * 案库姓名
     */
    private String lockName;

    /**
     * 归属公司
     */
    private String owningCompany;

    /**
     * 创建时间
     */
    private Date creationTime;

    /**
     * 更新时间
     */
    private Date updated;

    /**
     * 建档id
     */
    private Integer documentationId;

    /**
     * 全宗id
     */
    private Integer wholeSectId;
    private Integer affiliationss;

    @TableField(exist = false)
    private String ascriptionSect;
    @TableField(exist = false)
    private String affiliation;
    @TableField(exist = false)
    private List<ArchivesDocumentation> archivesDocumentations;
    @TableField(exist = false)
    private Integer belongssFile;
    @TableField(exist = false)
    private String wholeSectName;
}
