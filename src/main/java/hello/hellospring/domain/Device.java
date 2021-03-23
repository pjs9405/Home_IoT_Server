package hello.hellospring.domain;

import java.util.List;

public class Device {
    private String serviceTargetSeq;
    private String external;
    private int deviceSeq;
    private int modelTypeCode;
    private String modelId;
    private String deviceName;
    private List<Resource> resource;

    public String getServiceTargetSeq() {
        return serviceTargetSeq;
    }

    public void setServiceTargetSeq(String serviceTargetSeq) {
        this.serviceTargetSeq = serviceTargetSeq;
    }

    public int getDeviceSeq() {
        return deviceSeq;
    }

    public void setDeviceSeq(int deviceSeq) {
        this.deviceSeq = deviceSeq;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public List<Resource> getResource() {
        return resource;
    }

    public void setResource(List<Resource> resource) {
        this.resource = resource;
    }

    public String getExternal() {
        return external;
    }

    public void setExternal(String external) {
        this.external = external;
    }

    public int getModelTypeCode() {
        return modelTypeCode;
    }

    public void setModelTypeCode(int modelTypeCode) {
        this.modelTypeCode = modelTypeCode;
    }

    @Override
    public String toString() {
        return "Device{" +
                "serviceTargetSeq='" + serviceTargetSeq + '\'' +
                ", external='" + external + '\'' +
                ", deviceSeq=" + deviceSeq +
                ", modelTypeCode=" + modelTypeCode +
                ", modelId='" + modelId + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", resource=" + resource +
                '}';
    }
}