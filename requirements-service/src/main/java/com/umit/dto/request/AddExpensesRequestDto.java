package com.umit.dto.request;

import com.umit.utility.enums.EExpenseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddExpensesRequestDto {

    private String token;
    private Double amount;
    private EExpenseType expenseType;
    private String document;

}
