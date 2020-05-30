package com.securitydb.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Random;

@Entity
@Table
@Data
@NoArgsConstructor
@ToString
public class UserEntity {
    @Transient
    private Long randomNumber = new Random().nextLong();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nameOfUser;

    @Column
    private Long requestNumber;

    @Column
    private String roleOfUser;


    public UserEntity(String nameOfUser){
        this.nameOfUser = nameOfUser;
    }

    public UserEntity(String nameOfUser, String roleOfUser, Long requestNumber) {
        this.nameOfUser = nameOfUser;
        this.roleOfUser = roleOfUser;
        this.requestNumber = requestNumber;
    }

    public String getRoleOfUser() {
        return roleOfUser != null ? roleOfUser : "simpleUser";
    }

    public Long getRequestNumber() {
        return requestNumber != null ? requestNumber : randomNumber;
    }

}
