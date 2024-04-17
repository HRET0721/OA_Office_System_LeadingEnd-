package org.hret.entity.personnel.conference;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.hret.entity.utils.query.Page;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (MeetingBooking)表实体类
 *
 * @author makejava
 * @since 2024-04-10 15:01:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("meeting_booking")
@ApiModel("我的预定实体类")
public class MeetingBooking extends Page  implements Serializable {
//会议预定主键id
    private Integer meetingId;
//会议主题
    private String conferenceTheme;
//会议室名称
    private String roomName;
//会议时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date meetingTime;
//发起人
    private String initiator;
//    会议类型
    private Integer conferenceType;
//主持人
    private String compere;
//与会人数
    private Integer numberParticipants;
//会议状态
    private String conferenceStatus;
//会议时长
    private String meetingLength;
//会议描述
    private String meetingDescription;
//会议通知
    private String meetingNotice;
//特征1
    private String trait1;
//特征2
    private String trait2;
//特征3
    private String trait3;
//特征4
    private String trait4;
//特征5
    private String trait5;

    /**
     * 开始时间
     */
    @TableField(exist = false)
    private String meetingStartTime;

    /**
     * 结束时间
     */
    @TableField(exist = false)
    private String meetingEndTime;
}

