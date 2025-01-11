package com.umit.mapper;

import com.umit.dto.request.UpdateAdminRequestDto;
import com.umit.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdminMapper {
    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);

    Admin fromUpdateAdminRequestDtoToAdmin(final UpdateAdminRequestDto dto);
}
