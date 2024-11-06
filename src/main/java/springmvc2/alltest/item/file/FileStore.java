package springmvc2.alltest.item.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import springmvc2.alltest.item.domain.UploadFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * date           : 2024-11-06
 * created by     : 임경재 ( bmw4117@entropykorea.com )
 * description    :
 */
@Component
public class FileStore {

    private final String fileDir;

    public FileStore(@Value("${file.dir}") String fileDir) {
        this.fileDir = fileDir;
    }

    public String getFullPath(String fileName){
        return fileDir + fileName;
    }

    public List<UploadFile> storeFiles(List<MultipartFile> files) throws IOException {
        List<UploadFile> list = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                UploadFile uploadFile = storeFile(file);
                list.add(uploadFile);
            }
        }

        return list;
    }

    public UploadFile storeFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()){
            return null;
        }

        String originalFilename = file.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);
        file.transferTo(new File(fileDir + storeFileName));

        return new UploadFile(originalFilename, storeFileName);
    }

    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String string = UUID.randomUUID().toString();
        return string + "." + ext;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos+1);
    }
}
