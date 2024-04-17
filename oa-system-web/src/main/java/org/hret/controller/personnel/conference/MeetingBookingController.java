package org.hret.controller.personnel.conference;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.hret.entity.personnel.conference.MeetingBooking;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.conference.MeetingBookingService;
import org.springframework.web.bind.annotation.*;

/**
 * Authar:liujintao
 * Data:2024/4/10
 * jdk:17
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "MeetingBooking")
@Tag(name = "我的预定", description = "我的预定")
public class MeetingBookingController {
    private final MeetingBookingService meetingBookingService;

    @RequestMapping(value = "findMeetingBookingPage", method = RequestMethod.POST)
    @Operation(summary = "查询我的预定", description = "查询我的预定")
    public JsonResult findMeetingBookingPage(@RequestBody MeetingBooking meetingBooking) {
        return JsonResult.ok("200",meetingBookingService.findMeetingBookingPage(meetingBooking));
    }
}
