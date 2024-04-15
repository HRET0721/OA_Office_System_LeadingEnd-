package org.hret.entity.personnel.attendance.shifts.elastic;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

/**
 * (AdShiftsEalstic)实体类
 *
 * @author makejava
 * @since 2024-04-02 13:17:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AdShiftsElastic implements Serializable {
    @Serial
    private static final long serialVersionUID = 925611876299045030L;
    @TableId(value = "ad_shifts_elastic_id", type = IdType.AUTO)
/**
     * ad_shifts_elastic_id
     */
    private Long adShiftsElasticId;
/**
     * 允许晚走晚到 -> 第一天下班后晚走 ? 小时 


     */
    private Double go;
/**
     * 允许晚走玩到 -> 第二天上班可以晚到 ? 小时

     */
    private Double late;
/**
     * ad_shifts_id
     */
    private Long adShiftsId;

}

