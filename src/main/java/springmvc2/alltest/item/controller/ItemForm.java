package springmvc2.alltest.item.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * date           : 2024-11-06
 * created by     : 임경재 ( bmw4117@entropykorea.com )
 * description    :
 */
@Data
public class ItemForm {

    @NotEmpty
    private String itemName;
    @Min(value = 0)
    @NotNull
    private Integer price;
    @NotNull
    @Range(min = 0, max = 100)
    private Integer quantity;

    private MultipartFile attachFile;
    private List<MultipartFile> imageFiles;
}
