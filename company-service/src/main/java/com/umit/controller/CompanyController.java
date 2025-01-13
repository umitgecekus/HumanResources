package com.umit.controller;

import com.umit.dto.request.CompanyCreateRequestDto;
import com.umit.dto.request.CompanyTokenRequestDto;
import com.umit.dto.request.CompanyUpdateRequestDto;
import com.umit.dto.response.BasicResponse;
import com.umit.dto.response.CompanyGetAllResponseDto;
import com.umit.dto.response.CompanyManagerResponseDto;
import com.umit.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.umit.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(COMPANY)
public class CompanyController {

    private final CompanyService companyService;


    /**
     * Yeni bir şirket oluşturmayı sağlayan methodtur.
     * @param dto
     * @return
     */
    @PostMapping(CREATE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> createCompany(@RequestBody CompanyCreateRequestDto dto) {
        companyService.createCompany(dto);
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Company created successfully")
                .data(true)
                .build()
        );
    }

    /**
     * Bu method şirketin ID'si ile doğrulama yaparak bilgilerini güncellemeyi sağlar.
     * @param dto -> CompanyUpdateRequestDto
     * @return -> true, false
     */
    @PutMapping(UPDATE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> updateCompany(@RequestBody CompanyUpdateRequestDto dto) {
        companyService.updateCompany(dto);
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Company updated successfully")
                .data(true)
                .build()
        );
    }

    /**
     * Bu method ile şirketlerin listesi getirilir.
     * @return -> List<CompanyGetAllResponseDto>
     */
    @GetMapping(GET_ALL)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<List<CompanyGetAllResponseDto>>> getAll(@RequestParam String token) {
        return ResponseEntity.ok(BasicResponse.<List<CompanyGetAllResponseDto>>builder()
                .status(200)
                .message("Companies get all successfully")
                .data(companyService.getAll(token))
                .build());
    }


    /**
     * Bu method sitede bulunan toplam şirket sayısını verir.
     * @return -> Long(şirket sayısı)
     */
    @GetMapping(GET_ALL_COMPANY_COUNT)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Long>> getAllCompanyCount(@RequestParam String token) {
        return ResponseEntity.ok(BasicResponse.<Long>builder()
                .status(200)
                .message("Company count get all successfully")
                .data(companyService.getAll(token).stream().count())
                .build()
        );
    }

    /**
     * Site yöneticisinin başvuruda bulunmuş şirketleri görüntülemesini sağlar.
     * @return
     */

    @GetMapping(GET_ALL_PENDING_COMPANIES)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<List<CompanyManagerResponseDto>>> getAllPendingCompanies(@RequestParam String token) {
        return ResponseEntity.ok(BasicResponse.<List<CompanyManagerResponseDto>>builder()
                .status(200)
                .message("Pending Companies received all successfully")
                .data(companyService.getAllPendingCompanies(token))
                .build());
    }

    /**
     * Site yöneticisinin başvuruda bulunan şirketleri onaylamasını sağlayan methodtur.
     * @param
     * @return
     */
    @PostMapping(APPROVE_COMPANY)
    @CrossOrigin("*")
    // @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BasicResponse<Boolean>> approveCompany(@RequestBody CompanyTokenRequestDto dto) {
        companyService.approveCompany(dto);
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Company approved successfully")
                .data(true)
                .build());
    }

    @PostMapping(REJECT_COMPANY )
    @CrossOrigin("*")
    // @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BasicResponse<Boolean>> rejectCompany(@RequestBody CompanyTokenRequestDto dto) {
        System.out.println("dto burada.....:  "+dto);
        companyService.rejectCompany(dto);
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Company rejected.")
                .data(true)
                .build());
    }

}
