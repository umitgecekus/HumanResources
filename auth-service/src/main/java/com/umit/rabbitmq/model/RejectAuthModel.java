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
public class RejectAuthModel implements Serializable {

    private Long id;
    private Long authId;
    private Long updateAt;
    private EStatus status;

}
