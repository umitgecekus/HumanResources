package com.umit.dto.request;

import com.umit.utility.enums.EEquipmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentRequestDto {

    private String token;
    private String name;
    private EEquipmentType equipmentType;
    private String document;
    private Long receivingDate;
    private Long returningDate;

}
