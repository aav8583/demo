package com.securitydb.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
@ToString
public class UserRequests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long numberOfRequest;

    @Column
    private String comment;

    public UserRequests(String comment,Long numberOfRequest) {
        this.comment = comment;
        this.numberOfRequest = numberOfRequest;
    }
}
