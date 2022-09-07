package pe.regioncusco.gob.simecr.s3;

import org.springframework.web.multipart.MultipartFile;
import pe.regioncusco.gob.simecr.s3.model.Asset;

public interface S3Service {
    boolean putObject(MultipartFile file, String key);
    Asset getObject(String key);
}
