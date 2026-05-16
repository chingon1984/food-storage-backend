package com.chingon.FoodStorageApp.inventory.entity;

import com.chingon.FoodStorageApp.identity.entity.Household;
import com.chingon.FoodStorageApp.shared.audit.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "T_STORAGE")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(
        name = "storage_seq",
        allocationSize = 1,
        sequenceName = "T_STORAGE_SEQ"
)
public class Storage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "storage_seq")
    private Long id;
    @Column(nullable = false, unique = true, updatable = false)
    private UUID publicId;
    @Column(nullable = false)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOUSEHOLD_ID", nullable = false)
    private Household household;
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
