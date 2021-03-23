package hello.hellospring.repository;

import hello.hellospring.domain.Device;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class DeviceRepositoryImpl implements DeviceRepository{
    private final JdbcTemplate jdbcTemplate;

    public DeviceRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Device> findAll(int usrId) {
        return jdbcTemplate.query("SELECT device.id," +
                        "service_target_sequence,name,id_str,external,type_code " +
                        "FROM device " +
                        "INNER JOIN model " +
                        "ON device.model_id = model.id " +
                        "WHERE usr_id = ?",deviceRowMapper(),usrId);
    }

    private RowMapper<Device> deviceRowMapper() {
        return (rs,rowNum)-> {
            Device device = new Device();
            device.setDeviceSeq(rs.getInt("id"));
            device.setDeviceName(rs.getString("name"));
            device.setExternal(rs.getString("external"));
            device.setModelId(rs.getString("id_str"));
            device.setServiceTargetSeq(rs.getString("service_target_sequence"));
            device.setModelTypeCode(rs.getInt("type_code"));
            return device;

        };
    }
}
