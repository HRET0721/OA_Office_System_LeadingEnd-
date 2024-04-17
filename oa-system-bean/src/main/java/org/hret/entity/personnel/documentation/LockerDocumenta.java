package org.hret.entity.personnel.documentation;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hret.entity.utils.query.Page;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@TableName(value = "locker_documenta")
public class LockerDocumenta extends Page implements Serializable {
    @Serial
    private static final long serialVersionUID = -49452775890884099L;
    @TableId(value = "documentation_id", type = IdType.AUTO)
    @Excel(name = "案库id",width = 20,isImportField = "true")
    /**
     * 案库id
     */
    private Integer lockerId;

    /**
     * 案卷id
     */
    @Excel(name = "建档id",width = 20,isImportField = "true")
    private Integer documentationId;
}
