# Home_IoT_Server

## 1. 서비스 소개
IoT 기기를 제어하기 위한 REST API 구현 (기기목록 조회, 기기 정보 상세 조회, 기기 상태 업데이트 등)

## 2. 개발환경
    IDE : IntelliJ
    Language : JAVA
    Framework : Spring Boot

## 3. ERD
<img src="https://user-images.githubusercontent.com/43440240/113035966-ab68e180-91ce-11eb-9d04-89d0baed7d0a.png"  width="800" height="450">

* model : 제조사의 모델 정보
* device : 사용자가 보유한 기기 목록
* resource : device가 보유한 resource 목록 (ex: power-switch, connection-status)
* user : 사용자 목록
* log : 제어 이력 저장을 위한 테이블

## 4. 코드 예시
```java
//기기목록 조회 api
@GetMapping("/device/list") 
@ResponseBody 
public Map<String,Object> list(@RequestHeader(value="usrId", defaultValue="1")int usrId) throws Exception{

    Map<String, Object> map = new HashMap<>();
    Map<String,Object> data = new HashMap<>();
    List<Device> deviceList = deviceService.findDevices(usrId); //device list 조회

    Device tmp;
    for (int i =0 ;i < deviceList.size() ; i++) {
        tmp = deviceList.get(i);
        tmp.setResource(resourceService.findResources(tmp,deviceList,false)); // resource list 조회
    }
    data.put("deviceCount",deviceList.size());
    data.put("deviceList",deviceList);
    map.put("data",data);
    map.put("responseCode",200);
    return map;
}
```
