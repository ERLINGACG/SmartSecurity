package com.erling.controller.device;

import com.erling.entity.device.Device;
import com.erling.service.device.DeviceService;
import com.erling.utils.result.Result;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/device/api")
public class DeviceController {
    DeviceService  deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addDevice(
            @RequestBody @Valid Device device,
            BindingResult result
    ) {
        if(result.hasErrors()){
        return ResponseEntity.ok().body(
                new Result<>(
                        400,
                        Objects.requireNonNull(result.getFieldError()).getDefaultMessage(),
                        null
                )
        );
        }
       return deviceService.addDevice(device);
    }

    @GetMapping("/select")
    public ResponseEntity<?> getDevicesByEmail(@RequestParam String email
    ) {
        return deviceService.getDevicesByEmail(email);
    }

    @GetMapping("/getDevice")
    public ResponseEntity<Result<?>> getDevice(
          @RequestParam int pid,
          @RequestParam String email
    )
    {
        return deviceService.getDevice(pid, email);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Result<?>> deleteDevice(
            @RequestParam int pid,
            @RequestParam String email
    ) {
        return deviceService.deleteDevice(pid,email);
    }
    @PutMapping("/update")
    public ResponseEntity<Result<?>> updateDevice(
            @RequestBody @Valid Device device,
            BindingResult result
    ) {
        if(result.hasErrors()){
            return ResponseEntity.ok().body(
                    new Result<>(
                            400,
                            Objects.requireNonNull(result.getFieldError()).getDefaultMessage(),
                            null
                    )
            );
        }
        return deviceService.updateDevice(device);
    }


}
