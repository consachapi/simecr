package pe.regioncusco.gob.simecr.application.control.domain.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pe.regioncusco.gob.simecr.application.control.domain.enums.ImpactoEnum;
import pe.regioncusco.gob.simecr.application.control.domain.enums.NivelEnum;
import pe.regioncusco.gob.simecr.application.control.domain.enums.ProbabilidadEnum;
import pe.regioncusco.gob.simecr.application.control.domain.enums.RiesgoEstado;
import pe.regioncusco.gob.simecr.application.control.domain.models.Riesgo;
import pe.regioncusco.gob.simecr.application.control.domain.models.dtos.RiesgoDto;
import pe.regioncusco.gob.simecr.core.common.MyValue;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {ProductoMapper.class})
public interface RiesgoMapper {
    @Mapping(source = ".", target = "valor", qualifiedByName = "calculeValor")
    @Mapping(source = ".", target = "nivel", qualifiedByName = "calculeNivel")
    RiesgoDto toRiesgoDto(Riesgo riesgo);
    default RiesgoEstado map(Integer estado){
        return RiesgoEstado.valueOf(estado);
    }
    default ProbabilidadEnum toProbabilidad(Integer probabilidad){
        return ProbabilidadEnum.valueOf(probabilidad);
    }
    default ImpactoEnum toImpacto(Integer impacto){
        return ImpactoEnum.valueOf(impacto);
    }

    @Named("calculeValor")
    static Integer calculeValor(Riesgo riesgo){
        return riesgo.getProbabilidad() * riesgo.getImpacto();
    }

    @Named("calculeNivel")
    static NivelEnum calculeNivel(Riesgo riesgo){
        Integer valor = riesgo.getProbabilidad() * riesgo.getImpacto();
        if(valor >= 16 && valor <= 24){
            return NivelEnum.RB;
        }
        if(valor >= 32 && valor <= 40){
            return NivelEnum.RM;
        }
        if(valor >= 48 && valor <= 64){
            return NivelEnum.RA;
        }
        if(valor >= 80 && valor <= 100){
            return NivelEnum.RA;
        }
        return NivelEnum.RB;
    }


    @Mapping(source = "id", target = "value")
    @Mapping(source = "riesgo", target = "label")
    MyValue toMyValue(Riesgo riesgo);
}