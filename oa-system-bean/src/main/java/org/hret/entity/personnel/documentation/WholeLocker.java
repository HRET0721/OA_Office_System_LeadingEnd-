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
@AllArgsConstructor
@NoArgsConstructor
@Component
@TableName(value = "whole_locker")
public class WholeLocker extends Page implements Serializable {
    /**
     * 全宗id
     */
    @Serial
    private static final long serialVersionUID = -49452775890884099L;
    @TableId(value = "documentation_id", type = IdType.AUTO)
    @Excel(name = "建档id",width = 20,isImportField = "true")
    private Integer wholeSectId;

    /**
     * 案库id
     */
    @Excel(name = "案库id",width = 20,isImportField = "true")
    private Integer lockerId;
}
