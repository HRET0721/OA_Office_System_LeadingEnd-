package org.hret.service.personnel.impl.attendance.shifts;

/**
 * @version oa-system
 * @developer breath
 * @User breath
 * @date 2024/4/3 9:57
 */
import org.hret.entity.personnel.attendance.dto.AdShiftsDTO;
import org.hret.entity.personnel.attendance.shifts.AdShifts;
import org.hret.entity.personnel.attendance.shifts.elastic.AdShiftsElastic;
import org.hret.entity.personnel.attendance.shifts.frame.AdShiftsFrame;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class AdShiftsUtil {

    public AdShifts mapDtoToEntity(AdShiftsDTO dto, AdShifts entity) {
        // 使用 Spring BeanUtils 进行属性复制
        BeanUtils.copyProperties(dto, entity, getIgnoredProperties());

        // 处理自定义转换逻辑（如果有）
        return handleCustomMappings(dto, entity);
    }
    public AdShifts mapDtoToEntityPro(AdShiftsDTO dto, AdShifts entity) {
        // 使用 Spring BeanUtils 进行属性复制
        BeanUtils.copyProperties(dto, entity, getIgnoredProperties());

        // 处理自定义转换逻辑（如果有）
        return handleCustomMappingsPro(dto, entity);
    }

    private String[] getIgnoredProperties() {
        // 返回需要忽略复制的属性列表
        return new String[]{
                "adShiftsFrameTimeList",
                "adShiftsElasticList",
                "shiftsElasticAllowable",
                "shiftsElasticOvertime",
                "shiftsElasticOvertimeAllowable"
        };
    }

    private AdShifts handleCustomMappings(AdShiftsDTO dto, AdShifts entity) {
        // 在这里处理 DTO 中的 List<Object> 类型字段与 Entity 中的 List<具体类型> 字段之间的映射
//        if (dto.getAdShiftsFrameTimeList() != null) {
//            // 根据实际情况进行转换并赋值
//            entity.setAdShiftsFramesList(convertFrameTimeList(dto.getAdShiftsFrameTimeList()));
//        }

        if (dto.getAdShiftsElasticList() != null) {
            // 根据实际情况进行转换并赋值
            entity.setAdShiftsElasticList(convertElasticList(dto.getAdShiftsElasticList()));
        }
        return entity;
    }

    private AdShifts handleCustomMappingsPro(AdShiftsDTO dto, AdShifts entity) {
        // 在这里处理 DTO 中的 List<Object> 类型字段与 Entity 中的 List<具体类型> 字段之间的映射
        if (dto.getAdShiftsFrameTimeList() != null) {
            // 根据实际情况进行转换并赋值
            entity.setAdShiftsFramesList(convertFrameTimeList(dto.getAdShiftsFrameTimeList()));
        }

        if (dto.getAdShiftsElasticList() != null) {
            // 根据实际情况进行转换并赋值
            entity.setAdShiftsElasticList(convertElasticList(dto.getAdShiftsElasticList()));
        }
        return entity;
    }

    public List<AdShiftsFrame> convertFrameTimeList(List<Object> frameTimeObjects) {
        // 实现从 Object 到 AdShiftsFrame 的转换逻辑，返回转换后的 List<AdShiftsFrame>
        // 示例代码仅为占位，实际应根据数据结构和业务需求进行调整
        return frameTimeObjects.stream()
                .map(this::convertObjectToFrame)
                .collect(Collectors.toList());
    }

    private AdShiftsFrame convertObjectToFrame(Object object) {
        // 将单个 Object 对象转换为 AdShiftsFrame 对象并返回
        // 示例代码仅为占位，实际应根据数据结构和业务需求进行调整
        AdShiftsFrame frame = new AdShiftsFrame();
        // ... 实现转换逻辑 ...

        // 假设object是一个Map<String, Object>，实际类型取决于前端传来的数据结构
        Map<String, Object> objMap = (Map<String, Object>) object; // 请替换为实际类型转换

        // 设置 day
        Object dayObj = objMap.get("day");
        if (dayObj instanceof String) {
            frame.setDay((String) dayObj);
        } else {
            throw new IllegalArgumentException("Invalid type for field 'day': Expected String, but found " + dayObj.getClass().getName());
        }

        // 设置 dayTime
        Object dayTimeObj = objMap.get("day_time");
        if (dayTimeObj instanceof String) {
            frame.setDayTime((String) dayTimeObj);
        } else {
            throw new IllegalArgumentException("Invalid type for field 'day_time': Expected String, but found " + dayTimeObj.getClass().getName());
        }

        // 设置 dayAllow
        Object dayAllowObj = objMap.get("day_allow");
        if (dayAllowObj instanceof String) {
            try {
                frame.setDayAllow(Long.parseLong((String) dayAllowObj));
            } catch (NumberFormatException e) {
                // 处理转换失败的情况
                e.printStackTrace();
                // 可以记录日志、抛出自定义异常等
            }
        } else if (dayAllowObj instanceof Number) {
            Number numberDayAllow = (Number) dayAllowObj;
            frame.setDayAllow(numberDayAllow.longValue());
        }else if (dayAllowObj instanceof Boolean) {
            if ((Boolean) dayAllowObj) {
                frame.setDayAllow(1L);
            } else {
                frame.setDayAllow(0L);
            }
        }
        else {
            throw new IllegalArgumentException("Invalid type for field 'day_allow': Expected String or Long, but found " + dayAllowObj.getClass().getName());
        }

        // 设置 most
        Object mostObj = objMap.get("most");
        if (mostObj instanceof Number) {
            Number numberMost = (Number) mostObj;
            frame.setMost(numberMost.longValue());
        } else {
            throw new IllegalArgumentException("Invalid type for field 'most': Expected String or Long, but found " + mostObj.getClass().getName());
        }

        // 设置 late
        Object lateObj = objMap.get("late");
        if (lateObj instanceof Number) {
            Number numberLate = (Number) lateObj;
            frame.setLate(numberLate.longValue());
        } else {
            throw new IllegalArgumentException("Invalid type for field 'late': Expected String or Long, but found " + lateObj.getClass().getName());
        }

        // 设置 lake
        Object lakeObj = objMap.get("lake");
        if (lakeObj instanceof Number) {
            Number numberLake = (Number) lakeObj;
            frame.setLake(numberLake.longValue());
        } else {
            throw new IllegalArgumentException("Invalid type for field 'lake': Expected String or Long, but found " + lakeObj.getClass().getName());
        }


        // 设置 type
        Object typeObject = objMap.get("type");
        if (typeObject instanceof Number) {
            Number numberType = (Number) typeObject;
            frame.setType(numberType.longValue());
        } else if (typeObject instanceof String) {
            String typeString = (String) typeObject;
            try {
                long typeValue = Long.parseLong(typeString);
                frame.setType(typeValue);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid type for field 'type': Expected String or Long, but found " + typeObject.getClass().getName());
            }
        } else {
            throw new IllegalArgumentException("Invalid type for field 'type': Expected String or Long, but found " + typeObject.getClass().getName());
        }

        return frame;
    }



    public List<AdShiftsElastic> convertElasticList(List<Object> elasticObjects) {
        // 实现从 Object 到 AdShiftsElastic 的转换逻辑，返回转换后的 List<AdShiftsElastic>
        // 示例代码仅为占位，实际应根据数据结构和业务需求进行调整
        return elasticObjects.stream()
                .map(this::convertObjectToElastic)
                .collect(Collectors.toList());
    }

    public AdShiftsElastic convertObjectToElastic(Object object) {
        // 将单个 Object 对象转换为 AdShiftsElastic 对象并返回
        AdShiftsElastic elastic = new AdShiftsElastic();

        // 假设object是一个Map<String, Object>，实际类型取决于前端传来的数据结构
        Map<String, Object> objMap = (Map<String, Object>) object; // 请替换为实际类型转换

        // 转换 go 字段
        Object goObj = objMap.get("go");

        if (goObj instanceof Integer) {
            elastic.setGo(((Integer) goObj).doubleValue()); // 直接将 Integer 转换为 Double
        } else {
            throw new IllegalArgumentException("Invalid type for field 'go': Expected Integer, but found " + goObj.getClass().getName());
        }


        // 转换 late 字段，逻辑同上
        Object lateStr = objMap.get("late");
        try {
            elastic.setLate(((Integer) lateStr).doubleValue());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid value for field 'late': " + lateStr, e);
        }

        // ... 实现其他字段的转换逻辑 ...

        return elastic;
    }
}