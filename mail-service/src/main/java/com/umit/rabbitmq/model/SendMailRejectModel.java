package com.umit.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendMailRejectModel implements Serializable {

    private Long id;
    private String email;
    private Long updateAt;

}
