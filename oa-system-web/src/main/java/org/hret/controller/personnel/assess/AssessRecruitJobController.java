package org.hret.controller.personnel.assess;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "assessRecruitCandidate")
@Tag(name = "职位和考核方法", description = "职位和考核方法")
public class AssessRecruitJobController {
}
