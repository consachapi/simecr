package pe.regioncusco.gob.simecr.modules.remediacion.domain.models.dtos;

import pe.regioncusco.gob.simecr.modules.remediacion.domain.enums.MedidasRemediacionEstado;

public class MedidasRemediacionDto {
    private Long id;
    private String remediacion;
    private DeficienciaDto deficiencia;
    private MedidasRemediacionEstado estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemediacion() {
        return remediacion;
    }

    public void setRemediacion(String remediacion) {
        this.remediacion = remediacion;
    }

    public DeficienciaDto getDeficiencia() {
        return deficiencia;
    }

    public void setDeficiencia(DeficienciaDto deficiencia) {
        this.deficiencia = deficiencia;
    }

    public MedidasRemediacionEstado getEstado() {
        return estado;
    }

    public void setEstado(MedidasRemediacionEstado estado) {
        this.estado = estado;
    }
}
