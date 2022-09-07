package pe.regioncusco.gob.simecr.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pe.regioncusco.gob.simecr.exceptions.BadRequestException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public final class Utils {
    private static final Logger LOG = LoggerFactory.getLogger(Utils.class);

    public static String decodeBase64(String encode){
        return new String(Base64.getDecoder().decode(encode));
    }

    public static String padLeft(Integer value){
        return String.format("%06d",value);
    }

    public static int age(Date fnacimiento){
        Date fechaNaci =  new Date(fnacimiento.getTime());
        LocalDate today = LocalDate.now();
        LocalDate fechaNac = fechaNaci.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(fechaNac, today).getYears();
    }

    public static String generateFileName(){
        return UUID.randomUUID().toString();
    }

    public static String encodeFile(String pathFile){
        byte[] inputFile = new byte[0];
        try {
            inputFile = Files.readAllBytes(Paths.get(pathFile, new String[0]));
            byte[] encodeBytes = Base64.getEncoder().encode(inputFile);
            String encodedString = new String(encodeBytes);
            return encodedString;
        } catch (IOException e) {
            LOG.error("Error realizar encode base 64 del path {}, {}", pathFile, e.getLocalizedMessage());
            throw new BadRequestException("Ocurrio un error al extraer el archivo");
        }
    }

    public static String encodeBytes(byte[] encodeBytes){
        byte[] encode = Base64.getEncoder().encode(encodeBytes);
        return new String(encode);
    }
}
