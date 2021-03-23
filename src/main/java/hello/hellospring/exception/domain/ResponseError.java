package hello.hellospring.exception.domain;

import java.util.HashMap;
import java.util.Map;

public class ResponseError {
    private int responseCode;
    private Map<String,Object> data;


    public ResponseError() {
    }

    public ResponseError(int code, String message) {
        data = new HashMap<>();
        responseCode = 500;
        data.put("resultCode",code);
        data.put("resultMessage",message);

    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
