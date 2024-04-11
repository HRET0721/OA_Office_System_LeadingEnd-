import lombok.extern.slf4j.Slf4j;
import org.hret.OaSystemApplication;
import org.hret.entity.personnel.attendance.dto.AdShiftsDTO;
import org.hret.entity.personnel.attendance.shifts.AdShifts;
import org.hret.service.personnel.impl.attendance.shifts.AdShiftsServiceImpl;
import org.hret.service.personnel.impl.attendance.shifts.AdShiftsUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @version oa-system
 * @developer breath
 * @User breath
 * @date 2024/4/3 10:04
 */
@SpringBootTest(classes = OaSystemApplication.class)
@Slf4j
public class AdShiftsTest {
    @Autowired
    private AdShiftsUtil adShiftsUtil;
    @Autowired
    private AdShiftsServiceImpl adShiftsService;
    // 假设您的日志信息已存储在字符串变量 logContent 中
    String logContent = "AdShiftsDTO(...)";
// 使用适当的逻辑或工具解析日志内容，提取出各属性值

    private Long adShiftsId = null;
    private Long workerId = 2L;
    private String shiftsElasticAllowable = "true";
    private String shiftsElasticOvertime = "30";
    private String shiftsElasticOvertimeAllowable = "true";
    private String shiftsName = "班次名称1";


    private List<Object> adShiftsFrameTimeList = new ArrayList<>();
// 解析日志中的adShiftsFrameTimeList部分，创建相应的AdShiftsFrameTime对象并添加到列表中

    private List<Object> adShiftsEalsticList = new ArrayList<>();
// 解析日志中的adShiftsEalsticList部分，创建相应的AdShiftsElastic对象并添加到列表中

//    AdShiftsDTO adShiftsDTO = new AdShiftsDTO(adShiftsId, workerId, shiftsElasticAllowable, shiftsElasticOvertime, shiftsElasticOvertimeAllowable, shiftsName, adShiftsFrameTimeList, adShiftsEalsticList);

    @Test
    void testAdd() {
        AdShiftsDTO adShiftsDTO = new AdShiftsDTO(adShiftsId, workerId, shiftsElasticAllowable, shiftsElasticOvertime, shiftsElasticOvertimeAllowable, shiftsName, adShiftsFrameTimeList, adShiftsEalsticList);
//        new AdShiftsDTO(null,1,"true","28:00","true","班次名称1",adShiftsFrameTimeList,adShiftsEalsticList);
//        log.error(adShiftsService.findAdShitsByID(2L).toString());
        log.error(adShiftsUtil.mapDtoToEntity(adShiftsDTO, new AdShifts()).toString());
    }
}
