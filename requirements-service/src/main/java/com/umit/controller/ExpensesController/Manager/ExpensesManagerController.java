package com.umit.controller.ExpensesController.Manager;

import com.umit.dto.request.BaseRequestExpensesDto;
import com.umit.dto.response.BasicResponse;
import com.umit.entity.Expenses;
import com.umit.service.ExpensesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import  static com.umit.utility.constants.RestApiUrls.*;

@RestController
@RequestMapping(MANAGER)
@RequiredArgsConstructor
public class ExpensesManagerController {

    private final ExpensesService expensesService;

    @PutMapping(APPROVE_EXPENSES)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> approveExpenses(@RequestBody BaseRequestExpensesDto dto){
        expensesService.approveExpensesForEmployee(dto);
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Expenses approved.")
                .data(true)
                .build());
    }

    @GetMapping(FIND_ALL_PENDING_EXPENSES)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<List<Expenses>>> findAllPendingExpenses(@RequestParam String token){
        return ResponseEntity.ok(BasicResponse.<List<Expenses>>builder()
                .status(200)
                .data(expensesService.findAllPendingExpenses(token))
                .message("Pending expenses found.")
                .build());
    }

}
