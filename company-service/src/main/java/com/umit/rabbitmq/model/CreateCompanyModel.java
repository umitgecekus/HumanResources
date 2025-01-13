package com.umit.rabbitmq.model;

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
public class CreateCompanyModel implements Serializable {

    private Long managerId;
    private String name;
    private String taxNumber;
    private EStatus status;

}
