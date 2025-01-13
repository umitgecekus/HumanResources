package com.umit.service;

import com.umit.dto.request.CompanyCreateRequestDto;
import com.umit.dto.request.CompanyTokenRequestDto;
import com.umit.dto.request.CompanyUpdateRequestDto;
import com.umit.dto.response.AuthResponseDto;
import com.umit.dto.response.CompanyGetAllResponseDto;
import com.umit.dto.response.CompanyManagerResponseDto;
import com.umit.dto.response.ManagerResponseDto;
import com.umit.entity.Company;
import com.umit.exception.CompanyServiceException;
import com.umit.exception.*;
import com.umit.manager.AuthManager;
import com.umit.manager.ManagerManager;
import com.umit.mapper.CompanyMapper;
import com.umit.rabbitmq.model.ApproveManagerModel;
import com.umit.rabbitmq.model.RejectManagerModel;
import com.umit.rabbitmq.producer.ApproveManagerProducer;
import com.umit.rabbitmq.producer.RejectManagerProducer;
import com.umit.repository.CompanyRepository;
import com.umit.utility.enums.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final ApproveManagerProducer approveManagerProducer;
    private final RejectManagerProducer rejectManagerProducer;
    private final AuthManager authManager;
    private final ManagerManager managerManager;



    public void createCompany(CompanyCreateRequestDto dto) {
        companyRepository.save(CompanyMapper.INSTANCE.fromCompanyCreateRequestDtoToCompany(dto));
    }

    public Boolean updateCompany(CompanyUpdateRequestDto dto) {

        ManagerResponseDto manager = Optional.ofNullable(managerManager.findByToken(dto.getToken()).getBody())
                .orElseThrow(()->new CompanyServiceException(ErrorType.USER_NOT_FOUND));

        Company company = companyRepository.findById(dto.getId()).orElseThrow(() -> new CompanyServiceException(ErrorType.COMPANY_NOT_FOUND));
        company.setId(company.getId());
        company.setManagerId(manager.getId());
        company.setName(dto.getName());
        company.setTitle(dto.getTitle());
        company.setDescription(dto.getDescription());
        company.setAddress(dto.getAddress());
        company.setPhone(dto.getPhone());
        company.setEmail(dto.getEmail());
        company.setWebsite(dto.getWebsite());
        company.setLogo(dto.getLogo());
        company.setSector(dto.getSector());
        company.setTaxNumber(dto.getTaxNumber());
        company.setTaxOffice(dto.getTaxOffice());
        company.setMersisNo(dto.getMersisNo());
        company.setVision(dto.getVision());
        company.setMission(dto.getMission());
        company.setCountry(dto.getCountry());
        company.setCity(dto.getCity());
        company.setEmployeeCount(dto.getEmployeeCount());
        company.setFounded(dto.getFounded());
        company.setFoundingYear(dto.getFoundingYear());
        company.setLinkedin(dto.getLinkedin());
        company.setMembershipPlan(dto.getMembershipPlan());
        company.setUpdateAt(System.currentTimeMillis());
        company.setStatus(EStatus.ACTIVE);
        companyRepository.save(company);
        return true;

    }


    public List<CompanyGetAllResponseDto> getAll(String token) {
        AuthResponseDto auth = Optional.ofNullable(authManager.findByToken(token).getBody())
                .orElseThrow(()->new CompanyServiceException(ErrorType.USER_NOT_FOUND));
        if (auth.getRole().equals(ERole.ADMIN)) {
            return companyRepository.findAll()
                    .stream()
                    .filter(company -> company.getStatus().equals(EStatus.ACTIVE))
                    .map(companyMapper::fromCompanyToCompanyGetAllResponseDto)
                    .collect(Collectors.toList());
        }
        throw new CompanyServiceException(ErrorType.UNAUTHORIZED);
    }


    //statusu pending olan şirketleri listelemeye yarar
    public List<CompanyManagerResponseDto> getAllPendingCompanies(String token) {
        AuthResponseDto auth = Optional.ofNullable(authManager.findByToken(token).getBody())
                .orElseThrow(()->new CompanyServiceException(ErrorType.USER_NOT_FOUND));
        if (auth.getRole().equals(ERole.ADMIN)) {
            return companyRepository.findAll().stream().
                    filter(company -> company.getStatus().equals(EStatus.PENDING))
                    .map(companyMapper::fromCompanyToCompanyManagerResponseDto)
                    .collect(Collectors.toList()); }
        throw new CompanyServiceException(ErrorType.UNAUTHORIZED);
    }

    public Boolean approveCompany(CompanyTokenRequestDto dto) {
        System.out.println("Company başlangıcı approve");
        log.info("Approving company with token: {}"+ dto.getToken());
        AuthResponseDto auth = Optional.ofNullable(authManager.findByToken(dto.getToken()).getBody())
                .orElseThrow(()->new CompanyServiceException(ErrorType.USER_NOT_FOUND));
        Company company = companyRepository.findById(dto.getId()).orElseThrow(()->new CompanyServiceException(ErrorType.COMPANY_NOT_FOUND));
        if (auth.getRole().equals(ERole.ADMIN)) {
            company.setStatus(EStatus.ACTIVE);
            company.setUpdateAt(System.currentTimeMillis());
            companyRepository.save(company);
            approveManagerProducer.sendMessage(ApproveManagerModel.builder()
                    .id(company.getId())
                    .managerId(company.getManagerId())
                    .updateAt(System.currentTimeMillis())
                    .status(company.getStatus())
                    .build());
            return true;
        }
        throw new CompanyServiceException(ErrorType.UNAUTHORIZED);

    }

    public Boolean rejectCompany(CompanyTokenRequestDto dto) {
        AuthResponseDto auth = Optional.ofNullable(authManager.findByToken(dto.getToken()).getBody())
                .orElseThrow(()->new CompanyServiceException(ErrorType.USER_NOT_FOUND));

        Company company = companyRepository.findById(dto.getId()).orElseThrow(()->new CompanyServiceException(ErrorType.COMPANY_NOT_FOUND));
        if (auth.getRole().equals(ERole.ADMIN)) {
            company.setStatus(EStatus.PASSIVE);
            company.setUpdateAt(System.currentTimeMillis());
            companyRepository.save(company);
            rejectManagerProducer.sendMessage(RejectManagerModel.builder()
                    .id(company.getId())
                    .managerId(company.getManagerId())
                    .updateAt(System.currentTimeMillis())
                    .status(company.getStatus())
                    .build());
            return true;
        }
        throw new CompanyServiceException(ErrorType.UNAUTHORIZED);
    }

}
