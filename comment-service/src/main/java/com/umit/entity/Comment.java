package com.umit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    private Long managerId;
    private String managerName;
    private String managerSurname;
    private String managerEmail;
    private String managerAvatar;
    private Long point;
    private String managerOccupation;
    private Long createAt;
    private Long updateAt;

}
