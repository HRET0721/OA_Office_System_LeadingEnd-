package org.hret.service.personnel.impl.conference;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hret.entity.personnel.conference.MeetingBooking;
import org.hret.mapper.personnel.conference.MeetingBookingMapper;
import org.hret.service.personnel.conference.MeetingBookingService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Authar:liujintao
 * Data:2024/4/10
 * jdk:17
 */
@Service
public class MeetingBookingServiceImpl extends ServiceImpl<MeetingBookingMapper, MeetingBooking> implements MeetingBookingService {
    @Override
    public PageInfo<MeetingBooking> findMeetingBookingPage(MeetingBooking meetingBooking) {
        PageHelper.startPage(meetingBooking.getPageNum(), meetingBooking.getPageSize());
        LambdaQueryWrapper<MeetingBooking> wrapper = Wrappers.lambdaQuery(MeetingBooking.class);
//        会议主题模糊查询
        if (meetingBooking.getConferenceTheme() != null && !meetingBooking.getConferenceTheme().isEmpty()) {
            wrapper.like(MeetingBooking::getConferenceTheme, meetingBooking.getConferenceTheme());
        }
//        会议类型的条查
        if (meetingBooking.getConferenceType() != null){
            wrapper.eq(MeetingBooking::getConferenceType,meetingBooking.getConferenceType());
        }
//        会议室状态条查
        if (meetingBooking.getConferenceStatus() != null && !meetingBooking.getConferenceStatus().isEmpty()){
            wrapper.eq(MeetingBooking::getConferenceStatus,meetingBooking.getConferenceStatus());
        }
//        会议时间范围查询
        if (meetingBooking.getMeetingStartTime() != null && !meetingBooking.getMeetingStartTime().isEmpty()){
            wrapper.ge(MeetingBooking::getMeetingTime,meetingBooking.getMeetingStartTime());
        }
        if (meetingBooking.getMeetingEndTime() != null && !meetingBooking.getMeetingEndTime().isEmpty()){
            wrapper.le(MeetingBooking::getMeetingTime,meetingBooking.getMeetingEndTime());
        }
        List<MeetingBooking> list = this.list(wrapper);
        return new PageInfo<>(list);
    }
}
