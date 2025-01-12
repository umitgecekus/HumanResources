package com.umit.entity;

import com.umit.utility.enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_auth")
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @Column(unique = true)
    private String email;
    @Size(min = 8,max = 64, message = "Sifre en az 8 karakterden oluşmalıdır.")
    private String password;

    @Enumerated(EnumType.ORDINAL)
    private ERole role;

    private Long createAt;
    private Long updateAt;

    @Builder.Default
    private EStatus status=EStatus.PENDING;

}
