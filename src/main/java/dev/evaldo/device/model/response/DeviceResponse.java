package dev.evaldo.device.model.response;

import dev.evaldo.device.model.enums.StateType;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class DeviceResponse {

    private Long id;
    private String name;
    private String brand;
    private StateType state;
    private Timestamp creationTime;

}
