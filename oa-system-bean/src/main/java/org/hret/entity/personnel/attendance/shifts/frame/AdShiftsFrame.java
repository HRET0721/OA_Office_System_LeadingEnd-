package org.hret.entity.personnel.attendance.shifts.frame;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

/**
 * 班次管理 -> 上下班时间or规则 (AdShiftsFrame)实体类
 *
 * @author makejava
 * @since 2024-04-02 13:21:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AdShiftsFrame implements Serializable {
    @Serial
    private static final long serialVersionUID = -17880923197620280L;
    @TableId(value = "ad_shifts_frame_id", type = IdType.AUTO)
/**
     * ad_shifts_id
     */
    private Long adShiftsFrameId;
/**
     * 时间 -> 日子 ( 当日 or 次日 ) 

存储规则: 
  T: 当日 
  N: 次日 
     */
    private String day;
/**
     * 时间 -> 日子时间 

存储规则: 
  存储为varchar类型的 时分 格式的时间

存储实例: 
  18:00 
  20:00 
     */
    private String dayTime;
/**
     * 时间 -> 是否允许下班打卡 

存储规则: 
  1: 允许 
  0: 不允许
     */
    private Long dayAllow;
/**
     * 打卡规则 -> 最早可提前 ? 分钟打卡  or 最晚 

存储规则: 
  分钟 

例子: 
  60 
  10
     */
    private Long most;
/**
     * 打卡规则 -> 晚于 ? 分钟打卡为迟到  or 提前

存储规则: 
  分钟 

例子: 
  5
  10
     */
    private Long late;
/**
     * 打卡规则 -> 晚于 ? 分钟打卡为缺卡半天 or 提前

存储规则: 
  分钟

例子: 
  30 
  5
     */
    private Long lake;
/**
     * ad_shifts_id
     */
    private Long adShiftsId;
/**
     * 上班 or 下班 

存储规则: 
  1: 上班
  2: 下班
     */
    private Long type;



}

