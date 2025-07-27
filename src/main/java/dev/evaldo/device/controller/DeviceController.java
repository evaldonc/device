package dev.evaldo.device.controller;

import dev.evaldo.device.model.dto.DeviceDto;
import dev.evaldo.device.model.request.DeviceRequest;
import dev.evaldo.device.service.DeviceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/device")
@Tag(name = "Device", description = "Operations for managing devices")
public class DeviceController {

    private final DeviceService deviceService;

    @Operation(summary = "Create a new device", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "DeviceEntity created successfully"),
            @ApiResponse(responseCode = "403", description = "Unauthorized request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/create")
    public ResponseEntity<DeviceDto> createDevice(@RequestBody DeviceRequest req) {
        return ResponseEntity.ok(deviceService.createDevice(req));
    }

    @Operation(summary = "Get device by ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "403", description = "Unauthorized request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/get")
    public ResponseEntity<DeviceDto> getDevice(@RequestParam Long id) {
        return ResponseEntity.ok(deviceService.getDevice(id));
    }

    @Operation(summary = "Get all devices", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "403", description = "Unauthorized request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/getAll")
    public ResponseEntity<List<DeviceDto>> getAllDevice() {
        return ResponseEntity.ok(deviceService.getAllDevices());
    }

    @Operation(summary = "Get devices by brand", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "403", description = "Unauthorized request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/getByBrand")
    public ResponseEntity<List<DeviceDto>> getDevicesByBrand(@RequestParam String brand) {
        List<DeviceDto> devices = deviceService.getDevicesByBrand(brand).stream().toList();
        return ResponseEntity.ok(devices);
    }

    @Operation(summary = "Get devices by state", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "403", description = "Unauthorized request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/getByState")
    public ResponseEntity<List<DeviceDto>> getDevicesByState(@RequestParam String state) {
        List<DeviceDto> devices = deviceService.getDevicesByState(state).stream().toList();
        return ResponseEntity.ok(devices);
    }

    @Operation(summary = "Update device by ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "DeviceEntity updated successfully"),
            @ApiResponse(responseCode = "403", description = "Unauthorized request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/update")
    public ResponseEntity<DeviceDto> updateDevice(@RequestParam Long id, @RequestBody DeviceRequest req) {
        return ResponseEntity.ok(deviceService.updateDevice(id, req));
    }

    @Operation(summary = "Delete device by ID", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "DeviceEntity deleted successfully"),
            @ApiResponse(responseCode = "403", description = "Unauthorized request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteDevice(@RequestParam Long id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.ok().build();
    }

}
