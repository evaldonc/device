package dev.evaldo.device.service;

import dev.evaldo.device.exception.BusinessException;
import dev.evaldo.device.model.DeviceMapper;
import dev.evaldo.device.model.dto.DeviceDto;
import dev.evaldo.device.model.entity.DeviceEntity;
import dev.evaldo.device.model.enums.StateType;
import dev.evaldo.device.model.request.DeviceRequest;
import dev.evaldo.device.repository.DeviceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Log4j2
@RequiredArgsConstructor
public class DeviceService {

    private static final String DEVICE_ENTITY_NOT_FOUND_WITH_ID = "DeviceEntity not found with id: ";

    private final DeviceRepository deviceRepository;

    private final DeviceMapper deviceMapper;

    public DeviceDto createDevice(DeviceRequest req) {
        DeviceEntity deviceEntity = deviceMapper.toEntity(req);
        return deviceMapper.toDto(deviceRepository.save(deviceEntity));
    }

    public DeviceDto getDevice(Long id) {
        DeviceEntity deviceEntity = deviceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(DEVICE_ENTITY_NOT_FOUND_WITH_ID + id));
        return deviceMapper.toDto(deviceEntity);
    }

    public List<DeviceDto> getAllDevices() {
        return deviceRepository.findAll().stream()
                .map(deviceMapper::toDto)
                .toList();
    }

    public List<DeviceDto> getDevicesByBrand(String brand) {
        return deviceRepository.findByBrand(brand).stream()
                .map(deviceMapper::toDto)
                .toList();
    }

    public List<DeviceDto> getDevicesByState(String state) {
        return deviceRepository.findByState(StateType.valueOf(state)).stream()
                .map(deviceMapper::toDto)
                .toList();
    }

    @Transactional
    public DeviceDto updateDevice(Long id, DeviceRequest req) {
        DeviceEntity ett = deviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(DEVICE_ENTITY_NOT_FOUND_WITH_ID + id));
        if (ett.getState().equals(StateType.IN_USE)) {
            throw new BusinessException("Cannot update device in use");
        }
        if (Objects.nonNull(req.getName())) {
            ett.setName(req.getName());
        }
        if (Objects.nonNull(req.getBrand())) {
            ett.setBrand(req.getBrand());
        }
        if (Objects.nonNull(req.getState())) {
            ett.setState(req.getState());
        }
        return deviceMapper.toDto(deviceRepository.save(ett));
    }

    public void deleteDevice(Long id) {
        DeviceEntity ett = deviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(DEVICE_ENTITY_NOT_FOUND_WITH_ID + id));
        if (StateType.IN_USE.equals(ett.getState())) {
            throw new BusinessException("Cannot delete device in use");
        }
        deviceRepository.deleteById(id);
    }

}
