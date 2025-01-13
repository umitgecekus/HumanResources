package com.umit.mapper;

import com.umit.dto.response.AddExpensesResponseDto;
import com.umit.entity.Expenses;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExpensesMapper {

    ExpensesMapper INSTANCE = Mappers.getMapper(ExpensesMapper.class);


    AddExpensesResponseDto fromExpensesToAddExpensesResponseDto(final Expenses expense);

}
