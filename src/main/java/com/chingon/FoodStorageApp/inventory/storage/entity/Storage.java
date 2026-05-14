package com.chingon.FoodStorageApp.inventory.storage.entity;

import com.chingon.FoodStorageApp.identity.household.Household;
import com.chingon.FoodStorageApp.shared.audit.BaseEntity;
import com.chingon.FoodStorageApp.identity.user.User;
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
public class Storage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "storage_seq")
    private Long id;
    @Column(nullable = false)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOUSEHOLD_ID", nullable = false)
    private Household household;
    private String description;
    @Column(nullable = false)
    private Boolean archived = false;
}
