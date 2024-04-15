package org.hret.service.personnel.impl.attendance.shifts;

/**
 * @version oa-system
 * @developer breath
 * @User breath
 * @date 2024/4/9 14:50
 */


import org.hret.entity.personnel.attendance.shifts.AdShifts;
import org.hret.entity.personnel.attendance.shifts.elastic.AdShiftsElastic;
import org.hret.entity.personnel.attendance.shifts.frame.AdShiftsFrame;
import org.hret.entity.personnel.attendance.vo.AdShiftsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
public class AdShiftsReverseUtil {

    public AdShiftsVO mapEntityToVo(AdShifts entity, AdShiftsVO vo) {
        // 使用 Spring BeanUtils 进行属性复制
        BeanUtils.copyProperties(entity, vo,getIgnoredProperties());

        // 处理自定义转换逻辑（如果有）
        return convertInternalLists(entity, vo);
    }

    private String[] getIgnoredProperties() {
        // 返回需要忽略复制的属性列表
        return new String[]{
                "adShiftsFramesList",
                "adShiftsElasticList"
        };
    }

    /**
     * 重写转换逻辑，将 AdShiftsVO 中的 adShiftsFrameTimeList 和 adShiftsElasticList 转换成所需格式。
     *
     * @param adShifts 待转换的 AdShiftsVO 对象
     * @return 转换后的 AdShiftsVO 对象
     */
    public static AdShiftsVO convertInternalLists(AdShifts adShifts,AdShiftsVO dto) {

        // 转换 adShiftsFrameTimeList
        List<Object> transformedFrameTimeList = convertAdShiftsFrameTimeListRe(adShifts.getAdShiftsFramesList());

        // 转换 adShiftsElasticList
        List<Object> transformedElasticList = convertAdShiftsElasticList(adShifts.getAdShiftsElasticList());

        // 更新 AdShiftsVO 对象
        dto.setAdShiftsFrameTimeList(transformedFrameTimeList);
        dto.setAdShiftsElasticList(transformedElasticList);

        return dto;
    }
    

    //该方法存在bug
    private static List<Object> convertAdShiftsFrameTimeList(List<AdShiftsFrame> frames) {
        return frames.stream()
                .collect(Collectors.groupingBy(AdShiftsFrame::getType))
                .entrySet().stream()
                .map(entry -> {
                    Map<String, Object> frameInfo = new HashMap<>();
                    frameInfo.put("id", entry.getKey());
                    frameInfo.put("frameTimeAndRulesUp", entry.getValue().stream()
                            .filter(frame -> frame.getType() == 1)
                            .findFirst()
                            .orElse(null));
                    frameInfo.put("frameTimeAndRulesDown", entry.getValue().stream()
                            .filter(frame -> frame.getType() == 2)
                            .findFirst()
                            .orElse(null));
                    return frameInfo;
                })
                .collect(Collectors.toList());
    }

    private static List<Object> convertAdShiftsFrameTimeListRe(List<AdShiftsFrame> frames) {
        // 初始化结果列表
        List<Object> result = new ArrayList<>();

        // 定义一个临时变量，用于存储当前正在处理的 `day`（天数）对应的 `AdShiftsFrame` 列表
        Map<String, List<AdShiftsFrame>> framesByDay = new HashMap<>();

        // 遍历所有 `AdShiftsFrame` 对象
        for (AdShiftsFrame frame : frames) {
            // 获取当前帧的 `day`（天数）
            String day = frame.getDay();

            // 将当前帧按 `day` 添加到对应的列表中
            framesByDay.computeIfAbsent(day, k -> new ArrayList<>()).add(frame);
        }

        // 遍历按 `day` 分组后的列表
        for (Map.Entry<String, List<AdShiftsFrame>> entry : framesByDay.entrySet()) {
            String day = entry.getKey();
            List<AdShiftsFrame> dayFrames = entry.getValue();

            // 按 `type` 分组，分别获取 `type` 为 1 和 2 的帧
            List<AdShiftsFrame> upFrames = dayFrames.stream()
                    .filter(frame -> frame.getType() == 1)
                    .collect(Collectors.toList());
            List<AdShiftsFrame> downFrames = dayFrames.stream()
                    .filter(frame -> frame.getType() == 2)
                    .collect(Collectors.toList());

            // 创建一个 `Map<String, Object>` 用于存储当前 `day` 的帧信息
            Map<String, Object> frameInfo = new HashMap<>();
            frameInfo.put("id", result.size() + 1); // 设置唯一的 ID

            // 将 `type` 为 1 的帧信息放入 `frameTimeAndRulesUp`
            Map<String, Object> frameTimeAndRulesUp = new HashMap<>();
            if (!upFrames.isEmpty()) {
                AdShiftsFrame upFrame = upFrames.get(0);
                frameTimeAndRulesUp.put("day", day);
                frameTimeAndRulesUp.put("day_time", upFrame.getDayTime());
//                frameTimeAndRulesUp.put("day_allow", upFrame.getDayAllow());
                frameTimeAndRulesUp.put("day_allow", upFrame.getDayAllow() == 1); // 将 dayAllow 转换为布尔值
                frameTimeAndRulesUp.put("most", upFrame.getMost());
                frameTimeAndRulesUp.put("late", upFrame.getLate());
                frameTimeAndRulesUp.put("lake", upFrame.getLake());
                frameTimeAndRulesUp.put("type", upFrame.getType());
                frameTimeAndRulesUp.put("ad_shifts_id", upFrame.getAdShiftsId());
//                frameTimeAndRulesUp.put("dayAllow", upFrame.getDayAllow() == 1); // 将 dayAllow 转换为布尔值

                frameInfo.put("frameTimeAndRulesUp", frameTimeAndRulesUp);
            } else {
                frameInfo.put("frameTimeAndRulesUp", null);
            }

            // 将 `type` 为 2 的帧信息放入 `frameTimeAndRulesDown`
            Map<String, Object> frameTimeAndRulesDown = new HashMap<>();
            if (!downFrames.isEmpty()) {
                AdShiftsFrame downFrame = downFrames.get(0);
                frameTimeAndRulesDown.put("day", day);
                frameTimeAndRulesDown.put("day_time", downFrame.getDayTime());
//                frameTimeAndRulesDown.put("day_allow", downFrame.getDayAllow());
                frameTimeAndRulesDown.put("day_allow", downFrame.getDayAllow() == 1); // 将 dayAllow 转换为布尔值
                frameTimeAndRulesDown.put("most", downFrame.getMost());
                frameTimeAndRulesDown.put("late", downFrame.getLate());
                frameTimeAndRulesDown.put("lake", downFrame.getLake());
                frameTimeAndRulesDown.put("type", downFrame.getType());
                frameTimeAndRulesDown.put("ad_shifts_id", downFrame.getAdShiftsId());
//                frameTimeAndRulesDown.put("dayAllow", downFrame.getDayAllow() == 1); // 将 dayAllow 转换为布尔值

                frameInfo.put("frameTimeAndRulesDown", frameTimeAndRulesDown);
            } else {
                frameInfo.put("frameTimeAndRulesDown", null);
            }

            // 将当前 `day` 的帧信息添加到结果列表中
            result.add(frameInfo);
        }

        return result;
    }

    private static List<Object> convertAdShiftsElasticList(List<AdShiftsElastic> elastics) {
        AtomicInteger idCounter = new AtomicInteger(1);
        return elastics.stream()
                .map(elastic -> {
                    Map<String, Object> elasticInfo = new HashMap<>();
                    elasticInfo.put("id",idCounter.getAndIncrement());
                    elasticInfo.put("go", elastic.getGo());
                    elasticInfo.put("late", elastic.getLate());
                    return elasticInfo;
                })
                .collect(Collectors.toList());
    }


}

