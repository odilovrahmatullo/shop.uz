package shop.uz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import shop.uz.enums.ProductStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Boolean visible;
    @Column(name = "product_status")
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
