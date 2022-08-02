package pe.regioncusco.gob.simecr.modules.reportes.domain.models;

public class MedidasDashboard {
    private String medida;
    private Integer ejecutado;
    private Integer pendiente;
    private Integer total;

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public Integer getEjecutado() {
        return ejecutado;
    }

    public void setEjecutado(Integer ejecutado) {
        this.ejecutado = ejecutado;
    }

    public Integer getPendiente() {
        return pendiente;
    }

    public void setPendiente(Integer pendiente) {
        this.pendiente = pendiente;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
