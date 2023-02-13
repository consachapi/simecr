package pe.regioncusco.gob.simecr.security.domain.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.regioncusco.gob.simecr.commons.MyValue;
import pe.regioncusco.gob.simecr.exceptions.NotFoundException;
import pe.regioncusco.gob.simecr.security.infraestructures.database.entities.CargoEntity;
import pe.regioncusco.gob.simecr.security.infraestructures.database.respositorys.CargoEntityRepository;
import pe.regioncusco.gob.simecr.security.domains.mappers.CargoMapper;
import pe.regioncusco.gob.simecr.security.domains.models.Cargo;
import pe.regioncusco.gob.simecr.security.domain.services.CargoService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CargoServiceImpl implements CargoService {
    private static final Logger LOG = LoggerFactory.getLogger(CargoServiceImpl.class);

    @Autowired private CargoEntityRepository cargoEntityRepository;
    @Autowired private CargoMapper cargoMapper;

    @Override
    public List<MyValue> findAllSelection() {
        List<CargoEntity> cargoEntities = cargoEntityRepository.findAll();
        LOG.info("Cargos encontrados {}", cargoEntities.size());
        return cargoEntities.stream().map(cargoEntity -> cargoMapper.toMyValue(cargoEntity.toCargo())).collect(Collectors.toList());
    }

    @Override
    public Cargo findCargoById(Long id) {
        return findById(id);
    }

    private Cargo findById(Long id){
        Optional<Cargo> optionalCargo = cargoEntityRepository.findById(id).map(cargoEnt -> cargoEnt.toCargo());
        return optionalCargo.orElseThrow(() -> new NotFoundException("No existe cargo"));
    }

}
