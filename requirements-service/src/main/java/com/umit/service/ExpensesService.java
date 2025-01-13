package com.umit.service;

import com.umit.dto.request.AddExpensesRequestDto;
import com.umit.dto.request.BaseRequestExpensesDto;
import com.umit.dto.response.AddExpensesResponseDto;
import com.umit.dto.response.EmployeeResponseDto;
import com.umit.dto.response.ManagerResponseDto;
import com.umit.entity.Expenses;
import com.umit.exception.ErrorType;
import com.umit.exception.RequirementsServiceException;
import com.umit.manager.EmployeeManager;
import com.umit.manager.ManagerManager;
import com.umit.mapper.ExpensesMapper;
import com.umit.repository.ExpensesRepository;
import com.umit.utility.enums.EStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpensesService {

    private final ExpensesRepository expensesRepository;
    private final EmployeeManager employeeManager;
    private final ManagerManager managerManager;
    private final ExpensesMapper expensesMapper;

    public List<Expenses> findAllExpensesForEmployee(String token) {
        EmployeeResponseDto employee = Optional.ofNullable(employeeManager.findEmployeeByToken(token).getBody())
                .orElseThrow(()->new RequirementsServiceException(ErrorType.EMPLOYEE_NOT_FOUND));

        List<Expenses> expenses = expensesRepository.findAllByEmployeeId(employee.getId());

        if (expenses.isEmpty()) {
            throw new RequirementsServiceException(ErrorType.EXPENSES_NOT_FOUND);
        }
        return expenses;
    }

    public AddExpensesResponseDto addExpensesForEmployee(AddExpensesRequestDto dto) {
        EmployeeResponseDto employee = Optional.ofNullable(employeeManager.findEmployeeByToken(dto.getToken()).getBody())
                .orElseThrow(()->new RequirementsServiceException(ErrorType.EMPLOYEE_NOT_FOUND));

        return ExpensesMapper.INSTANCE.fromExpensesToAddExpensesResponseDto(expensesRepository.save(Expenses.builder()
                .employeeId(employee.getId())
                .managerId(employee.getManagerId())
                .amount(dto.getAmount())
                .expenseType(dto.getExpenseType())
                .document(dto.getDocument())
                .requestDate(System.currentTimeMillis())
                .status(EStatus.PENDING)
                .build()));

    }

    public Boolean approveExpensesForEmployee(BaseRequestExpensesDto dto) {
        System.out.println("expenses service hata başlangıcı");
        ManagerResponseDto manager = Optional.ofNullable(managerManager.findByToken(dto.getToken()).getBody())
                .orElseThrow(() -> new RequirementsServiceException(ErrorType.MANAGER_NOT_FOUD));
        System.out.println("hata başlangııc mı????");
        Optional<Expenses> expensesOptional = expensesRepository.findById(dto.getRequirementId());
        if (expensesOptional.isPresent()) {
            Expenses expense = expensesOptional.get();
            if (expense.getManagerId().equals(manager.getId())) {
                if (expense.getStatus().equals(EStatus.PENDING)) {
                    expense.setStatus(EStatus.ACTIVE);
                    expense.setApprovalDate(System.currentTimeMillis());
                    expensesRepository.save(expense);
                    // Harcama miktarını çalışanın maaşına ekler
                    System.out.println("HATAA EXPENSES SERVİCE");
                    System.out.println("employee id: " + expense.getEmployeeId());
                    EmployeeResponseDto employee = Optional.ofNullable(employeeManager.findById(dto.getEmployeeId()).getBody())
                            .orElseThrow(()->new RequirementsServiceException(ErrorType.EMPLOYEE_NOT_FOUND));
                    System.out.println("2. hataaaaa");
                    if (employee != null ) {
                        double currentSalary = employee.getSalary() != null ? employee.getSalary() : 0.0;
                        double additionalAmount = expense.getAmount();
                        double newSalary = currentSalary + additionalAmount;
                        employee.setSalary(newSalary);
                        employeeManager.updateEmployeeSalary(employee.getId(), newSalary);
                        return true;
                    } else {
                        throw new RequirementsServiceException(ErrorType.EMPLOYEE_NOT_FOUND);
                    }
                }
            } else {
                throw new RequirementsServiceException(ErrorType.MANAGER_NOT_AUTHORIZED);
            }
        } else {
            throw new RequirementsServiceException(ErrorType.EXPENSES_NOT_FOUND);
        }
        return true;
    }

    public List<Expenses> findAllPendingExpenses(String token) {
        ManagerResponseDto manager = Optional.ofNullable(managerManager.findByToken(token).getBody())
                .orElseThrow(() -> new RequirementsServiceException(ErrorType.MANAGER_NOT_FOUD));
        return expensesRepository.findAll().stream().
                filter(expenses -> expenses.getStatus().equals(EStatus.PENDING))
                .collect(Collectors.toList());
    }

}
