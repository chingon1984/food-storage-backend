package com.chingon.FoodStorageApp.identity.entity;

import com.chingon.FoodStorageApp.shared.audit.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "T_HOUSEHOLD")
@Getter
@Setter
@ToString(of = {"id", "public_id", "name"})
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "household_seq",
        allocationSize = 1,
        sequenceName = "T_HOUSEHOLD_SEQ"
)
public class Household extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "household_seq")
    private Long id;
    @Column(nullable = false, unique = true, updatable = false)
    private UUID publicId;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private Boolean archived = false;

    @PrePersist
    public void prePersist() {
        if (publicId == null) {
            publicId = UUID.randomUUID();
        }
    }

}
