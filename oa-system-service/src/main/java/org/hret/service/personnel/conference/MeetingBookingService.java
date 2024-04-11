package org.hret.service.personnel.conference;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import org.hret.entity.personnel.conference.MeetingBooking;

/**
 * Authar:liujintao
 * Data:2024/4/10
 * jdk:17
 */
public interface MeetingBookingService extends IService<MeetingBooking> {
    PageInfo<MeetingBooking> findMeetingBookingPage(MeetingBooking meetingBooking);
}
