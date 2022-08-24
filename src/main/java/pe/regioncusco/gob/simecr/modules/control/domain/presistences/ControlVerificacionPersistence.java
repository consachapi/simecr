package pe.regioncusco.gob.simecr.modules.control.domain.presistences;

import pe.regioncusco.gob.simecr.modules.control.domain.models.ControlVerificacion;
import pe.regioncusco.gob.simecr.modules.control.domain.models.MedidasControl;
import pe.regioncusco.gob.simecr.modules.parametros.domain.models.Oficina;

import java.util.List;
import java.util.Optional;

public interface ControlVerificacionPersistence {
    ControlVerificacion save(ControlVerificacion controlVerificacion, String usuario, Integer estado);
    ControlVerificacion updateOficina(ControlVerificacion controlVerificacion);
    List<ControlVerificacion> findAll();
    List<ControlVerificacion> findAllByMedidaControl(MedidasControl medidasControl);
    Optional<ControlVerificacion> findById(Long id);
    List<ControlVerificacion> findAllByOficinas(List<Oficina> oficinas);
    ControlVerificacion changeEstado(ControlVerificacion paramControlVerificacion, Integer paramInteger, String paramString);
}
