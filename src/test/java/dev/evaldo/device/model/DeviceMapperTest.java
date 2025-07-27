package dev.evaldo.device.model;

import dev.evaldo.device.model.dto.DeviceDto;
import dev.evaldo.device.model.entity.DeviceEntity;
import dev.evaldo.device.model.enums.StateType;
import dev.evaldo.device.model.request.DeviceRequest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DeviceMapperImpl.class})
class DeviceMapperTest {

    @Autowired
    private DeviceMapper deviceMapper;

    @Test
    void testToEntity() {
        DeviceRequest request = new DeviceRequest();
        request.setName("Device 1");
        request.setBrand("Brand A");
        request.setState(StateType.AVAILABLE);

        DeviceEntity entity = deviceMapper.toEntity(request);

        assertNotNull(entity);
        assertEquals("Device 1", entity.getName());
        assertEquals("Brand A", entity.getBrand());
        assertEquals(StateType.AVAILABLE, entity.getState());
    }

    @Test
    void testToDto() {
        DeviceEntity entity = new DeviceEntity();
        entity.setId(1L);
        entity.setName("Device 1");
        entity.setBrand("Brand A");
        entity.setState(StateType.IN_USE);

        DeviceDto dto = deviceMapper.toDto(entity);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Device 1", dto.getName());
        assertEquals("Brand A", dto.getBrand());
        assertEquals(StateType.IN_USE, dto.getState());
    }
}