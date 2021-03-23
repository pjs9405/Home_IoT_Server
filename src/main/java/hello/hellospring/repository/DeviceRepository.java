package hello.hellospring.repository;


import hello.hellospring.domain.Device;
import java.util.List;

public interface DeviceRepository {
    List<Device> findAll(int usrId);
}
