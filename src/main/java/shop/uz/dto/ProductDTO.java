package shop.uz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import shop.uz.enums.ProductStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Boolean visible;
    private List<String> photos;
    private List<AttachDTO> photosDTO;
    private List<Integer>categories;
    private ProductStatus productStatus;
    private LocalDateTime createdDate;
}
