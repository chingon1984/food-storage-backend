package com.chingon.FoodStorageApp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_STORED_ITEM")
@Getter
@Setter
@ToString(of = {"id", "quantityValue", "expiryDate"})
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "stored_item_seq",
        allocationSize = 1,
        sequenceName = "T_STORED_ITEM_SEQ")
public class StoredItem extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stored_item_seq")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTAINER_ID", nullable = false)
    private Container container;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;
    @Column(nullable = false)
    private Boolean archived = false;
    private LocalDate expiryDate;
    private Integer quantityValue = 1;
}
