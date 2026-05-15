package com.chingon.FoodStorageApp.identity.entity;

import com.chingon.FoodStorageApp.shared.audit.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "T_USER")
@Getter
@Setter
@ToString(of = {"id", "email", "name"})
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "user_seq",
        allocationSize = 1,
        sequenceName = "T_USER_SEQ"
)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String passwordHash;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SecRole secRole = SecRole.USER;
    @Column(nullable = false)
    private Boolean verified = false;

}
