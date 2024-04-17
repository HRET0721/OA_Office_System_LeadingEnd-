package org.hret.entity.personnel.leave;

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

@AllArgsConstructor
@Component
@TableName(value = "operate_dept")
@NoArgsConstructor
@Data
public class OperateDept extends Page implements Serializable {
    @Serial
    private static final long serialVersionUID = -49452775890884099L;
    @TableId(value = "dept_id", type = IdType.AUTO)
    @Excel(name = "部门id",width = 20,isImportField = "true")
    private Integer deptId;
    @Excel(name = "操作id",width = 20,isImportField = "true")
    private Integer operateId;

}
