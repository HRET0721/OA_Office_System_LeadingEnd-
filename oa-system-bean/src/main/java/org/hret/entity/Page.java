package org.hret.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author:HRET Milky Way
 * Date:2024/3/19
 * version:1.0
 * @author HRET
 * 分页实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page {

    @TableField(exist = false)
    private Integer pageNum;

    @TableField(exist = false)
    private Integer pageSize;

}
