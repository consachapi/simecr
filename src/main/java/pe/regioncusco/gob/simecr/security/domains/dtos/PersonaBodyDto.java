package pe.regioncusco.gob.simecr.security.domains.dtos;

import pe.regioncusco.gob.simecr.security.domains.models.Cargo;
import pe.regioncusco.gob.simecr.security.domains.models.Perfil;

public class PersonaBodyDto {
    private String ndocumento;
    private Cargo cargo;
    private Perfil perfil;
    private boolean estado;

    public String getNdocumento() {
        return ndocumento;
    }

    public void setNdocumento(String ndocumento) {
        this.ndocumento = ndocumento;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
