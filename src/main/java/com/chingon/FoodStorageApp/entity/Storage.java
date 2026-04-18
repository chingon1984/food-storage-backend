package com.chingon.FoodStorageApp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "T_STORAGE")
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
@SequenceGenerator(
        name = "storage_seq",
        allocationSize = 1,
        sequenceName = "T_STORAGE_SEQ"
)
public class Storage extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "storage_seq")
    private Long id;
    private String name;
    private Long userId;
    private String description;
    private Boolean archived;
}
