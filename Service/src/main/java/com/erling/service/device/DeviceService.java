package com.erling.service.device;

import com.erling.dao.device.DeviceMapper;
import com.erling.entity.device.Device;
import com.erling.utils.result.Result;
import com.erling.utils.result.ResultEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class DeviceService {
      DeviceMapper  deviceMapper;
      public DeviceService(DeviceMapper deviceMapper) {
          this.deviceMapper = deviceMapper;
      }
      public ResponseEntity<Result<?>> addDevice(Device device) {
          if(deviceMapper.getDeviceByTopic(device.getDeviceTopic()) != null || deviceMapper.getDeviceByPid(device.getPid()) != null){
              return ResponseEntity.ok(new Result<>(
                      ResultEnum.DEVICE_EXIST,
                      null
              ));
          }
          device.setDate(LocalDateTime.now());
          return ResponseEntity.ok(new Result<>(
                 ResultEnum.DEVICE_ADD_SUCCESS,
                 deviceMapper.insertDevice(device)
          ));
      }
      public ResponseEntity<Result<?>> getDevicesByEmail(String email) {
          List<Device> devices = deviceMapper.getDevicesByEmail(email);
          if(devices != null){
              return ResponseEntity.ok(new Result<>(
                      ResultEnum.DEVICE_SELECT_SUCCESS,
                      devices
              ));
          }
          return ResponseEntity.ok(new Result<>(
                  ResultEnum.DEVICE_SELECT_FAIL,
                  null
          ));
      }

      public ResponseEntity<Result<?>> deleteDevice(int pid,String email) {
          if(deviceMapper.getDeviceByPidAndEmail(pid,email) != null){
              return ResponseEntity.ok(new Result<>(
                      ResultEnum.DEVICE_DELETE_SUCCESS,
                      deviceMapper.deleteDevice(pid,email)
              ));
          }
          return ResponseEntity.ok(new Result<>(
                  ResultEnum.DEVICE_NOT_EXIST,
                  null
          ));
      }
      public ResponseEntity<Result<?>> getDevice(int pid,String email) {
          if(deviceMapper.getDeviceByPidAndEmail(pid,email) != null){
              return ResponseEntity.ok(new Result<>(
                      ResultEnum.DEVICE_SELECT_SUCCESS,
                      deviceMapper.getDeviceByPidAndEmail(pid,email)
              ));
          }
          return ResponseEntity.ok(new Result<>(
                  ResultEnum.DEVICE_NOT_EXIST,
                  null
          ));
      }
      public ResponseEntity<Result<?>> updateDevice(Device device) {
          Device oldDevice = deviceMapper.getDeviceByPidAndEmail(device.getPid(),device.getUserEmail());

          if( oldDevice!= null){
              if (!oldDevice.getDeviceTopic().equals(device.getDeviceTopic())) {
                  Device newDevice = deviceMapper.getDeviceByTopic(device.getDeviceTopic());
                  if (newDevice != null && !Objects.equals(newDevice.getPid(), device.getPid())) {
                      return ResponseEntity.ok(new Result<>(
                              200,
                              "不能重复设置相同的设备主题",
                              null
                      ));
                  }
              }
              device.setDate(LocalDateTime.now());
              return ResponseEntity.ok(new Result<>(
                      ResultEnum.DEVICE_UPDATE_SUCCESS,
                      deviceMapper.updateDevice(device)
              ));
          }
          return ResponseEntity.ok(new Result<>(
                  ResultEnum.DEVICE_NOT_EXIST,
                  null
          ));
      }
}
