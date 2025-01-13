package com.umit.mapper;

import com.umit.dto.request.CompanyCreateRequestDto;
import com.umit.dto.request.CompanyUpdateRequestDto;
import com.umit.dto.response.CompanyGetAllResponseDto;
import com.umit.dto.response.CompanyManagerResponseDto;
import com.umit.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompanyMapper {

    CompanyMapper INSTANCE= Mappers.getMapper(CompanyMapper.class);

    Company fromCompanyCreateRequestDtoToCompany(CompanyCreateRequestDto dto);

    CompanyGetAllResponseDto fromCompanyToCompanyGetAllResponseDto(final Company company);

    CompanyManagerResponseDto fromCompanyToCompanyManagerResponseDto(final Company company);


    Company fromCompanyUpdateRequestDtoToCompany(CompanyUpdateRequestDto dto);

    CompanyUpdateRequestDto fromCompanyToCompanyUpdateRequestDto(final Company company);

}
