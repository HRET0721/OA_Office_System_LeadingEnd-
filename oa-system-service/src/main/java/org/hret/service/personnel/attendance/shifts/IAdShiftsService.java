package org.hret.service.personnel.attendance.shifts;

import com.baomidou.mybatisplus.extension.service.IService;
import org.hret.entity.personnel.attendance.shifts.AdShifts;
import org.hret.entity.personnel.attendance.dto.AdShiftsDTO;
import org.hret.entity.personnel.attendance.vo.AdShiftsVO;

import java.util.List;

public interface IAdShiftsService extends IService<AdShifts> {
    List<AdShifts> findAdShiftsPage();

    AdShifts findAdShitsByID(Long adShiftsId);

    AdShiftsVO findAdShitsByIDPro(Long adShiftsId);

    void addAdShifts(AdShiftsDTO adShiftsDto);

    void addAdShiftsMq(AdShiftsDTO adShiftsDto);

    void addAdShiftsMqPro(AdShiftsDTO adShiftsDto);

    void updateAdShifts(AdShiftsDTO adShiftsDto);

}
