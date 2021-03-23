package hello.hellospring.service;

import hello.hellospring.domain.Device;
import hello.hellospring.exception.ResourceNotFoundException;
import hello.hellospring.exception.UserIdNotExistException;
import hello.hellospring.repository.DeviceRepositoryImpl;
import hello.hellospring.repository.ResourceRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {
    private DeviceRepositoryImpl deviceRepositoryImpl;
    @Autowired
    public DeviceService(DeviceRepositoryImpl deviceRepositoryImpl) {
        this.deviceRepositoryImpl = deviceRepositoryImpl;
    }

    public List<Device> findDevices(int usrId) throws Exception{
        List<Device> deviceList = deviceRepositoryImpl.findAll(usrId);
        if(deviceList.size() == 0) {
            throw new UserIdNotExistException();
        }
        return deviceList;
    }

}
