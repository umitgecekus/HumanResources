package com.umit.rabbitmq.model;

import com.umit.utility.enums.ERole;
import com.umit.utility.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateManagerModel implements Serializable {

    private Long authId;
    private String name;
    private String surname;
    private String avatar;
    private String email;
    private String phone;
    private String address;
    private String company;
    private String taxNumber;
    private ERole role;
    private Long createAt;
    private EStatus status;

}
