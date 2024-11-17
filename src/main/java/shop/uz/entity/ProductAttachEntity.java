package shop.uz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "product_attach")
public class ProductAttachEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "attach_id")
    private String attachId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attach_id",insertable = false, updatable = false)
    private AttachEntity attach;

    @Column(name = "product_id")
    private Integer productId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id",insertable = false, updatable = false)
    private ProductEntity product;

    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
