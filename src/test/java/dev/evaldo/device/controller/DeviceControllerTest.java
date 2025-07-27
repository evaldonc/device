package dev.evaldo.device.controller;

import dev.evaldo.device.model.dto.DeviceDto;
import dev.evaldo.device.model.request.DeviceRequest;
import dev.evaldo.device.service.DeviceService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class DeviceControllerTest {

    AutoCloseable openMocks;

    @Mock
    private DeviceService deviceService;

    @InjectMocks
    private DeviceController deviceController;

    @BeforeEach
    void setUp() {
        openMocks = openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void testCreateDevice() {
        DeviceRequest req = new DeviceRequest();
        DeviceDto dto = new DeviceDto();
        when(deviceService.createDevice(req)).thenReturn(dto);

        ResponseEntity<DeviceDto> response = deviceController.createDevice(req);

        assertEquals(dto, response.getBody());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void testGetDevice() {
        DeviceDto dto = new DeviceDto();
        when(deviceService.getDevice(1L)).thenReturn(dto);

        ResponseEntity<DeviceDto> response = deviceController.getDevice(1L);

        assertEquals(dto, response.getBody());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void testGetAllDevice() {
        List<DeviceDto> list = List.of(new DeviceDto());
        when(deviceService.getAllDevices()).thenReturn(list);

        ResponseEntity<List<DeviceDto>> response = deviceController.getAllDevice();

        assertEquals(list, response.getBody());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void testGetDevicesByBrand() {
        List<DeviceDto> list = List.of(new DeviceDto());
        when(deviceService.getDevicesByBrand("brand")).thenReturn(list);

        ResponseEntity<List<DeviceDto>> response = deviceController.getDevicesByBrand("brand");

        assertEquals(list, response.getBody());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void testGetDevicesByState() {
        List<DeviceDto> list = List.of(new DeviceDto());
        when(deviceService.getDevicesByState("state")).thenReturn(list);

        ResponseEntity<List<DeviceDto>> response = deviceController.getDevicesByState("state");

        assertEquals(list, response.getBody());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void testUpdateDevice() {
        DeviceRequest req = new DeviceRequest();
        DeviceDto dto = new DeviceDto();
        when(deviceService.updateDevice(1L, req)).thenReturn(dto);

        ResponseEntity<DeviceDto> response = deviceController.updateDevice(1L, req);

        assertEquals(dto, response.getBody());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void testDeleteDevice() {
        doNothing().when(deviceService).deleteDevice(1L);

        ResponseEntity<Void> response = deviceController.deleteDevice(1L);

        assertEquals(200, response.getStatusCode().value());
        assertNull(response.getBody());
    }
}