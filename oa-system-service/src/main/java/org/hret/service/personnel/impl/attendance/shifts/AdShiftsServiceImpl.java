package org.hret.service.personnel.impl.attendance.shifts;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hret.entity.personnel.attendance.dto.AdShiftsDTO;
import org.hret.entity.personnel.attendance.shifts.AdShifts;
import org.hret.entity.personnel.attendance.shifts.elastic.AdShiftsElastic;
import org.hret.entity.personnel.attendance.shifts.frame.AdShiftsFrame;
import org.hret.entity.personnel.attendance.vo.AdShiftsVO;
import org.hret.mapper.personnel.attendance.shifts.AdShiftsMapper;
import org.hret.mapper.personnel.attendance.shifts.elastic.AdShiftsElasticMapper;
import org.hret.mapper.personnel.attendance.shifts.frame.AdShiftsFrameMapper;
import org.hret.service.personnel.attendance.shifts.IAdShiftsService;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @version oa-system
 * @developer breath
 * @User breath
 * @date 2024/3/26 15:26
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AdShiftsServiceImpl extends ServiceImpl<AdShiftsMapper, AdShifts> implements IAdShiftsService {

    @Autowired
    private AdShiftsMapper adShiftsMapper;
    @Autowired
    private AdShiftsFrameMapper adShiftsFrameMapper;

    @Autowired
    private AdShiftsElasticMapper adShiftsElasticMapper;

    final private AdShiftsUtil adShiftsUtil;
    final private AdShiftsReverseUtil adShiftsReverseUtil;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public List<AdShifts> findAdShiftsPage() {
        List<AdShifts> adShiftsList = adShiftsMapper.selectList(null);
        adShiftsList.forEach(adShifts -> {
            adShifts.setAdShiftsFramesList(adShiftsFrameMapper.selectList(new QueryWrapper<AdShiftsFrame>().eq("ad_shifts_id", adShifts.getAdShiftsId())));
            adShifts.setAdShiftsElasticList(adShiftsElasticMapper.selectList(new QueryWrapper<AdShiftsElastic>().eq("ad_shifts_id", adShifts.getAdShiftsId())));
        });
        return adShiftsList;
    }

    @Override
    public AdShifts findAdShitsByID(Long adShiftsId) {
        QueryWrapper<AdShifts> wrapper = new QueryWrapper<>();
        // 假设我们不仅想查询AdShifts表本身的信息，还想要关联查询其它三个表的数据
        // 注意：此处仅为示例，实际关联条件需根据数据库表设计调整
        wrapper.eq("ad_shifts_id", adShiftsId); // 根据ad_shifts_id查询
        AdShifts adShifts = getOne(wrapper);

        // 根据id查询上班或下时间and班规则集合
        QueryWrapper<AdShiftsFrame> rulesQueryWrapper = new QueryWrapper<>();
        rulesQueryWrapper.eq("ad_shifts_id", adShiftsId);
        List<AdShiftsFrame> adShiftsFrameRulesList = new ArrayList<>();
        adShiftsFrameMapper.selectList(rulesQueryWrapper).forEach(rule -> {
            adShifts.setAdShiftsFramesList(new ArrayList<>());
            adShifts.getAdShiftsFramesList().add(rule);
            adShiftsFrameRulesList.add(rule);
        });
        adShifts.setAdShiftsFramesList(adShiftsFrameRulesList);

        // 根据id查询AdShiftsElastic集合
        QueryWrapper<AdShiftsElastic> elasticQueryWrapper = new QueryWrapper<>();
        elasticQueryWrapper.eq("ad_shifts_id", adShiftsId);
        List<AdShiftsElastic> adShiftsElasticList = new ArrayList<>();
        adShiftsElasticMapper.selectList(elasticQueryWrapper).forEach(elastic -> {
            adShifts.setAdShiftsElasticList(new ArrayList<>());
            adShifts.getAdShiftsElasticList().add(elastic);
            adShiftsElasticList.add(elastic);
        });
        adShifts.setAdShiftsElasticList(adShiftsElasticList);

        return adShifts;
    }

    @Override
    public AdShiftsVO findAdShitsByIDPro(Long adShiftsId) {
        QueryWrapper<AdShifts> wrapper = new QueryWrapper<>();
        // 假设我们不仅想查询AdShifts表本身的信息，还想要关联查询其它三个表的数据
        // 注意：此处仅为示例，实际关联条件需根据数据库表设计调整
        wrapper.eq("ad_shifts_id", adShiftsId); // 根据ad_shifts_id查询
        AdShifts adShifts = getOne(wrapper);

        // 根据id查询上班或下时间and班规则集合
        QueryWrapper<AdShiftsFrame> rulesQueryWrapper = new QueryWrapper<>();
        rulesQueryWrapper.eq("ad_shifts_id", adShiftsId);
        List<AdShiftsFrame> adShiftsFrameRulesList = new ArrayList<>();
        adShiftsFrameMapper.selectList(rulesQueryWrapper).forEach(rule -> {
            adShifts.setAdShiftsFramesList(new ArrayList<>());
            adShifts.getAdShiftsFramesList().add(rule);
            adShiftsFrameRulesList.add(rule);
        });
        adShifts.setAdShiftsFramesList(adShiftsFrameRulesList);

        // 根据id查询AdShiftsElastic集合
        QueryWrapper<AdShiftsElastic> elasticQueryWrapper = new QueryWrapper<>();
        elasticQueryWrapper.eq("ad_shifts_id", adShiftsId);
        List<AdShiftsElastic> adShiftsElasticList = new ArrayList<>();
        adShiftsElasticMapper.selectList(elasticQueryWrapper).forEach(elastic -> {
            adShifts.setAdShiftsElasticList(new ArrayList<>());
            adShifts.getAdShiftsElasticList().add(elastic);
            adShiftsElasticList.add(elastic);
        });
        adShifts.setAdShiftsElasticList(adShiftsElasticList);

        AdShiftsVO adShiftsVo = adShiftsReverseUtil.mapEntityToVo(adShifts, new AdShiftsVO());
        return adShiftsVo;
    }

    @Override
    //事务管理
    @Transactional(rollbackFor = Exception.class)
    public void addAdShifts(AdShiftsDTO adShiftsDTO) {
        log.error(adShiftsDTO.toString());

        //shiftsId 用来多表新增
        Long shiftsId = null;

        //1.新增ad_shifts表
        AdShifts adShifts = adShiftsUtil.mapDtoToEntity(adShiftsDTO, new AdShifts());
        if (StringUtils.isNotBlank(adShiftsDTO.getShiftsElasticAllowable())) {
            boolean elasticAllowable = Boolean.parseBoolean(adShiftsDTO.getShiftsElasticAllowable());
            adShifts.setShiftsElasticAllowable(elasticAllowable ? 1L : 0L);
        }
        if (StringUtils.isNotBlank(adShiftsDTO.getShiftsElasticOvertimeAllowable())) {
            boolean elasticOvertimeAllowable = Boolean.parseBoolean(adShiftsDTO.getShiftsElasticOvertimeAllowable());
            adShifts.setShiftsElasticOvertimeAllowable(elasticOvertimeAllowable ? 1L : 0L);
        }
        if (StringUtils.isNotBlank(adShiftsDTO.getShiftsElasticOvertime())) {
            adShifts.setShiftsElasticOvertime(Long.parseLong(adShiftsDTO.getShiftsElasticOvertime()));
        }

        int rowsAffected = 0;
        try {
            rowsAffected = adShiftsMapper.insert(adShifts);
        } catch (RuntimeException runtimeException) {
            log.error("新增ad_shifts表失败");
            throw new RuntimeException("新增ad_shifts表失败");

        }
        // 若插入成功（rowsAffected > 0），此时 adShifts.id 应已填充上数据库自动生成的 id 值
        if (rowsAffected > 0) {
            shiftsId = adShiftsMapper.selectOne(new QueryWrapper<AdShifts>().eq("shifts_name", adShifts.getShiftsName())).getAdShiftsId();
        }
        try {
            //2.新增 ad_shifts_elastic 表
            //2.1 批量插入
            List<AdShiftsElastic> elasticList = adShiftsUtil.convertElasticList(adShiftsDTO.getAdShiftsElasticList());
            log.debug(elasticList.toString());
            // 设置每个AdShiftsElastic对象的adShiftsId为 shiftsId
            for (AdShiftsElastic elastic : elasticList) {
                elastic.setAdShiftsId(shiftsId);
            }

            //        //2.2 异步处理（可选）
//        ExecutorService executor = Executors.newFixedThreadPool(5);
//
//        adShiftsDTO.getAdShiftsEalsticList().forEach(elastic -> {
//            executor.submit(() -> {
//                AdShiftsElastic adShiftsElastic = adShiftsUtil.convertObjectToElastic(elastic);
//                adShiftsElasticMapper.insert(adShiftsElastic);
//            });
//        });
//
//        executor.shutdown(); // 确保在应用关闭时等待所有任务完成

            //批量插入操作
            adShiftsElasticMapper.insertBatchSomeColumn(elasticList);
        } catch (RuntimeException runtimeException) {
            log.error("新增ad_shifts_elastic表失败");
            throw new RuntimeException("新增ad_shifts_elastic表失败");
        }


    }

    @Override
    public void addAdShiftsMq(AdShiftsDTO adShiftsDTO) {
        log.debug(adShiftsDTO.toString());

        //shiftsId 用来多表新增
//        Long shiftsId = null;
        Long shiftsId = 0l;

        //1.新增ad_shifts表
        AdShifts adShifts = adShiftsUtil.mapDtoToEntity(adShiftsDTO, new AdShifts());
        if (StringUtils.isNotBlank(adShiftsDTO.getShiftsElasticAllowable())) {
            boolean elasticAllowable = Boolean.parseBoolean(adShiftsDTO.getShiftsElasticAllowable());
            adShifts.setShiftsElasticAllowable(elasticAllowable ? 1L : 0L);
        }
        if (StringUtils.isNotBlank(adShiftsDTO.getShiftsElasticOvertimeAllowable())) {
            boolean elasticOvertimeAllowable = Boolean.parseBoolean(adShiftsDTO.getShiftsElasticOvertimeAllowable());
            adShifts.setShiftsElasticOvertimeAllowable(elasticOvertimeAllowable ? 1L : 0L);
        }
        if (StringUtils.isNotBlank(adShiftsDTO.getShiftsElasticOvertime())) {
            adShifts.setShiftsElasticOvertime(Long.parseLong(adShiftsDTO.getShiftsElasticOvertime()));
        }
        log.warn("AdShifts adShifts = adShiftsUtil.mapDtoToEntity(adShiftsDTO, new AdShifts());" + adShifts.toString());

        int rowsAffected = 0;
        try {
            rowsAffected = adShiftsMapper.insert(adShifts);
        } catch (RuntimeException runtimeException) {
            log.error("新增ad_shifts表失败");
            rabbitTemplate.convertAndSend("shifts.direct", "shifts", "新增ad_shifts表失败");
        }
        // 若插入成功（rowsAffected > 0），此时 adShifts.id 应已填充上数据库自动生成的 id 值
        if (rowsAffected > 0) {
            shiftsId = adShiftsMapper.selectOne(new QueryWrapper<AdShifts>().eq("shifts_name", adShifts.getShiftsName())).getAdShiftsId();
        }
        //2.新增 ad_shifts_elastic 表
        try {
            //2.1 批量插入

            List<AdShiftsElastic> elasticList = adShiftsUtil.convertElasticList(adShiftsDTO.getAdShiftsElasticList());


            // 设置每个AdShiftsElastic对象的adShiftsId为 shiftsId
            for (AdShiftsElastic elastic : elasticList) {
                elastic.setAdShiftsId(shiftsId);
            }
            log.error(elasticList.toString());
            //        //2.2 异步处理（可选）
//        ExecutorService executor = Executors.newFixedThreadPool(5);
//
//        adShiftsDTO.getAdShiftsEalsticList().forEach(elastic -> {
//            executor.submit(() -> {
//                AdShiftsElastic adShiftsElastic = adShiftsUtil.convertObjectToElastic(elastic);
//                adShiftsElasticMapper.insert(adShiftsElastic);
//            });
//        });
//
//        executor.shutdown(); // 确保在应用关闭时等待所有任务完成
//            批量新增ad_shifts_elastic表
            adShiftsElasticMapper.insertBatchSomeColumn(elasticList);
        } catch (RuntimeException runtimeException) {
            rabbitTemplate.convertAndSend("addAdShiftsMq.direct", "shiftsElastic", "通知rabbitMq 进行 新增 ad_shifts_elastic 表 失败", new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    message.getMessageProperties().setExpiration("10000");
                    return message;
                }
            });
            log.info("消息发送成功！");
        }

        //3.新增 ad_shifts_frame 表

        List<Object> adShiftsFrameTimeList = adShiftsDTO.getAdShiftsFrameTimeList();

        ObjectMapper objectMapper = new ObjectMapper();
        List<Object> extractedRulesList = new ArrayList<>();

        try {
            for (Object item : adShiftsFrameTimeList) {
                if (!(item instanceof String)) {
                    // 如果 item 不是 String 类型，则将其转换为 String 类型
                    String jsonString = objectMapper.writeValueAsString(item);
                    Map<String, Object> itemMap = objectMapper.readValue(jsonString, Map.class);
                    extractedRulesList.add(itemMap.get("frameTimeAndRulesUp"));
                    extractedRulesList.add(itemMap.get("frameTimeAndRulesDown"));
                } else {
                    // 如果 item 是 String 类型，则直接使用
                    String jsonString = (String) item;
                    Map<String, Object> itemMap = objectMapper.readValue(jsonString, Map.class);
                    extractedRulesList.add(itemMap.get("frameTimeAndRulesUp"));
                    extractedRulesList.add(itemMap.get("frameTimeAndRulesDown"));
                }
            }

            log.error(extractedRulesList.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if (extractedRulesList.size() > 0) {
            List<AdShiftsFrame> adShiftsFrameList = adShiftsUtil.convertFrameTimeList(extractedRulesList);
            final long tempShiftsId = shiftsId; // 创建临时final变量
            adShiftsFrameList.forEach(adShiftsFrame -> {
                adShiftsFrame.setAdShiftsId(tempShiftsId);
            });
            log.error(adShiftsFrameList.toString());
            //批量新增ad_shifts_frame表
            adShiftsFrameMapper.insertBatchSomeColumn(adShiftsFrameList);
        }

    }

    @Override
    public void addAdShiftsMqPro(AdShiftsDTO adShiftsDTO) {
        log.info("初始拿到的adShiftsDTO:" + adShiftsDTO.toString());

        //shiftsId 用来多表新增
//        Long shiftsId = null;
        Long shiftsId = 0l;

        //0.对adShiftsFrameTimeList进行解析并传入一个可以进行类型转换的map
        List<Object> adShiftsFrameTimeList = adShiftsDTO.getAdShiftsFrameTimeList();

        ObjectMapper objectMapper = new ObjectMapper();
        List<Object> extractedRulesList = new ArrayList<>();

        try {
            for (Object item : adShiftsFrameTimeList) {
                if (!(item instanceof String)) {
                    // 如果 item 不是 String 类型，则将其转换为 String 类型
                    String jsonString = objectMapper.writeValueAsString(item);
                    Map<String, Object> itemMap = objectMapper.readValue(jsonString, Map.class);
                    extractedRulesList.add(itemMap.get("frameTimeAndRulesUp"));
                    extractedRulesList.add(itemMap.get("frameTimeAndRulesDown"));
                } else {
                    // 如果 item 是 String 类型，则直接使用
                    String jsonString = (String) item;
                    Map<String, Object> itemMap = objectMapper.readValue(jsonString, Map.class);
                    extractedRulesList.add(itemMap.get("frameTimeAndRulesUp"));
                    extractedRulesList.add(itemMap.get("frameTimeAndRulesDown"));
                }
            }
            log.info("对adShiftsFrameTimeList进行解析并传入一个可以进行类型转换的map:extractedRulesList.toString()" + extractedRulesList.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        adShiftsDTO.setAdShiftsFrameTimeList(extractedRulesList);

        //1.新增ad_shifts表
        //1.1 根据adShiftsDTO创建adShifts实体
        AdShifts adShifts = adShiftsUtil.mapDtoToEntityPro(adShiftsDTO, new AdShifts());
        if (StringUtils.isNotBlank(adShiftsDTO.getShiftsElasticAllowable())) {
            boolean elasticAllowable = Boolean.parseBoolean(adShiftsDTO.getShiftsElasticAllowable());
            adShifts.setShiftsElasticAllowable(elasticAllowable ? 1L : 0L);
        }
        if (StringUtils.isNotBlank(adShiftsDTO.getShiftsElasticOvertimeAllowable())) {
            boolean elasticOvertimeAllowable = Boolean.parseBoolean(adShiftsDTO.getShiftsElasticOvertimeAllowable());
            adShifts.setShiftsElasticOvertimeAllowable(elasticOvertimeAllowable ? 1L : 0L);
        }
        if (StringUtils.isNotBlank(adShiftsDTO.getShiftsElasticOvertime())) {
            adShifts.setShiftsElasticOvertime(Long.parseLong(adShiftsDTO.getShiftsElasticOvertime()));
        }
        log.warn("转换后的adShifts:" + adShifts.toString());

        int rowsAffected = 0;
        try {
            //1.2 新增ad_shifts表操作
            rowsAffected = adShiftsMapper.insert(adShifts);
        } catch (RuntimeException runtimeException) {
            log.error("新增ad_shifts表失败");
            rabbitTemplate.convertAndSend("shifts.lazy.direct", "shifts.lazy", "新增ad_shifts表失败");
        }
        // 若插入成功（rowsAffected > 0），此时 adShifts.id 应已填充上数据库自动生成的 id 值
        if (rowsAffected > 0) {
            shiftsId = adShiftsMapper.selectOne(new QueryWrapper<AdShifts>().eq("shifts_name", adShifts.getShiftsName())).getAdShiftsId();
        }

        //2.新增 ad_shifts_elastic 表
        try {
            //2.1 批量插入
            List<AdShiftsElastic> adShiftsEalsticList = adShifts.getAdShiftsElasticList();
            // 设置每个AdShiftsElastic对象的adShiftsId为 shiftsId
            for (AdShiftsElastic elastic : adShiftsEalsticList) {
                elastic.setAdShiftsId(shiftsId);
            }
            log.error("转换后的adShiftsElasticList:" + adShiftsEalsticList);
            //        //2.2 异步处理（可选）
//        ExecutorService executor = Executors.newFixedThreadPool(5);
//
//        adShiftsDTO.getAdShiftsEalsticList().forEach(elastic -> {
//            executor.submit(() -> {
//                AdShiftsElastic adShiftsElastic = adShiftsUtil.convertObjectToElastic(elastic);
//                adShiftsElasticMapper.insert(adShiftsElastic);
//            });
//        });
//
//        executor.shutdown(); // 确保在应用关闭时等待所有任务完成
            //2.2 批量新增ad_shifts_elastic表
            adShiftsElasticMapper.insertBatchSomeColumn(adShiftsEalsticList);
        } catch (RuntimeException runtimeException) {
            rabbitTemplate.convertAndSend("shifts.direct", "shiftsElastic", "通知rabbitMq 进行 新增 ad_shifts_elastic 表 失败", new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    message.getMessageProperties().setExpiration("10000");
                    return message;
                }
            });
            log.info("消息发送成功！");
        }

        //3.新增 ad_shifts_frame 表
        try {
            List<AdShiftsFrame> adShiftsFramesList = adShifts.getAdShiftsFramesList();
            final long tempShiftsId = shiftsId; // 创建临时final变量
            adShiftsFramesList.forEach(adShiftsFrame -> {
                adShiftsFrame.setAdShiftsId(tempShiftsId);
            });
            log.error("转换后的adShiftsFramesList:" + adShiftsFramesList);
            //3.1 批量新增ad_shifts_frame表
            adShiftsFrameMapper.insertBatchSomeColumn(adShiftsFramesList);
        } catch (RuntimeException runtimeException) {
            rabbitTemplate.convertAndSend("shifts.direct", "shiftsFrame", "通知rabbitMq 进行 新增 ad_shifts_frame 表 失败", new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    message.getMessageProperties().setExpiration("10000");
                    return message;
                }
            });
            log.info("消息发送成功！");
        }

    }

    @Override
    public void updateAdShifts(AdShiftsDTO adShiftsDTO) {
        log.info("初始拿到的adShiftsDTO:" + adShiftsDTO.toString());

        //shiftsId 用来多表新增
//        Long shiftsId = null;
        Long shiftsId = 0l;

        //0.对adShiftsFrameTimeList进行解析并传入一个可以进行类型转换的map
        List<Object> adShiftsFrameTimeList = adShiftsDTO.getAdShiftsFrameTimeList();

        ObjectMapper objectMapper = new ObjectMapper();
        List<Object> extractedRulesList = new ArrayList<>();
        try {
            for (Object item : adShiftsFrameTimeList) {
                if (!(item instanceof String)) {
                    // 如果 item 不是 String 类型，则将其转换为 String 类型
                    String jsonString = objectMapper.writeValueAsString(item);
                    Map<String, Object> itemMap = objectMapper.readValue(jsonString, Map.class);
                    extractedRulesList.add(itemMap.get("frameTimeAndRulesUp"));
                    extractedRulesList.add(itemMap.get("frameTimeAndRulesDown"));
                } else {
                    // 如果 item 是 String 类型，则直接使用
                    String jsonString = (String) item;
                    Map<String, Object> itemMap = objectMapper.readValue(jsonString, Map.class);
                    extractedRulesList.add(itemMap.get("frameTimeAndRulesUp"));
                    extractedRulesList.add(itemMap.get("frameTimeAndRulesDown"));
                }
            }
            log.info("对adShiftsFrameTimeList进行解析并传入一个可以进行类型转换的map:extractedRulesList.toString()" + extractedRulesList.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        adShiftsDTO.setAdShiftsFrameTimeList(extractedRulesList);

        //1.修改ad_shifts表
        //1.1 根据adShiftsDTO创建adShifts实体
        AdShifts adShifts = adShiftsUtil.mapDtoToEntityPro(adShiftsDTO, new AdShifts());
        if (StringUtils.isNotBlank(adShiftsDTO.getShiftsElasticAllowable())) {
            boolean elasticAllowable = Boolean.parseBoolean(adShiftsDTO.getShiftsElasticAllowable());
            adShifts.setShiftsElasticAllowable(elasticAllowable ? 1L : 0L);
        }
        if (StringUtils.isNotBlank(adShiftsDTO.getShiftsElasticOvertimeAllowable())) {
            boolean elasticOvertimeAllowable = Boolean.parseBoolean(adShiftsDTO.getShiftsElasticOvertimeAllowable());
            adShifts.setShiftsElasticOvertimeAllowable(elasticOvertimeAllowable ? 1L : 0L);
        }
        if (StringUtils.isNotBlank(adShiftsDTO.getShiftsElasticOvertime())) {
            adShifts.setShiftsElasticOvertime(Long.parseLong(adShiftsDTO.getShiftsElasticOvertime()));
        }
        log.warn("转换后的adShifts:" + adShifts.toString());

        int rowsAffected = 0;
        try {
            //1.2 修改ad_shifts表操作
            rowsAffected = adShiftsMapper.updateById(adShifts);

        } catch (RuntimeException runtimeException) {
            log.error("新增ad_shifts表失败");
            rabbitTemplate.convertAndSend("shifts.direct", "shifts", "新增ad_shifts表失败");
        }
        //1.3 根据ad_shifts 的 id 删除ad_shifts_elastic表 and ad_shifts_frame表 中的数据
        // 若插入成功（rowsAffected > 0），此时 adShifts.id 应已填充上数据库自动生成的 id 值
        if (rowsAffected > 0) {
            shiftsId = adShiftsMapper.selectOne(new QueryWrapper<AdShifts>().eq("shifts_name", adShifts.getShiftsName())).getAdShiftsId();
            adShiftsElasticMapper.delete(new QueryWrapper<AdShiftsElastic>().eq("ad_shifts_id", adShifts.getAdShiftsId()));
            adShiftsFrameMapper.delete(new QueryWrapper<AdShiftsFrame>().eq("ad_shifts_id", adShifts.getAdShiftsId()));
        }

        //2.修改 ad_shifts_elastic 表
        try {
            //2.1 批量插入
            List<AdShiftsElastic> adShiftsEalsticList = adShifts.getAdShiftsElasticList();
            // 设置每个AdShiftsElastic对象的adShiftsId为 shiftsId
            for (AdShiftsElastic elastic : adShiftsEalsticList) {
                elastic.setAdShiftsId(shiftsId);
            }
            log.error("转换后的adShiftsElasticList:" + adShiftsEalsticList);
            //        //2.2 异步处理（可选）
//        ExecutorService executor = Executors.newFixedThreadPool(5);
//
//        adShiftsDTO.getAdShiftsEalsticList().forEach(elastic -> {
//            executor.submit(() -> {
//                AdShiftsElastic adShiftsElastic = adShiftsUtil.convertObjectToElastic(elastic);
//                adShiftsElasticMapper.insert(adShiftsElastic);
//            });
//        });
//
//        executor.shutdown(); // 确保在应用关闭时等待所有任务完成
            //2.2 批量新增ad_shifts_elastic表
            adShiftsElasticMapper.insertBatchSomeColumn(adShiftsEalsticList);
        } catch (RuntimeException runtimeException) {
            rabbitTemplate.convertAndSend("shifts.direct", "shiftsElastic", "通知rabbitMq 进行 修改 ad_shifts_elastic 表 失败", new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    message.getMessageProperties().setExpiration("10000");
                    return message;
                }
            });
            log.info("消息发送成功！");
        }

        //3.修改 ad_shifts_frame 表
        try {
            List<AdShiftsFrame> adShiftsFramesList = adShifts.getAdShiftsFramesList();
            final long tempShiftsId = shiftsId; // 创建临时final变量
            adShiftsFramesList.forEach(adShiftsFrame -> {
                adShiftsFrame.setAdShiftsId(tempShiftsId);
            });
            log.error("转换后的adShiftsFramesList:" + adShiftsFramesList);
            //3.1 批量新增ad_shifts_frame表
            adShiftsFrameMapper.insertBatchSomeColumn(adShiftsFramesList);
        } catch (RuntimeException runtimeException) {
            rabbitTemplate.convertAndSend("shifts.direct", "shiftsFrame", "通知rabbitMq 进行 修改 ad_shifts_frame 表 失败", new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    message.getMessageProperties().setExpiration("10000");
                    return message;
                }
            });
            log.info("消息发送成功！");
        }

    }
}
