package com.umit.controller.EquipmentsController.Employee;

import com.umit.dto.request.BaseRequestForRequirementsDto;
import com.umit.dto.request.EquipmentRequestDto;
import com.umit.dto.response.BasicResponse;
import com.umit.entity.Equipments;
import com.umit.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.umit.utility.constants.RestApiUrls.*;

@RestController
@RequestMapping(EMPLOYEE)
@RequiredArgsConstructor
public class EquipmentsEmployeeController {

    private final EquipmentService equipmentService;

    @GetMapping(GET_ALL_EQUIPMENT_OF_EMPLOYEE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<List<Equipments>>> findAllMyEquipments(@RequestParam String token) {

        return ResponseEntity.ok(BasicResponse.<List<Equipments>>builder()
                .status(200)
                .message("Equipments of an Employee are successfully found.")
                .data(equipmentService.findAllMyEquipmentsForEmployee(token))
                .build());
    }

    @PutMapping(REQUEST_EQUIPMENT)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> requestLeave(@RequestBody EquipmentRequestDto dto) {
        equipmentService.requestEquipmentFromEmployee(dto);
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Equipment requested by employee successfully.")
                .data(true)
                .build());
    }

    @PutMapping(RETURN_EQUIPMENT)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> returnEquipment(@RequestBody BaseRequestForRequirementsDto dto) {
        equipmentService.returnEquipmentFromEmployee(dto);
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Equipment returned by employee successfully.")
                .data(true)
                .build());
    }

}
