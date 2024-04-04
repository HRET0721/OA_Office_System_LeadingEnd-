package org.hret.service.personnel.assess;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import org.hret.entity.personnel.assess.Assess;
import org.hret.pojo.JsonResult;

/**
 * @author HRET
 */
public interface AssessService  extends IService<Assess> {

    PageInfo<Assess> findAssessListAndPage(Assess assess);

    JsonResult addAssessMent(Assess assess);

    JsonResult deleteAssessMent(Long assessId);

    Assess findAeeseeMentById(Long assessId);

    JsonResult updateAssessMent(Assess assess);

    PageInfo<Assess> findAssessAndUserListAndPage(Assess assess);
}
