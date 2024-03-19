package org.hret.service.personnel.assess;

import com.baomidou.mybatisplus.extension.service.IService;
import org.hret.entity.personnel.assess.Assess;
import org.hret.pojo.JsonResult;

public interface AssessService  extends IService<Assess> {
    JsonResult findAssessListAndPage(Assess assess, Integer pageNum, Integer pageSize);


    void addAssessMent(Assess assess);

    void deleteAssessMent(Long assessId);

    Assess findAeeseeMentById(Long assessId);

    void updateAssessMent(Assess assess);

}
