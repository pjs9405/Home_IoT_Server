package hello.hellospring.repository;

import hello.hellospring.domain.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ResourceRepository {
    List<Resource> findAll(int deviceId);
    int updateResource(int deviceSeq, String group, boolean value);
}
