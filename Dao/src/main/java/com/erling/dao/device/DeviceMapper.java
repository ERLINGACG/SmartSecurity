package com.erling.dao.device;

import com.erling.entity.device.Device;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeviceMapper  {

    @Select("SELECT * FROM smartsecuritydb.device WHERE pid = #{pid}")
    Device getDeviceByPid(int pid);

    @Select("SELECT * FROM smartsecuritydb.device WHERE deviceTopic = #{topic}")
    Device getDeviceByTopic(String topic);

    @Select("SELECT * FROM device WHERE userEmail = #{email}")
    List<Device> getDevicesByEmail(String email);

    @Select("SELECT * FROM device WHERE pid = #{pid} AND userEmail = #{email}")
    Device getDeviceByPidAndEmail(int pid,String email);



    @Insert("INSERT INTO smartsecuritydb.device " +
            "(PID, DEVICENAME, DEVICETOPIC, DEVICETYPE, USEREMAIL, DEVICEDESCRIBE, DATE) VALUES" +
            " (#{pid}, #{deviceName}, #{deviceTopic}, #{deviceType}, #{userEmail}, #{deviceDescribe}, #{date})")
    boolean insertDevice(Device device);


    @Update("UPDATE smartsecuritydb.device SET " +
            "deviceName = #{deviceName}, deviceTopic = #{deviceTopic}, deviceType = #{deviceType}, " +
            "deviceDescribe = #{deviceDescribe},date = #{date} " +
            "WHERE pid = #{pid} AND userEmail = #{userEmail}")
    boolean updateDevice(Device device);

    @Delete("DELETE FROM smartsecuritydb.device WHERE pid = #{pid} AND userEmail = #{email}")
    boolean deleteDevice(int pid,String email);
}
