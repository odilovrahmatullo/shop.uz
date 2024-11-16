package shop.uz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO {

    private Integer id;
    private String nameUz;
    private String nameEn;
    private String nameRu;


    private String iconId;
    private AttachDTO icon;
    private Integer orderName;
    private Integer parentId;
    private CategoryDTO parent;
}
