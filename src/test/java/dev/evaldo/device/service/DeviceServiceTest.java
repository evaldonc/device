package dev.evaldo.device.service;

import dev.evaldo.device.exception.BusinessException;
import dev.evaldo.device.model.DeviceMapper;
import dev.evaldo.device.model.dto.DeviceDto;
import dev.evaldo.device.model.entity.DeviceEntity;
import dev.evaldo.device.model.enums.StateType;
import dev.evaldo.device.model.request.DeviceRequest;
import dev.evaldo.device.repository.DeviceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeviceServiceTest {

    @Mock
    private DeviceRepository deviceRepository;

    @Mock
    private DeviceMapper deviceMapper;

    @InjectMocks
    private DeviceService deviceService;

    @Test
    void testCreateDevice() {
        DeviceRequest request = new DeviceRequest();
        DeviceEntity entity = new DeviceEntity();
        DeviceDto dto = new DeviceDto();

        when(deviceMapper.toEntity(request)).thenReturn(entity);
        when(deviceRepository.save(entity)).thenReturn(entity);
        when(deviceMapper.toDto(entity)).thenReturn(dto);

        DeviceDto result = deviceService.createDevice(request);

        assertEquals(dto, result);
        verify(deviceRepository).save(entity);
    }

    @Test
    void testGetDeviceNotFound() {
        when(deviceRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> deviceService.getDevice(1L));
    }

    @Test
    void testUpdateDeviceInUseThrowsException() {
        DeviceEntity entity = new DeviceEntity();
        entity.setState(StateType.IN_USE);

        when(deviceRepository.findById(1L)).thenReturn(Optional.of(entity));

        DeviceRequest request = new DeviceRequest();
        assertThrows(BusinessException.class, () -> deviceService.updateDevice(1L, request));
    }

    @Test
    void testDeleteDevice() {
        DeviceEntity entity = DeviceEntity.builder().id(1L).state(StateType.AVAILABLE).build();
        when(deviceRepository.findById(1L)).thenReturn(Optional.of(entity));

        deviceService.deleteDevice(1L);

        verify(deviceRepository).deleteById(entity.getId());
    }
}