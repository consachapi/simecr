package pe.regioncusco.gob.simecr.application.control.domain.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.regioncusco.gob.simecr.application.control.domain.enums.ProductoEstado;
import pe.regioncusco.gob.simecr.application.control.domain.models.Producto;
import pe.regioncusco.gob.simecr.application.control.domain.models.dtos.ProductoBodyDto;
import pe.regioncusco.gob.simecr.application.control.domain.models.dtos.ProductoDto;
import pe.regioncusco.gob.simecr.application.control.domain.presistences.ProductoPersistence;
import pe.regioncusco.gob.simecr.core.common.MyValue;
import pe.regioncusco.gob.simecr.security.config.AccessTokenImpl;
import pe.regioncusco.gob.simecr.core.exceptions.NotFoundException;
import pe.regioncusco.gob.simecr.application.control.domain.mappers.ProductoMapper;
import pe.regioncusco.gob.simecr.application.control.domain.services.ProductoService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {
    private static final Logger LOG = LoggerFactory.getLogger(ProductoServiceImpl.class);

    @Autowired private AccessTokenImpl accessToken;
    @Autowired private ProductoPersistence productoPersistence;
    @Autowired private ProductoMapper productoMapper;

    @Override
    public List<ProductoDto> findAll() {
        return productoPersistence.findAll().stream().map(producto -> productoMapper.toProductoDto(producto)).collect(Collectors.toList());
    }

    @Override
    public List<MyValue> findAllActive() {
        List<Producto> productos = productoPersistence.findAllByActive(ProductoEstado.ACTIVO.value());
        LOG.info("Productos activos un total de {}", productos.size());
        return productos.stream().map(producto -> productoMapper.toMyValue(producto)).collect(Collectors.toList());
    }

    @Override
    public ProductoDto save(ProductoBodyDto producto) {
        Producto product = productoMapper.toProducto(producto);
        Producto nuevoProducto = productoPersistence.save(product, accessToken.getUserId(), ProductoEstado.ACTIVO.value());
        return productoMapper.toProductoDto(nuevoProducto);
    }

    @Override
    public ProductoDto update(Long id, ProductoBodyDto producto) {
        Producto searchProducto = getProductoById(id);
        BeanUtils.copyProperties(producto, searchProducto);
        searchProducto.setId(id);
        return productoMapper.toProductoDto(productoPersistence.save(searchProducto, accessToken.getUserId(), producto.getEstado()));
    }

    @Override
    public ProductoDto findById(Long id) {
        return productoMapper.toProductoDto(getProductoById(id));
    }

    @Override
    public Producto findProductoById(Long id) {
        return getProductoById(id);
    }

    @Override
    public void delete(Long id) {
        Producto searchProducto = getProductoById(id);
        productoPersistence.save(searchProducto, accessToken.getUserId(), ProductoEstado.ANULADO.value());
    }

    private Producto getProductoById(Long id){
        Optional<Producto> producto = productoPersistence.findById(id);
        if(!producto.isPresent()){
            LOG.error("El producto con id {}, no existe", id);
            throw new NotFoundException("El producto no existe.");
        }
        return producto.get();
    }

}
