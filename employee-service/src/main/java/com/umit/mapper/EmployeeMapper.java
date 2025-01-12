package com.umit.mapper;

import com.umit.dto.request.ManagerOrAdminUpdateEmployeeRequestDto;
import com.umit.dto.request.SaveEmployeeRequestDto;
import com.umit.dto.request.UpdateEmployeeRequestDto;
import com.umit.dto.response.EmployeeResponseDto;
import com.umit.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    Employee fromUpdateEmployeeRequestDtoToEmployee(final UpdateEmployeeRequestDto dto);

    Employee fromManagerOrAdminUpdateEmployeeRequestDtoToEmployee(final ManagerOrAdminUpdateEmployeeRequestDto dto);

    Employee fromSaveEmployeeRequestDtoToEmployee(final SaveEmployeeRequestDto dto);

    EmployeeResponseDto fromEmployeeToEmployeeResponseDto(final Employee employee);

}
