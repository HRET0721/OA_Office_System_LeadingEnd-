package org.hret.entity.personnel.attendance.patch;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * ad_patch  补卡信息表(AdPatch)实体类
 *
 * @author makejava
 * @since 2024-03-25 19:50:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AdPatch implements Serializable {
    @Serial
    private static final long serialVersionUID = 931134101368293590L;

    @TableId(value = "ad_patch_id", type = IdType.AUTO)
    private Long adPatchId;
    /**
     * 规则名称
     */
    private String adPatchName;
    /**
     * 应用范围
     * <p>
     * 存储规则:
     * 1: 全公司考勤
     * 2: 技术部考勤
     * 3: 技术部先锋组考勤
     * 4: 产品部考勤
     * 5: 销售部考勤
     */
    private String adPatchScope;
    /**
     * 负责人
     * <p>
     * 存储规则:
     * 存储为 worker 表中的 id
     */
    private Long workerId;
    /**
     * 允许补卡
     * <p>
     * 存储规则:
     * 1: 允许补卡
     * 2: 不允许补卡
     */
    private Long patchAllow;
    /**
     * 补卡次数
     */
    private Long patchNum;
    /**
     * 补卡天数
     */
    private Long patchDays;
    /**
     * 补卡类型
     * <p>
     * 存储规则:
     * 1: 缺卡
     * 2: 迟到
     * 3: 早退
     * 4: 正常
     * ( 多状态下以逗号隔开 )
     * <p>
     * 例子:
     * 1,2,3,
     * 1,3,4
     */
    private String patchType;


}