package com.umit.mapper;

import com.umit.dto.request.RegisterEmployeeRequestDto;
import com.umit.dto.request.RegisterManagerRequestDto;
import com.umit.dto.response.AuthResponseDto;
import com.umit.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {

    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

    Auth fromRegisterManagerRequestDtoToAuth(final RegisterManagerRequestDto dto);

    Auth fromRegisterEmployeeRequestDtoToAuth(final RegisterEmployeeRequestDto dto);


    AuthResponseDto fromAuthToAuthResponseDto(final Auth auth);

}
