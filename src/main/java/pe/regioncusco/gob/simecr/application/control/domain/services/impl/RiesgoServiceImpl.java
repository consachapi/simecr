package pe.regioncusco.gob.simecr.application.control.domain.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pe.regioncusco.gob.simecr.application.control.domain.enums.RiesgoEstado;
import pe.regioncusco.gob.simecr.application.control.domain.models.Producto;
import pe.regioncusco.gob.simecr.application.control.domain.models.Riesgo;
import pe.regioncusco.gob.simecr.application.control.domain.models.dtos.RiesgoDto;
import pe.regioncusco.gob.simecr.core.common.MyValue;
import pe.regioncusco.gob.simecr.security.config.AccessTokenImpl;
import pe.regioncusco.gob.simecr.core.exceptions.NotFoundException;
import pe.regioncusco.gob.simecr.application.control.domain.mappers.RiesgoMapper;
import pe.regioncusco.gob.simecr.application.control.domain.presistences.RiesgoPersistence;
import pe.regioncusco.gob.simecr.application.control.domain.services.ProductoService;
import pe.regioncusco.gob.simecr.application.control.domain.services.RiesgoService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RiesgoServiceImpl implements RiesgoService {
    private static final Logger LOG = LoggerFactory.getLogger(RiesgoServiceImpl.class);

    @Value("${periodo}") Integer periodo;

    @Autowired private AccessTokenImpl accessToken;
    @Autowired private RiesgoPersistence riesgoPersistence;
    @Autowired private ProductoService productoService;
    @Autowired private RiesgoMapper riesgoMapper;

    @Override
    public List<RiesgoDto> findAll() {
        List<Riesgo> riesgos = riesgoPersistence.findAll();
        return riesgos.stream().map(riesgo -> riesgoMapper.toRiesgoDto(riesgo)).collect(Collectors.toList());
    }

    @Override
    public List<MyValue> findAllByProducto(Long producto) {
        Producto product = productoService.findProductoById(producto);
        List<Riesgo> riesgos = riesgoPersistence.findAllByProducto(product);
        List<Riesgo> filterRiesgo = riesgos.stream().filter(riesgo -> riesgo.getProbabilidad() * riesgo.getImpacto() >= 32).collect(Collectors.toList());
        return filterRiesgo.stream().map(riesgo -> riesgoMapper.toMyValue(riesgo)).collect(Collectors.toList());
    }

    @Override
    public Riesgo save(Riesgo riesgo) {
        Producto producto = productoService.findProductoById(riesgo.getProducto().getId());
        riesgo.setProducto(producto);
        LOG.info("Creando nuevo riesgo del producto {}", riesgo.getProducto().getId());
        return riesgoPersistence.save(riesgo, accessToken.getUserId(), periodo, RiesgoEstado.ACTIVO.value());
    }

    @Override
    public Riesgo update(Long id, Riesgo riesgo) {
        Riesgo updRiesgo = findById(id);
        BeanUtils.copyProperties(riesgo, updRiesgo);
        updRiesgo.setId(id);
        return riesgoPersistence.save(updRiesgo, accessToken.getUserId(), periodo, riesgo.getEstado());
    }

    @Override
    public void delete(Long id) {
        Riesgo riesgo = findById(id);
        riesgoPersistence.delete(riesgo, accessToken.getUserId(), RiesgoEstado.ANULADO.value());
    }

    @Override
    public Riesgo findRiesgoById(Long id) {
        return findById(id);
    }

    @Override
    public RiesgoDto findByRiesgoDtoById(Long id) {
        Riesgo riesgo = findById(id);
        return riesgoMapper.toRiesgoDto(riesgo);
    }

    private Riesgo findById(Long id){
        Optional<Riesgo> optionalRiesgo = riesgoPersistence.findById(id);
        if(!optionalRiesgo.isPresent()){
            LOG.error("EL riesgo con {}, no existe", id);
            throw new NotFoundException("No existe el riesgo");
        }
        return optionalRiesgo.get();
    }

}
