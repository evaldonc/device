package dev.evaldo.device.repository;

import dev.evaldo.device.model.entity.DeviceEntity;
import dev.evaldo.device.model.enums.StateType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface DeviceRepository extends JpaRepository<DeviceEntity, Long>{

    Collection<DeviceEntity> findByBrand(String brand);

    Collection<DeviceEntity> findByState(StateType state);

}

