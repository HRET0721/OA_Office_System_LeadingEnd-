package org.hret.entity.administrative.seal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hret.entity.utils.query.Page;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.io.Serializable;

/**
 * (SLibrary)实体类
 *
 * @author makejava
 * @since 2024-04-15 09:53:24
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class SLibrary extends Page implements Serializable {
    private static final long serialVersionUID = 172600324205860495L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
/**
     * 印章名称
     */
    private String sName;
/**
     * 责任人
     */
    private String responsible;
/**
     * 状态
     */
    private Integer state;
/**
     * 备注
     */
    private String remark;
/**
     * 印章封存
     */
    private String sSealing;
/**
     * 创建时间
     */
    private Date creationTime;



}

