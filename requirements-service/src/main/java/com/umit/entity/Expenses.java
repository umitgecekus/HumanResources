package com.umit.entity;

import com.umit.utility.enums.EExpenseType;
import com.umit.utility.enums.EStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_expenses")
public class Expenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long employeeId;
    private Long managerId;
    private Double amount;
    private EExpenseType expenseType;
    private String document;
    private Long requestDate;
    private Long approvalDate;
    private EStatus status;

}
