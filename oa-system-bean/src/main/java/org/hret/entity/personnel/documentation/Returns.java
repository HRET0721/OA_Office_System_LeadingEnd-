package org.hret.entity.personnel.documentation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hret.entity.utils.query.Page;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@TableName(value = "returns")
public class Returns extends Page implements Serializable {
    @Serial
    private static final long serialVersionUID = -49452775890884099L;
    @TableId(value = "return_id", type = IdType.AUTO)
    private Integer returnId;

    /**
     * 归还人
     */
    private String returnName;

    /**
     * 归还日期
     */
    private Date returnDate;

    /**
     * 状态(1(审批中)2(已同意)3(已拒绝)4(已撤销))
     */
    private String states;

    private Date submissionssTimes;

    private Integer documentationId;

    @TableField(exist = false)
    private Integer fileNumber;
    @TableField(exist = false)
    private String fileTitle;
}
