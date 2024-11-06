package springmvc2.alltest.item.domain;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * date           : 2024-11-06
 * created by     : 임경재 
 * description    :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    private UploadFile attachFile;
    private List<UploadFile> imageFiles;
}
