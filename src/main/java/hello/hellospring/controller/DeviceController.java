package hello.hellospring.controller;

import hello.hellospring.domain.Device;
import hello.hellospring.domain.DeviceForm;
import hello.hellospring.exception.DataMissingException;
import hello.hellospring.exception.ResourceNotFoundException;
import hello.hellospring.service.DeviceService;
import hello.hellospring.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DeviceController {

    private DeviceService deviceService;
    private ResourceService resourceService;

    @Autowired
    public DeviceController(DeviceService deviceService, ResourceService resourceService) {
        this.deviceService = deviceService;
        this.resourceService = resourceService;
    }

    @GetMapping("/device/list")
    @ResponseBody
    public Map<String,Object> list(@RequestHeader(value="usrId", defaultValue="1")int usrId) throws Exception{

        Map<String, Object> map = new HashMap<>();
        Map<String,Object> data = new HashMap<>();
        List<Device> deviceList = deviceService.findDevices(usrId);

        Device tmp;
        for (int i =0 ;i < deviceList.size() ; i++) {
            tmp = deviceList.get(i);
            tmp.setResource(resourceService.findResources(tmp,deviceList,false));
        }
        data.put("deviceCount",deviceList.size());
        data.put("deviceList",deviceList);
        map.put("data",data);
        map.put("responseCode",200);
        return map;
    }

    @PostMapping("/device/findById")
    @ResponseBody
    public Map<String,Object> findById (@RequestHeader(value="usrId", defaultValue="1")int usrId, @RequestBody Device device) throws Exception{
        Map<String,Object> map = new HashMap<>();
        List<Device> deviceList = deviceService.findDevices(usrId);
        device.setResource(resourceService.findResources(device, deviceList,true));
        map.put("data",device);
        map.put("responseCode",200);
        return map;
    }


    @PutMapping("/device/update")
    @ResponseBody
    public Map<String,Object> update(@RequestHeader(value="usrId", defaultValue="1")int usrId, @RequestBody DeviceForm deviceForm) throws Exception{
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> map_in = new HashMap<>();
        List<Device> deviceList = deviceService.findDevices(usrId);
        int count = resourceService.updateResources(deviceForm,deviceList);
        if(count == 1 ) {
            map_in.put("resultMessage","제어 성공");
            map_in.put("resultCode",200);
            map.put("data",map_in);
            map.put("responseCode", 200);
        }
        return map;
    }
}
