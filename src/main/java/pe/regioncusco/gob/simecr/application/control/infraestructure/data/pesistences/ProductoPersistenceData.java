package pe.regioncusco.gob.simecr.application.control.infraestructure.data.pesistences;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.application.control.domain.models.Producto;
import pe.regioncusco.gob.simecr.application.control.infraestructure.data.repositorys.ProductoEntityRepository;
import pe.regioncusco.gob.simecr.application.control.infraestructure.data.entities.ProductoEntity;
import pe.regioncusco.gob.simecr.application.control.domain.commons.ProductoDomainCommon;
import pe.regioncusco.gob.simecr.application.control.domain.presistences.ProductoPersistence;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductoPersistenceData implements ProductoPersistence  {
    private static final Logger LOG = LoggerFactory.getLogger(ProductoPersistenceData.class);

    @Autowired
    private ProductoEntityRepository productoEntityRepository;

    @Override
    public List<Producto> findAll() {
        List<ProductoEntity> productoEntities = productoEntityRepository.findAll();
        LOG.info("Productos obtenidos {}", productoEntities.size());
        return ProductoDomainCommon.toListProducto(productoEntities);
    }

    @Override
    public List<Producto> findAllByActive(Integer estado) {
        List<ProductoEntity> productoEntities = productoEntityRepository.findAllByEstado(estado);
        LOG.info("Productos obtenidos {}", productoEntities.size());
        return ProductoDomainCommon.toListProducto(productoEntities);
    }

    @Override
    public Producto save(Producto producto, String usuario, Integer estado) {
        ProductoEntity productoEntity = new ProductoEntity();
        BeanUtils.copyProperties(producto, productoEntity);
        productoEntity.setPeriodo(2022);
        productoEntity.setFechaRegistro(new Date());
        productoEntity.setUsuario(usuario);
        productoEntity.setEstado(estado);
        ProductoEntity nuevo = productoEntityRepository.save(productoEntity);
        return nuevo.toProducto();
    }

    @Override
    public Optional findById(Long id) {
        Optional<ProductoEntity> optionalProductoEntity = productoEntityRepository.findById(id);
        return optionalProductoEntity.map(productoEntity -> productoEntity.toProducto());
    }
}
