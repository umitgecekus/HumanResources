package com.umit.service;

import com.umit.dto.request.UpdateAdminRequestDto;
import com.umit.entity.Admin;
import com.umit.exception.AdminServiceException;
import com.umit.exception.ErrorType;
import com.umit.mapper.AdminMapper;
import com.umit.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    public Boolean updateAdmin(UpdateAdminRequestDto dto){
        Optional<Admin> admin = adminRepository.findById(dto.getId());
        if (admin.isEmpty()){
            throw new AdminServiceException(ErrorType.ADMIN_NOT_FOUND);
        }
        admin.get().setUpdateAt(System.currentTimeMillis());
        adminRepository.save(AdminMapper.INSTANCE.fromUpdateAdminRequestDtoToAdmin(dto));
        return true;
    }



}
