package com.umit.dto.response;

import com.umit.utility.enums.EExpenseType;
import com.umit.utility.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddExpensesResponseDto {

    private Long id;
    private Long employeeId;
    private Long managerId;
    private Double amount;
    private EExpenseType expenseType;
    private String document;
    private Long requestDate;
    private EStatus status;

}
