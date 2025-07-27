package dev.evaldo.device.model;

import dev.evaldo.device.model.dto.DeviceDto;
import dev.evaldo.device.model.entity.DeviceEntity;
import dev.evaldo.device.model.request.DeviceRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DeviceMapper {

    DeviceDto toDto(DeviceEntity deviceEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationTime", expression = "java(new java.sql.Timestamp(System.currentTimeMillis()))")
    DeviceEntity toEntity(DeviceRequest req);


}
