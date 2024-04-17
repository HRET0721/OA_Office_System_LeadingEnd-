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
@TableName(value = "documentation_borrowing")
public class DocumentationBorrowing extends Page implements Serializable {
    @Serial
    private static final long serialVersionUID = -49452775890884099L;
    @TableId(value = "documentation_id", type = IdType.AUTO)
//    部门id
    @Excel(name = "建档id",width = 20,isImportField = "true")
    private Integer documentationId;

    /**
     * 借阅id
     */
    @Excel(name = "借阅id",width = 20,isImportField = "true")
    private Integer borrowingId;

}
