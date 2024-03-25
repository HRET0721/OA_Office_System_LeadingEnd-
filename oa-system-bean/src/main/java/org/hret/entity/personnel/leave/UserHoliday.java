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
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@TableName(value = "user_holiday")
public class UserHoliday extends Page implements Serializable {
    @Serial
    private static final long serialVersionUID = -49452775890884099L;
    @TableId(value = "user_id", type = IdType.AUTO)
//    部门id
    @Excel(name = "用户id",width = 20,isImportField = "true")
    private Integer userId;
    @Excel(name = "假期id",width = 20,isImportField = "true")
    private Integer balanceId;
}
