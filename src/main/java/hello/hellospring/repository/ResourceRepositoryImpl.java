package hello.hellospring.repository;

import hello.hellospring.domain.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ResourceRepositoryImpl implements ResourceRepository{
    private final JdbcTemplate jdbcTemplate;
    public ResourceRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Resource> findAll(int deviceId) {
        return jdbcTemplate.query("SELECT id,grp,code,value " +
                "FROM resource " +
                "WHERE device_id = ?",resourceRowMapper(),deviceId);
    }

    @Override
    public int updateResource(int deviceSeq, String group, boolean value) {
        jdbcTemplate.update("UPDATE resource SET value = ? " +
                "WHERE device_id = ? AND grp = ?",value,deviceSeq,group);
        List<Resource> tmp = jdbcTemplate.query("SELECT id,grp,code,value " +
                "FROM resource " +
                "WHERE device_id = ? AND grp = ?",resourceRowMapper(),deviceSeq,group);
        return tmp.get(0).getId();
    }

    private RowMapper<Resource> resourceRowMapper() {
        return (rs,rowNum)-> {
            Resource resource = new Resource();
            resource.setId(rs.getInt("id"));
            resource.setGroup(rs.getString("grp"));
            resource.setCode(rs.getString("code"));
            resource.setValue(rs.getBoolean("value"));
            return resource;
        };
    }
}
