package org.hret.entity.personnel.leave;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Balance implements Serializable {
    /**
     * 余额id
     */
    @TableId(value = "balance_id", type = IdType.AUTO)
    private Integer balanceId;

    /**
     * 事假
     */
    private String leave;

    /**
     * 年假
     */
    private String annualLeave;

    /**
     * 病假
     */
    private String sickLeave;

    /**
     * 产假
     */
    private String maternityLeave;

    /**
     * 陪产假
     */
    private String paternityLeave;

    /**
     * 婚假
     */
    private String marriageLeave;
}
