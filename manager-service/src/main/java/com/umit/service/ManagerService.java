package com.umit.service;

import com.umit.dto.request.AdminUpdateManagerRequestDto;
import com.umit.dto.request.SaveManagerRequestDto;
import com.umit.dto.request.UpdateManagerRequestDto;
import com.umit.dto.response.ManagerResponseDto;
import com.umit.dto.response.SaveManagerResponseDto;
import com.umit.entity.Manager;
import com.umit.exception.ErrorType;
import com.umit.exception.ManagerServiceException;
import com.umit.mapper.ManagerMapper;
import com.umit.rabbitmq.model.ApproveAuthModel;
import com.umit.rabbitmq.model.CreateCompanyModel;
import com.umit.rabbitmq.model.RejectAuthModel;
import com.umit.rabbitmq.producer.ApproveAuthProducer;
import com.umit.rabbitmq.producer.CreateCompanyProducer;
import com.umit.rabbitmq.producer.RejectAuthProducer;
import com.umit.repository.ManagerRepository;
import com.umit.utility.JwtTokenManager;
import com.umit.utility.enums.EStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;
    private final JwtTokenManager jwtTokenManager;
    private final CreateCompanyProducer createCompanyProducer;
    private final ApproveAuthProducer approveAuthProducer;
    private final RejectAuthProducer rejectAuthProducer;

    public SaveManagerResponseDto createManager(SaveManagerRequestDto dto) {
        Manager manager= ManagerMapper.INSTANCE.fromSaveManagerRequestDtoToManager(dto);
        manager.setUpdateAt(System.currentTimeMillis());
        managerRepository.save(manager);
        createCompanyProducer.sendMessage(CreateCompanyModel.builder()
                .managerId(manager.getId())
                .name(dto.getCompany())
                .taxNumber(dto.getTaxNumber())
                .status(dto.getStatus())
                .build());
        return ManagerMapper.INSTANCE.fromManagerToSaveManagerResponseDto(manager);
    }

    public Boolean approveManager(Long managerId) {
        Optional<Manager> optionalManager= managerRepository.findById(managerId);
        if (optionalManager.isEmpty())
            throw new ManagerServiceException(ErrorType.MANAGER_NOT_FOUND);
        Manager manager=optionalManager.get();
        manager.setStatus(EStatus.ACTIVE);
        manager.setUpdateAt(System.currentTimeMillis());
        managerRepository.save(optionalManager.get());
        approveAuthProducer.sendMessage(ApproveAuthModel.builder()
                .id(manager.getId())
                .authId(manager.getAuthId())
                .updateAt(System.currentTimeMillis())
                .status(manager.getStatus())
                .build());
        return true;
    }

    public Boolean rejectManager(Long managerId) {
        Optional<Manager> optionalManager= managerRepository.findById(managerId);
        if (optionalManager.isEmpty())
            throw new ManagerServiceException(ErrorType.MANAGER_NOT_FOUND);
        Manager manager=optionalManager.get();
        manager.setStatus(EStatus.PASSIVE);
        manager.setUpdateAt(System.currentTimeMillis());
        managerRepository.save(optionalManager.get());
        rejectAuthProducer.sendMessage(RejectAuthModel.builder()
                .id(manager.getId())
                .authId(manager.getAuthId())
                .updateAt(System.currentTimeMillis())
                .status(manager.getStatus())
                .build());
        return true;
    }

    public ManagerResponseDto findManagerByToken(String token) {
        System.out.println("findToken geldi mi? "+token);
        Optional<Long> authId = jwtTokenManager.getIdFromToken(token);
        System.out.println("jwtden sonra token geldi mi? "+authId);
        if(authId.isPresent()){
            Manager manager = managerRepository.findByAuthId(authId.get()).get();
            return  ManagerMapper.INSTANCE.fromManagerToManagerResponseDto(manager);
        }
        throw new ManagerServiceException(ErrorType.MANAGER_NOT_FOUND);
    }

    public Boolean updateManager(UpdateManagerRequestDto dto){
        //System.out.println("servis başlangıcı - token geldi mi?..: "+dto.getToken());
        ManagerResponseDto managerResponseDto = Optional.ofNullable(findManagerByToken(dto.getToken()))
                .orElseThrow(() -> new ManagerServiceException(ErrorType.MANAGER_NOT_FOUND));
        //System.out.println("servis- token geldi mi 2: "+dto.getToken());
        if(!dto.getId().equals(managerResponseDto.getId())){
            throw new ManagerServiceException(ErrorType.MANAGER_NOT_FOUND);
        }

        Optional<Manager> optionalManager = managerRepository.findById(dto.getId());
        if (optionalManager.isPresent()) {
            Manager manager = optionalManager.get();
            manager.setName(dto.getName());
            manager.setSurname(dto.getSurname());
            manager.setBirthDate(dto.getBirthDate());
            manager.setPhoneNumber(dto.getPhoneNumber());
            manager.setAddress(dto.getAddress());
            manager.setAvatar(dto.getAvatar());
            manager.setTaxNumber(dto.getTaxNumber());
            manager.setUpdateAt(System.currentTimeMillis());
            manager.setGender(dto.getGender());
            manager.setOccupation(dto.getOccupation());
            managerRepository.save(manager);
            return true;
        }else{
            throw new ManagerServiceException(ErrorType.MANAGER_NOT_FOUND);
        }
    }

    public Boolean adminUpdateManager(AdminUpdateManagerRequestDto dto) {
        Optional<Manager> manager = managerRepository.findById(dto.getId());
        if (manager.isEmpty()) {
            throw new ManagerServiceException(ErrorType.MANAGER_NOT_FOUND);
        }
        manager.get().setUpdateAt(System.currentTimeMillis());
        managerRepository.save(ManagerMapper.INSTANCE.fromAdminUpdateManagerRequestDtoToManager(dto));
        return true;
    }

    public ManagerResponseDto findByToken(String token) {
        System.out.println("token var mı?" + token);
        Optional<Long> authId = jwtTokenManager.getIdFromToken(token);
        if (authId.isPresent()){
            Manager manager = managerRepository.findByAuthId(authId.get()).get();
            return ManagerMapper.INSTANCE.fromManagerToManagerResponseDto(manager);
        }
        System.out.println("manager not found mı?");
        throw new ManagerServiceException(ErrorType.USER_NOT_FOUND);
    }


}
