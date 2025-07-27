package dev.evaldo.device.model.request;

import dev.evaldo.device.model.enums.StateType;
import lombok.Data;

@Data
public class DeviceRequest {

    private String name;
    private String brand;
    private StateType state;

}
