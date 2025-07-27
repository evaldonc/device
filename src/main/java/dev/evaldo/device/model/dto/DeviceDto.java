package dev.evaldo.device.model.dto;

import dev.evaldo.device.model.enums.StateType;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class DeviceDto {

    private Long id;
    private String name;
    private String brand;
    private StateType state;
    private Timestamp creationTime;

}
