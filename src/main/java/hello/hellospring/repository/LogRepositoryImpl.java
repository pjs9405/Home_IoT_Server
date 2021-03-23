package hello.hellospring.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class LogRepositoryImpl implements LogRepository{
    private final JdbcTemplate jdbcTemplate;

    public LogRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int insertLog(int resourceId, String group, String code, boolean value) {
        return jdbcTemplate.update("INSERT INTO log " +
                "(resource_id, grp, code, value, modified_date)" +
                "VALUES(?, ?, ?, ?, now())",resourceId,group,code,value);
    }
}
