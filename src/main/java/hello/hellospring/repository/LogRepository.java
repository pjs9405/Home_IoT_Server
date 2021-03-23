package hello.hellospring.repository;

public interface LogRepository {
    int insertLog(int resourceId, String group, String code, boolean value);
}
