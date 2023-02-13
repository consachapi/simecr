package pe.regioncusco.gob.simecr.application.control.domain.presistences;

import pe.regioncusco.gob.simecr.application.control.domain.models.MedidasControl;

import java.util.List;
import java.util.Optional;

public interface MedidasControlPersistence {
    MedidasControl save(MedidasControl medidasControl, String usuario, Integer estado);
    List<MedidasControl> findAll();
    void delete(MedidasControl medidaControl, String usuario, Integer estado);
    Optional<MedidasControl> findById(Long id);
}
