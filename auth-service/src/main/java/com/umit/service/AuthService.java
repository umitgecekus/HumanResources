package com.umit.service;

import com.umit.dto.request.*;
import com.umit.dto.response.AuthLoginResponseDto;
import com.umit.dto.response.AuthResponseDto;
import com.umit.entity.Auth;
import com.umit.exception.AuthServiceException;
import com.umit.exception.ErrorType;
import com.umit.mapper.AuthMapper;
import com.umit.rabbitmq.model.CreateEmployeeModel;
import com.umit.rabbitmq.model.CreateManagerModel;
import com.umit.rabbitmq.model.SendMailActivationModel;
import com.umit.rabbitmq.model.SendMailRejectModel;
import com.umit.rabbitmq.producer.CreateEmployeeProducer;
import com.umit.rabbitmq.producer.CreateManagerProducer;
import com.umit.rabbitmq.producer.SendMailActivationProducer;
import com.umit.rabbitmq.producer.SendMailRejectProducer;
import com.umit.repository.AuthRepository;
import com.umit.utility.CodeGenerator;
import com.umit.utility.JwtTokenManager;
import com.umit.utility.enums.ERole;
import com.umit.utility.enums.EStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final JwtTokenManager jwtTokenManager;
    private final CreateManagerProducer createManagerProducer;
    private final SendMailActivationProducer sendMailActivationProducer;
    private final SendMailRejectProducer sendMailRejectProducer;
    private final CreateEmployeeProducer createEmployeeProducer;

    public AuthLoginResponseDto login(AuthLoginRequestDto dto) {
        Optional<Auth> auth = authRepository.findOptionalByEmailAndPassword(dto.getEmail(), dto.getPassword());

        if (auth.isEmpty())
            throw new AuthServiceException(ErrorType.ERROR_INVALID_LOGIN_PARAMETER);

        if (auth.get().getStatus().equals(EStatus.ACTIVE)) {
            Optional<String> token = jwtTokenManager.createToken(auth.get().getId());
            if(token.isEmpty()){
                throw new AuthServiceException(ErrorType.INVALID_TOKEN);
            }else {
                return AuthLoginResponseDto.builder().token(token.get()).role(auth.get().getRole()).build();
            }
        }else {
            throw new AuthServiceException(ErrorType.USER_IS_NOT_ACTIVE);

        }
    }

    /**
     * Süper admin token ile kontrol edilir.
     * @param dto
     * @return
     */
    public Boolean registerManager(RegisterManagerRequestDto dto) {
        Optional<Auth> OptionalAuth = authRepository.findOptionalByEmail(dto.getEmail());
        if (OptionalAuth.isPresent())
            throw new AuthServiceException(ErrorType.USER_ALREADY_EXISTS);

        Auth auth = AuthMapper.INSTANCE.fromRegisterManagerRequestDtoToAuth(dto);
        auth.setRole(ERole.MANAGER);
        auth.setPassword(dto.getName().substring(0,1).toUpperCase()+ dto.getName().toLowerCase() + CodeGenerator.generateCode() + ".");
        auth.setCreateAt(System.currentTimeMillis());
        authRepository.save(auth);
        createManagerProducer.sendMessage(CreateManagerModel.builder()  //
                .authId(auth.getId())
                .address(dto.getAddress())
                .company(dto.getCompany())
                .email(dto.getEmail())
                .avatar(dto.getAvatar())
                .name(dto.getName())
                .phone(dto.getPhone())
                .surname(dto.getSurname())
                .taxNumber(dto.getTaxNumber())
                .createAt(System.currentTimeMillis())
                .status(EStatus.PENDING)
                .build());
        return true;
    }

    public Boolean registerEmployee(RegisterEmployeeRequestDto dto) {
        Auth auth = AuthMapper.INSTANCE.fromRegisterEmployeeRequestDtoToAuth(dto);
        String name = normalizeAndRemoveSpaces(dto.getName().toLowerCase());
        String surname = normalizeAndRemoveSpaces(dto.getSurname().toLowerCase());
        String password = name.substring(0,1).toUpperCase()+ name.substring(1) + surname+ CodeGenerator.generateCode() +".";
        String company = normalizeAndRemoveSpaces(dto.getCompanyName().toLowerCase());
        String employeeEmail = name + "." + surname + "@" + company + ".com";

        Optional<Auth> OptionalAuth = authRepository.findOptionalByEmail(employeeEmail);
        if (OptionalAuth.isPresent())
            throw new AuthServiceException(ErrorType.USER_ALREADY_EXISTS);

        auth.setPassword(password);
        auth.setEmail(employeeEmail);

        auth.setStatus(EStatus.ACTIVE);
        auth.setCreateAt(System.currentTimeMillis());
        auth.setUpdateAt(System.currentTimeMillis());
        auth.setRole(ERole.EMPLOYEE);
        authRepository.save(auth);

        createEmployeeProducer.sendMessage(CreateEmployeeModel.builder()
                .authId(auth.getId())
                .name(dto.getName())
                .surname(dto.getSurname())
                .identityNumber(dto.getIdentityNumber())
                .phoneNumber(dto.getPhoneNumber())
                .email(auth.getEmail())
                .address(dto.getAddress())
                .position(dto.getPosition())
                .department(dto.getDepartment())
                .occupation(dto.getOccupation())
                .companyName(dto.getCompanyName())
                .status(EStatus.ACTIVE)
                .managerId(dto.getManagerId())
                .jobStartDate(dto.getJobStartDate())
                .createAt(System.currentTimeMillis())
                .updateAt(System.currentTimeMillis())
                .build());

        return true;

    }

    public boolean isCompanyEmail(String email, String company) {
        //TODO: kontrol edilecek. email doğrulaması yapmayı amaçladım. Şirket maili ile girmeli. Ayrıca bir domain istenebilir?

        if (email.equalsIgnoreCase(company))
            return true;
        else {
            throw new AuthServiceException(ErrorType.ERROR_EMAIL_ISCOMPANY);
        }
    }

    public Boolean registerAdmin(RegisterAdminRequestDto dto) {
        Optional<Long> authId= jwtTokenManager.getIdFromToken(dto.getToken());
        if (authId.isEmpty()){
            throw new AuthServiceException(ErrorType.INVALID_TOKEN);
        }
        Optional<Auth> admin = authRepository.findOptionalById(authId.get());
        if (admin.isEmpty()){
            throw new AuthServiceException(ErrorType.USER_NOT_FOUND);
        }


        Auth auth = Auth.builder().email(dto.getEmail()).password(dto.getPassword()).build();
        auth.setRole(ERole.ADMIN);
        auth.setStatus(EStatus.ACTIVE);
        authRepository.save(auth);
        return true;
    }

    public Boolean approveAuth(Long authId) {
        Optional<Auth> optionalAuth = authRepository.findOptionalById(authId);
        if (optionalAuth.isEmpty())
            throw new AuthServiceException(ErrorType.USER_NOT_FOUND);
        Auth auth=optionalAuth.get();
        auth.setStatus(EStatus.ACTIVE);
        auth.setUpdateAt(System.currentTimeMillis());
        authRepository.save(auth);
        sendMailActivationProducer.sendMessage(SendMailActivationModel.builder()
                .id(auth.getId())
                .email(auth.getEmail())
                .password(auth.getPassword())
                .updateAt(System.currentTimeMillis())
                .build());
        return true;
    }

    public Boolean rejectAuth(Long authId) {
        Optional<Auth> auth = authRepository.findOptionalById(authId);
        if (auth.isEmpty())
            throw new AuthServiceException(ErrorType.USER_NOT_FOUND);
        auth.get().setStatus(EStatus.PASSIVE);
        auth.get().setUpdateAt(System.currentTimeMillis());
        authRepository.save(auth.get());
        sendMailRejectProducer.sendMessage(SendMailRejectModel.builder()
                .id(auth.get().getId())
                .email(auth.get().getEmail())
                .updateAt(System.currentTimeMillis())
                .build());
        return true;
    }


    public Boolean changePassword(ChangePasswordDto dto) {

        Optional<Auth> auth = authRepository.findOptionalByEmailAndPassword(dto.getEmail(), dto.getOldPassword());
        if (auth.isEmpty()){
            throw new AuthServiceException(ErrorType.USER_NOT_FOUND);
        }
        if (!auth.get().getPassword().equalsIgnoreCase(dto.getOldPassword())) {
            throw new AuthServiceException(ErrorType.PASSWORD_NOT_MATCH);
        }
        if (!dto.getNewPassword().equalsIgnoreCase(dto.getConfirmPassword())) {
            throw new AuthServiceException(ErrorType.PASSWORD_NOT_MATCH);
        }
        auth.get().setPassword(dto.getNewPassword());
        authRepository.save(auth.get());
        return true;
    }


    public String normalizeAndRemoveSpaces(String input) {
        String normalizedString = Normalizer.normalize(input,Normalizer.Form.NFD);
        // Add the following lines to preserve the following characters
        normalizedString = normalizedString.replace("ı","i");
        normalizedString = normalizedString.replace("ö","o");
        normalizedString = normalizedString.replace("ü","u");
        normalizedString = normalizedString.replace("ç","c");
        normalizedString = normalizedString.replace("ş","s");
        normalizedString = normalizedString.replace("ğ","g");

        normalizedString = normalizedString.replaceAll("[^\\p{ASCII}]","");
        normalizedString = normalizedString.replaceAll("\\s+","");

        return normalizedString;
    }

    /*public AuthResponseDto findByToken(String token) {
        System.out.println("service katmanında token var mı?" + token);
        ERole role = jwtTokenManager.getRoleFromToken(token);
        System.out.println("burayı geçti mi??? servicete");
        if (role != null) {
            System.out.println("kontrol yapıyor mu?");
            Optional<Long> authId = authRepository.findByRole(ERole.valueOf(String.valueOf(role))).stream().map(Auth::getId).findFirst();
            System.out.println("role..: " + role.name());
            if (authId.isPresent()) {
                Optional<Auth> auth = authRepository.findById(authId.get());
                if (auth.isPresent()) {
                    return AuthMapper.INSTANCE.fromAuthToAuthResponseDto(auth.get());
                }
            }
        }
        System.out.println("admin not found mı?");
        throw new AuthServiceException(ErrorType.USER_NOT_FOUND);
    } */

    public AuthResponseDto findByToken(String token) {
        System.out.println("token var mı?" + token);
        Optional<Long> authId = jwtTokenManager.getIdFromToken(token);
        if (authId.isPresent()){
            Auth auth = authRepository.findById(authId.get()).orElseThrow(()->new AuthServiceException(ErrorType.USER_NOT_FOUND));
            return AuthMapper.INSTANCE.fromAuthToAuthResponseDto(auth);
        }
        throw new AuthServiceException(ErrorType.USER_NOT_FOUND);
    }

}
