package com.umit.mapper;

import com.umit.dto.request.AdminUpdateManagerRequestDto;
import com.umit.dto.request.SaveManagerRequestDto;
import com.umit.dto.request.UpdateManagerRequestDto;
import com.umit.dto.response.ManagerResponseDto;
import com.umit.dto.response.SaveManagerResponseDto;
import com.umit.entity.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ManagerMapper {

    ManagerMapper INSTANCE = Mappers.getMapper(ManagerMapper.class);


    Manager fromSaveManagerRequestDtoToManager(final SaveManagerRequestDto dto);

    SaveManagerResponseDto fromManagerToSaveManagerResponseDto(final Manager manager);

    Manager fromUpdateManagerRequestDtoToManager(final UpdateManagerRequestDto dto);

    Manager fromAdminUpdateManagerRequestDtoToManager(final AdminUpdateManagerRequestDto dto);


    //@Mapping(source = "id",target = "managerId")
    ManagerResponseDto fromManagerToManagerResponseDto(final Manager manager);

}
