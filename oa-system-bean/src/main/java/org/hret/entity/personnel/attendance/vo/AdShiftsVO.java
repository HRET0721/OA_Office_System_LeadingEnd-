package org.hret.entity.personnel.attendance.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @version oa-system
 * @developer breath
 * @User breath
 * @date 2024/4/9 14:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "新增班次实体")
public class AdShiftsVO {
    @ApiModelProperty(value = "班次id")
    private Long adShiftsId;
    @ApiModelProperty(value = "用户id")
    private Long workerId;
    @ApiModelProperty(value = "允许晚到晚走")
    private String shiftsElasticAllowable ;
    @ApiModelProperty(value = "晚到超时时间")
    private String shiftsElasticOvertime ;
    @ApiModelProperty(value = "允许晚到晚走")
    private String shiftsElasticOvertimeAllowable ;
    @ApiModelProperty(value = "班次名称")
    private String shiftsName ;
    @ApiModelProperty(value = "上班下班时间and规则")
    private List<Object> adShiftsFrameTimeList ;
    @ApiModelProperty(value = "弹性设置")
    private List<Object> adShiftsElasticList ;
}