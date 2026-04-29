package com.chingon.FoodStorageApp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "T_CONTAINER")
@Getter
@Setter
@ToString(of = {"id", "name", "description"})
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "container_seq",
        allocationSize = 1,
        sequenceName = "T_CONTAINER_SEQ"
)
public class Container extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "container_seq")
    private Long id;
    @Column(nullable = false)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORAGE_ID", nullable = false)
    private Storage storage;
    private String description;
    @Column(nullable = false)
    private Boolean archived = false;
}
