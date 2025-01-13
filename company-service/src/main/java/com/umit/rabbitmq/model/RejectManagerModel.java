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
public class RejectManagerModel implements Serializable {

    private String id;
    private Long managerId;
    private Long updateAt;
    private EStatus status;

}
