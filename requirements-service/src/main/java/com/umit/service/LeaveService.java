package com.umit.service;

import com.umit.dto.request.AddLeaveRequestDto;
import com.umit.dto.request.BaseRequestForRequirementsDto;
import com.umit.dto.request.RequestLeaveDto;
import com.umit.dto.response.EmployeeResponseDto;
import com.umit.dto.response.ManagerResponseDto;
import com.umit.entity.Leave;
import com.umit.exception.ErrorType;
import com.umit.exception.RequirementsServiceException;
import com.umit.manager.EmployeeManager;
import com.umit.manager.ManagerManager;
import com.umit.repository.LeaveRepository;
import com.umit.utility.LeaveCalculator;
import com.umit.utility.enums.ELeaveType;
import com.umit.utility.enums.EStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LeaveService {

    private final LeaveRepository leaveRepository;
    private final LeaveCalculator leaveCalculator;
    private final EmployeeManager employeeManager;
    private final ManagerManager managerManager;

    /**
     * TODO: Bu metotlarda token authId buluyor ya, yapılan örnekler değişken baya
     * mesela authId ile managerIdsini bulup islem yapilan methotlar var genelde dto ile id almiyorlar
     * ama authId'yi managerId olarak kabul mu etmis oluyorlar onu tam anlamadim, islem yapan kisi icin
     * nasil repositoryden kisiyi bulduracagiz vs..
     * @param dto
     * @return
     */

    public Boolean addLeaveToEmployee(AddLeaveRequestDto dto) {
        ManagerResponseDto managerResponseDto = Optional.ofNullable(managerManager.findByToken(dto.getToken()).getBody())
                .orElseThrow(()->new RequirementsServiceException(ErrorType.MANAGER_NOT_FOUD));
        EmployeeResponseDto employeeResponseDto = Optional.ofNullable(employeeManager.findById(dto.getEmployeeId()).getBody())
                .orElseThrow(()->new RequirementsServiceException(ErrorType.EMPLOYEE_NOT_FOUND));

        leaveRepository.save(
                Leave.builder()
                        .managerId(managerResponseDto.getId())
                        .employeeId(employeeResponseDto.getId())
                        .employeeName(employeeResponseDto.getName())
                        .employeeSurname(employeeResponseDto.getSurname())
                        .companyId(employeeResponseDto.getCompanyName())
                        .approvalDate(System.currentTimeMillis())
                        .numberOfDays(leaveCalculator.calculateNumberOfDays(dto.getStartDate(),dto.getEndDate()))
                        .startDate(dto.getStartDate())
                        .endDate(dto.getEndDate())
                        .leaveType(dto.getLeaveType())
                        .authId(employeeResponseDto.getAuthId())
                        .status(EStatus.ACTIVE)
                        .build());
        return true;
    }

    public Boolean approveLeaveForEmployee(BaseRequestForRequirementsDto dto) {
        ManagerResponseDto managerResponseDto = Optional.ofNullable(managerManager.findByToken(dto.getToken()).getBody())
                .orElseThrow(()->new RequirementsServiceException(ErrorType.MANAGER_NOT_FOUD));

        Optional<Leave> optionalLeave = leaveRepository.findById(dto.getRequirementId());
        if(optionalLeave.get().getManagerId().equals(managerResponseDto.getId())){
            if (optionalLeave.isEmpty()) {
                throw new RequirementsServiceException(ErrorType.LEAVE_NOT_FOUND);
            }
            Leave leave = optionalLeave.get();
            leave.setStatus(EStatus.ACTIVE);
            leave.setApprovalDate(System.currentTimeMillis());
            leaveRepository.save(leave);
            return true;
        }else{
            throw new RequirementsServiceException(ErrorType.MANAGER_NOT_FOUD);
        }
    }

    public Boolean rejectLeaveForEmployee(BaseRequestForRequirementsDto dto) {
        ManagerResponseDto managerResponseDto = Optional.ofNullable(managerManager.findByToken(dto.getToken()).getBody())
                .orElseThrow(()->new RequirementsServiceException(ErrorType.MANAGER_NOT_FOUD));

        Optional<Leave> optionalLeave = leaveRepository.findById(dto.getRequirementId());
        if(optionalLeave.get().getManagerId().equals(managerResponseDto.getId())){
            if (optionalLeave.isEmpty()) {
                throw new RequirementsServiceException(ErrorType.LEAVE_NOT_FOUND);
            }
            Leave leave = optionalLeave.get();
            leave.setStatus(EStatus.PASSIVE);
            leave.setApprovalDate(System.currentTimeMillis());
            leaveRepository.save(leave);
            return true;
        }else{
            throw new RequirementsServiceException(ErrorType.MANAGER_NOT_FOUD);
        }
    }

    public List<Leave> findAllLeavesOfAnEmployee(String token, Long employeeId) {
        Optional.ofNullable(managerManager.findByToken(token).getBody())
                .orElseThrow(()->new RequirementsServiceException(ErrorType.MANAGER_NOT_FOUD));

        Optional<List<Leave>> leaveList = leaveRepository.findAllByEmployeeId(employeeId);
        if(leaveList.isEmpty()){
            throw new RequirementsServiceException(ErrorType.NO_LEAVES_FOR_AN_EMPLOYEE);
        }
        return leaveList.get();
    }


    public List<Leave> findAllPendingLeavesOfEmployees(String token) {
        ManagerResponseDto managerResponseDto =Optional.ofNullable(managerManager.findByToken(token).getBody())
                .orElseThrow(()->new RequirementsServiceException(ErrorType.MANAGER_NOT_FOUD));

        Optional<List<Leave>> leaveList = leaveRepository.findAllByManagerIdAndStatus(managerResponseDto.getId(), EStatus.PENDING);
        if(leaveList.isEmpty()){
            throw new RequirementsServiceException(ErrorType.NO_PENDING_LEAVES_FOR_MANAGER);
        }

        return leaveList.get();
    }

    public List<Leave> findAllMyLeavesForEmployee(String token) {
        EmployeeResponseDto employee = Optional.ofNullable(employeeManager.findEmployeeByToken(token).getBody())
                .orElseThrow(()->new RequirementsServiceException(ErrorType.EMPLOYEE_NOT_FOUND));

        Optional<List<Leave>> leaves= leaveRepository.findAllByEmployeeId(employee.getId());
        if (leaves.isEmpty()){
            throw new RequirementsServiceException(ErrorType.LEAVE_NOT_FOUND);
        }
        return leaves.get();
    }

    public Boolean requestLeaveFromEmployee(RequestLeaveDto dto) {

        EmployeeResponseDto responseDto = Optional.ofNullable(employeeManager.findEmployeeByToken(dto.getToken()).getBody())
                .orElseThrow(()->new RequirementsServiceException(ErrorType.EMPLOYEE_NOT_FOUND));
        if (responseDto.getId() == null){
            throw new RequirementsServiceException(ErrorType.EMPLOYEE_NOT_FOUND);
        }
        Double numberOfDays = leaveCalculator.calculateNumberOfDays(dto.getStartDate(), dto.getEndDate());
        if(numberOfDays==0){
            throw new RequirementsServiceException(ErrorType.LEAVE_DATE_NOT_VALID);
        }
        String leaveTypeString = dto.getLeaveType().toString();
        ELeaveType leaveType = ELeaveType.valueOf(leaveTypeString);
        leaveRepository.save(Leave.builder()
                .leaveType(leaveType)
                .authId(responseDto.getAuthId())
                .employeeName(responseDto.getName())
                .employeeSurname(responseDto.getSurname())
                .employeeId(responseDto.getId())
                .managerId(responseDto.getManagerId())
                .startDate(dto.getStartDate())
                .companyId(responseDto.getCompanyName())
                .endDate(dto.getEndDate())
                .numberOfDays(numberOfDays)
                .createDate(System.currentTimeMillis())
                .status(EStatus.PENDING)
                .build());
        return true;
    }

}
