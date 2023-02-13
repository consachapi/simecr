package pe.regioncusco.gob.simecr.modules.parametros.domain.models;

public class Componente {
    private Long id;
    private Eje eje;
    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Eje getEje() {
        return eje;
    }

    public void setEje(Eje eje) {
        this.eje = eje;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
