package pe.regioncusco.gob.simecr.modules.control.infraestructure.data.pesistences;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.modules.control.domain.enums.RiesgoEstado;
import pe.regioncusco.gob.simecr.modules.control.domain.models.Producto;
import pe.regioncusco.gob.simecr.modules.control.domain.models.Riesgo;
import pe.regioncusco.gob.simecr.modules.control.domain.presistences.RiesgoPersistence;
import pe.regioncusco.gob.simecr.modules.control.infraestructure.data.commons.ProductoAdapterCommon;
import pe.regioncusco.gob.simecr.modules.control.infraestructure.data.entities.ProductoEntity;
import pe.regioncusco.gob.simecr.modules.control.infraestructure.data.entities.RiesgoEntity;
import pe.regioncusco.gob.simecr.modules.control.infraestructure.data.repositorys.RiesgoEntityRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class RiesgoPersistenceData implements RiesgoPersistence {
    private static final Logger LOG = LoggerFactory.getLogger(RiesgoPersistenceData.class);

    @Autowired private RiesgoEntityRepository riesgoEntityRepository;

    @Override
    public List<Riesgo> findAll() {
        List<RiesgoEntity> riesgoEntities = riesgoEntityRepository.findAll();
        return riesgoEntities.stream().map(riesgoEntity -> riesgoEntity.toRiesgo()).collect(Collectors.toList());
    }

    @Override
    public List<Riesgo> findAllByProducto(Producto producto) {
        ProductoEntity productoEntity = ProductoAdapterCommon.toProductoEntity(producto);
        List<RiesgoEntity> riesgoEntities = riesgoEntityRepository.findAllByProducto(productoEntity);
        LOG.info("Riesgos del producto {}, un total de {} riesgos.", producto.getId(), riesgoEntities.size());
        return riesgoEntities.stream().map(riesgoEntity -> riesgoEntity.toRiesgo()).collect(Collectors.toList());
    }

    @Override
    public Riesgo save(Riesgo riesgo, String usuario, Integer periodo, Integer estado) {
        ProductoEntity productoEntity = new ProductoEntity();
        BeanUtils.copyProperties(riesgo.getProducto(), productoEntity);

        RiesgoEntity riesgoEntity = new RiesgoEntity();
        BeanUtils.copyProperties(riesgo, riesgoEntity);
        riesgoEntity.setProducto(productoEntity);
        riesgoEntity.setPeriodo(periodo);
        riesgoEntity.setFechaRegsitro(new Date());
        riesgoEntity.setUsuario(usuario);
        riesgoEntity.setEstado(estado);

        RiesgoEntity nuevoEntity = riesgoEntityRepository.save(riesgoEntity);
        return nuevoEntity.toRiesgo();
    }

    @Override
    public void delete(Riesgo riesgo, String usuario, Integer estado) {
        Optional<RiesgoEntity> optionalRiesgoEntity = riesgoEntityRepository.findById(riesgo.getId());
        RiesgoEntity riesgoEntity = optionalRiesgoEntity.get();
        riesgoEntity.setUsuario(usuario);
        riesgoEntity.setEstado(estado);
        riesgoEntityRepository.save(riesgoEntity);
    }

    @Override
    public Optional<Riesgo> findById(Long id) {
        Optional<RiesgoEntity> optionalRiesgoEntity = riesgoEntityRepository.findById(id);
        return optionalRiesgoEntity.map(riesgoEntity -> riesgoEntity.toRiesgo());
    }
}
