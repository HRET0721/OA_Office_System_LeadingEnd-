package org.hret.entity.personnel.attendance.shifts;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hret.entity.personnel.attendance.shifts.frame.AdShiftsFrame;
import org.hret.entity.personnel.attendance.shifts.elastic.AdShiftsElastic;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * ad_shifts 班次表

 关联表 -> ad_shifts_*(AdShifts)实体类
 *
 * @author makejava
 * @since 2024-03-26 17:07:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AdShifts implements Serializable {
    @Serial
    private static final long serialVersionUID = -52753427207070308L;
    @TableId(value = "ad_shifts_id", type = IdType.AUTO)
/**
 * 班次表id
 */
    private Long adShiftsId;
    /**
     * 班次负责人:

     存储规则:
     存储为 worker ( 负责人 ) 表的 id


     */
    private Long workerId;
    /**
     * 上下班时间:

     存储规则:
     1: 为true ( 即去ad_shifts_frame表去查 )
     0: 为false ( 即未拥有时段 )
     */

    /**
     * 允许晚走晚到 by 弹性设置 :

     存储规则:
     1: 允许晚到晚走 ( 如果为1查询 ad_shifts_elastic )
     2: 不允许晚到晚走


     */
    private Long shiftsElasticAllowable;
    /**
     * 晚到超过时间

     存储规则:
     以分钟来存储

     例:
     60 ( min )
     30 ( min )
     */
    private Long shiftsElasticOvertime;
    /**
     * 允许晚到超时:

     存储规则:
     1: 允许晚到超时
     2: 不允许晚到超时

     */
    private Long shiftsElasticOvertimeAllowable;
    /**
     * 班次名称
     */
    @NonNull
    private String shiftsName;



    @TableField(exist = false)
    private List<AdShiftsFrame> adShiftsFramesList;

    @TableField(exist = false)
    private List<AdShiftsElastic> adShiftsElasticList;
}

