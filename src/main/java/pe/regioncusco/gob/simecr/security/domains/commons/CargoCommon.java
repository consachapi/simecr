package pe.regioncusco.gob.simecr.security.domain.commons;

import pe.regioncusco.gob.simecr.commons.MyValue;
import pe.regioncusco.gob.simecr.security.infraestructures.database.entities.CargoEntity;
import pe.regioncusco.gob.simecr.security.domains.models.Cargo;

public final class CargoCommon {
    public static CargoEntity toCargoEntity(Cargo cargo){
        CargoEntity cargoEntity = new CargoEntity();
        cargoEntity.setId(cargo.getId());
        cargoEntity.setDescripcion(cargo.getDescripcion());
        return cargoEntity;
    }

    public static final MyValue toMyValue(CargoEntity cargoEntity){
        return new MyValue(cargoEntity.getId(), cargoEntity.getDescripcion());
    }
}
