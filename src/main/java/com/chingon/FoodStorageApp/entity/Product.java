package com.chingon.FoodStorageApp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

enum ProductCategory {
    VEGETABLES_FRUITS,
    MEAT_FISH,
    READY_TO_SERVE,
    COFFEE_TEA,
    NUTRIENTS,
    MISCELLANEOUS
}

enum Source {
    OPEN_FOOD_FACTS,
    MANUAL
}

enum QuantityUnit {
    GRAM,
    KILOGRAM,
    LITER,
    MILLILITER,
    PIECE
}

@Entity
@Table(name = "T_PRODUCT")
@Getter
@Setter
@ToString(of = {"id", "name", "brand", "barcode"})
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "product_seq",
        allocationSize = 1,
        sequenceName = "T_PRODUCT_SEQ"
)
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    private Long id;
    @Column(nullable = false)
    private String barcode;
    @Column(nullable = false)
    private String name;
    private String brand;
    private BigDecimal quantityValue;
    @Enumerated(EnumType.STRING)
    private QuantityUnit quantityUnit;
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    private ProductCategory category;
    private LocalDateTime lastFetchedAt;  // Sind meine Produkt-Stammdaten noch aktuell oder soll ich Open Food Facts erneut abfragen?
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Source source = Source.OPEN_FOOD_FACTS;
}
