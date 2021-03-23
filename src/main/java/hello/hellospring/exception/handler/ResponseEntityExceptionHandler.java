package hello.hellospring.exception.handler;

import hello.hellospring.exception.DataMissingException;
import hello.hellospring.exception.ResourceNotFoundException;
import hello.hellospring.exception.UserIdNotExistException;
import hello.hellospring.exception.domain.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.xml.crypto.Data;

@ControllerAdvice
public class ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseError handleResourceNotFound(ResourceNotFoundException e) {
        ResponseError responseError = new ResponseError(456,"Resource가 없습니다.");
        return responseError;
    }

    @ExceptionHandler(value = {MissingRequestHeaderException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseError handleHeaderNotFound(MissingRequestHeaderException e) {
        ResponseError responseError = new ResponseError(457,"User Id가 없습니다.");
        return responseError;
    }

    @ExceptionHandler(value = {DataMissingException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseError handleDataMissing(DataMissingException e) {
        ResponseError responseError = new ResponseError(458,"누락된 데이터가 있습니다.");
        return responseError;
    }

    @ExceptionHandler(value = {UserIdNotExistException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseError handleUsrIdNotExist(UserIdNotExistException e) {
        ResponseError responseError = new ResponseError(459,"존재하지 않는 user id 입니다.");
        return responseError;
    }

}
