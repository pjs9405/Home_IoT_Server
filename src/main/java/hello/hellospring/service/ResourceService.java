package hello.hellospring.service;

import hello.hellospring.domain.Device;
import hello.hellospring.domain.DeviceForm;
import hello.hellospring.domain.Resource;
import hello.hellospring.exception.DataMissingException;
import hello.hellospring.exception.ResourceNotFoundException;
import hello.hellospring.repository.LogRepositoryImpl;
import hello.hellospring.repository.ResourceRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {
    private ResourceRepositoryImpl resourceRepositoryImpl;
    private LogRepositoryImpl logRepositoryImpl;

    @Autowired
    public ResourceService(ResourceRepositoryImpl resourceRepositoryImpl,
                           LogRepositoryImpl logRepositoryImpl) {
        this.resourceRepositoryImpl = resourceRepositoryImpl;
        this.logRepositoryImpl = logRepositoryImpl;
    }

    public List<Resource> findResources(Device device, List<Device> deviceList,boolean chk) throws Exception{
        if(chk) {
            check(deviceList,device);
        }
        return resourceRepositoryImpl.findAll(device.getDeviceSeq());
    }

    private void check(List<Device> deviceList, Device device) throws Exception{
        Device tmp;

        if(device.getDeviceSeq() == 0 ||
                device.getDeviceName() == null ||
                device.getServiceTargetSeq() == null ||
                device.getModelTypeCode() == 0 ||
                device.getExternal() == null ||
                device.getModelId() == null) {
            throw new DataMissingException();
        }
        for (int i =0;i<deviceList.size();i++) {
            tmp = deviceList.get(i);
            if(device.getDeviceSeq() == tmp.getDeviceSeq()
                    && device.getDeviceName().equals(tmp.getDeviceName())
                    && device.getExternal().equals(tmp.getExternal())
                    && device.getModelId().equals(tmp.getModelId())
                    && device.getModelTypeCode() == tmp.getModelTypeCode()
                    && device.getServiceTargetSeq().equals(tmp.getServiceTargetSeq()) ) {
                return;
            }
        }
        throw new ResourceNotFoundException();
    }
    public int updateResources(DeviceForm deviceForm,List<Device> deviceList) throws Exception{
        int deviceSeq;
        String group,code;
        boolean value;
        checkForm(deviceForm, deviceList);
        deviceSeq = deviceForm.getDeviceSeq();
        group = deviceForm.getResource().getGroup();
        code = deviceForm.getResource().getCode();
        value = deviceForm.getResource().isValue();
        int resourceId = resourceRepositoryImpl.updateResource(deviceSeq,group,value);
        return logRepositoryImpl.insertLog(resourceId,group,code,value);
    }

    private void checkForm(DeviceForm deviceForm, List<Device> deviceList) {
        if(deviceForm.getDeviceSeq() == 0 ||
                deviceForm.getDeviceName() == null ||
                deviceForm.getServiceTargetSeq() == null ||
                deviceForm.getModelTypeCode() == 0 ||
                deviceForm.getExternal() == null ||
                deviceForm.getModelId() == null ||
                deviceForm.getResource() == null ||
        deviceForm.getResource().getCode() == null ||
        deviceForm.getResource().getGroup() == null) {
            throw new DataMissingException();
        }
        Device tmp;
        for (int i =0;i<deviceList.size();i++) {
            deviceList.get(i).setResource(resourceRepositoryImpl.findAll(deviceList.get(i).getDeviceSeq()));
            tmp = deviceList.get(i);
            if(deviceForm.getDeviceSeq() == tmp.getDeviceSeq()
                    && deviceForm.getDeviceName().equals(tmp.getDeviceName())
                    && deviceForm.getExternal().equals(tmp.getExternal())
                    && deviceForm.getModelId().equals(tmp.getModelId())
                    && deviceForm.getModelTypeCode() == tmp.getModelTypeCode()
                    && deviceForm.getServiceTargetSeq().equals(tmp.getServiceTargetSeq()) ) {
                for (int j =0 ;j<tmp.getResource().size();j++) {
                    if(deviceForm.getResource().getGroup().equals(tmp.getResource().get(j).getGroup())
                            && deviceForm.getResource().getCode().equals(tmp.getResource().get(j).getCode())) {
                        return;
                    }
                }
            }
        }
        throw new ResourceNotFoundException();
    }
}
