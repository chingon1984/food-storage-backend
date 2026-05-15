package com.chingon.FoodStorageApp.identity.entity;

import com.chingon.FoodStorageApp.shared.audit.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "T_HOUSEHOLD_MEMBER")
@Getter
@Setter
@ToString(of = {"id", "role"})
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "household_member_seq",
        allocationSize = 1,
        sequenceName = "T_HOUSEHOLD_MEMBER_SEQ"
)
public class HouseholdMember extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "household_member_seq")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "HOUSEHOLD_ID",
            nullable = false,
            foreignKey = @ForeignKey(name = "HOUSEHOLD_MEMBER_HOUSEHOLD_FK")
    )
    private Household household;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "USER_ID",
            nullable = false,
            foreignKey = @ForeignKey(name = "HOUSEHOLD_MEMBER_USER_FK")
    )
    private User user;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Household_Role role;
    @Column(nullable = false)
    private Boolean archived = false;
}
