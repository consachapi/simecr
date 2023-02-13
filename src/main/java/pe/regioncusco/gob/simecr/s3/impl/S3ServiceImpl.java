package pe.regioncusco.gob.simecr.s3.impl;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pe.regioncusco.gob.simecr.core.common.ParamsManager;
import pe.regioncusco.gob.simecr.core.exceptions.BadRequestException;
import pe.regioncusco.gob.simecr.s3.S3Service;
import pe.regioncusco.gob.simecr.s3.model.Asset;

import java.io.IOException;

@Service
public class S3ServiceImpl implements S3Service {
    private static final Logger LOG = LoggerFactory.getLogger(S3ServiceImpl.class);
    private static final String BUCKET = ParamsManager.BUCKET;

    @Autowired private AmazonS3Client amazonS3Client;

    @Override
    public boolean putObject(MultipartFile multipartFile, String key){
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET, key, multipartFile.getInputStream(), objectMetadata);
            amazonS3Client.putObject(putObjectRequest);
            return true;
        }catch (IOException ex){
            LOG.error("Error al guardar el archivo en amazon s3 {}, exception {}", multipartFile.getName(), ex.getLocalizedMessage());
            throw new BadRequestException("Ocurrio un error al guardar el archivo " + multipartFile.getName());
        }
    }

    @Override
    public Asset getObject(String key){
        S3Object s3Object = amazonS3Client.getObject(BUCKET, key);
        ObjectMetadata objectMetadata = s3Object.getObjectMetadata();
        try {
            S3ObjectInputStream s3ObjectInputStream = s3Object.getObjectContent();
            byte[] bytes = IOUtils.toByteArray(s3ObjectInputStream);
            return new Asset(bytes, objectMetadata.getContentType());
        } catch (IOException e) {
            LOG.error("Error get objeto amazon s3 {}", e.getLocalizedMessage());
            throw new BadRequestException("Error al recuperar el archivo");
        }
    }

    public void deleteObject(String key){
        amazonS3Client.deleteObject(BUCKET, key);
    }

    public String getObjectUrl(String key){
        return String.format("https://%s.s3.amazonaws.com/%s", BUCKET, key);
    }

}
