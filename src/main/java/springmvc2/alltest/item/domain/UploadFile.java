package springmvc2.alltest.item.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * date           : 2024-11-06
 * created by     : 임경재 
 * description    :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadFile {

    private String uploadFileName;
    private String storeFileName;
}
