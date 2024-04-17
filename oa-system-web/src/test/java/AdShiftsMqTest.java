import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.hret.OaSystemApplication;
import org.hret.entity.personnel.attendance.dto.AdShiftsDTO;
import org.hret.service.personnel.attendance.shifts.IAdShiftsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @version oa-system
 * @developer breath
 * @User breath
 * @date 2024/4/8 18:27
 */

@SpringBootTest(classes = OaSystemApplication.class)
@Slf4j
public class AdShiftsMqTest {
    @Autowired
    private IAdShiftsService adShiftsService;
    String jsonString = "{\n" +
            "  \"adShiftsId\": null,\n" +
            "  \"workerId\": 2,\n" +
            "  \"shiftsElasticAllowable\": true,\n" +
            "  \"shiftsElasticOvertime\": \"30\",\n" +
            "  \"shiftsElasticOvertimeAllowable\": true,\n" +
            "  \"shiftsName\": \"班次名称1\",\n" +
            "  \"adShiftsFrameTimeList\": [\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"frameTimeAndRulesUp\": {\n" +
            "        \"day\": \"T\",\n" +
            "        \"day_time\": \"18:00\",\n" +
            "        \"day_allow\": 1,\n" +
            "        \"most\": 60,\n" +
            "        \"late\": 5,\n" +
            "        \"lake\": 30,\n" +
            "        \"type\": 1,\n" +
            "        \"ad_shifts_id\": null,\n" +
            "        \"dayAllow\": true\n" +
            "      },\n" +
            "      \"frameTimeAndRulesDown\": {\n" +
            "        \"day\": \"T\",\n" +
            "        \"day_time\": \"18:00\",\n" +
            "        \"day_allow\": 1,\n" +
            "        \"most\": 60,\n" +
            "        \"late\": 5,\n" +
            "        \"lake\": 30,\n" +
            "        \"type\": 2,\n" +
            "        \"ad_shifts_id\": null\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 2,\n" +
            "      \"frameTimeAndRulesUp\": {\n" +
            "        \"day\": \"N\",\n" +
            "        \"day_time\": \"18:00\",\n" +
            "        \"day_allow\": 1,\n" +
            "        \"most\": 60,\n" +
            "        \"late\": 5,\n" +
            "        \"lake\": 30,\n" +
            "        \"type\": 1,\n" +
            "        \"ad_shifts_id\": null,\n" +
            "        \"dayAllow\": false\n" +
            "      },\n" +
            "      \"frameTimeAndRulesDown\": {\n" +
            "        \"day\": \"N\",\n" +
            "        \"day_time\": \"18:00\",\n" +
            "        \"day_allow\": 1,\n" +
            "        \"most\": 60,\n" +
            "        \"late\": 5,\n" +
            "        \"lake\": 30,\n" +
            "        \"type\": 2,\n" +
            "        \"ad_shifts_id\": null\n" +
            "      }\n" +
            "    }\n" +
            "  ],\n" +
            "  \"adShiftsElasticList\": [\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"go\": 4,\n" +
            "      \"late\": 6\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 2,\n" +
            "      \"go\": 4,\n" +
            "      \"late\": 6\n" +
            "    }\n" +
            "  ]\n" +
            "}\n"; // 修复后的 JSON 字符串

    @Test
    public void testDemo1() throws JsonProcessingException, JsonMappingException {

        ObjectMapper mapper = new ObjectMapper();
        AdShiftsDTO adShiftsDTO = mapper.readValue(jsonString, AdShiftsDTO.class);

        adShiftsService.addAdShiftsMqPro(adShiftsDTO);
    }

    @Test
    public void testDemo2() {
    log.error(adShiftsService.findAdShiftsPage().toString());
}
    @Test
    public void testDemo3(){
        log.warn(adShiftsService.findAdShitsByIDPro(13L).toString());
    }
}

//04-09 16:15:52:568  WARN 3532 --- [           main] o.h.s.p.i.a.shifts.AdShiftsServiceImpl   : 转换后的adShifts:AdShifts(adShiftsId=null, workerId=2, shiftsElasticAllowable=1, shiftsElasticOvertime=30, shiftsElasticOvertimeAllowable=1, shiftsName=班次名称1, adShiftsFramesList=[AdShiftsFrame(adShiftsFrameId=null, day=T, dayTime=18:00, dayAllow=1, most=60, late=5, lake=30, adShiftsId=null, type=1), AdShiftsFrame(adShiftsFrameId=null, day=T, dayTime=18:00, dayAllow=1, most=60, late=5, lake=30, adShiftsId=null, type=2), AdShiftsFrame(adShiftsFrameId=null, day=N, dayTime=18:00, dayAllow=1, most=60, late=5, lake=30, adShiftsId=null, type=1), AdShiftsFrame(adShiftsFrameId=null, day=N, dayTime=18:00, dayAllow=1, most=60, late=5, lake=30, adShiftsId=null, type=2)], adShiftsElasticList=[AdShiftsElastic(adShiftsElasticId=null, go=4.0, late=6.0, adShiftsId=null), AdShiftsElastic(adShiftsElasticId=null, go=4.0, late=6.0, adShiftsId=null)])
