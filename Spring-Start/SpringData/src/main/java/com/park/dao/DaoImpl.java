package com.park.dao;

import com.park.model.Car;
import com.park.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import javax.sql.DataSource;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class DaoImpl {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.dataSource = dataSource;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

    }

    //jdbc bsaic
    public Person getPersonById(int id) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM person WHERE id=?")) {
                ps.setObject(1, id);
                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    return new Person(resultSet.getString("name"), resultSet.getInt("age"));
                } else {
                    return null;
                }
            }
        }
    }

    //jdbcTemplate  basic
    public int getPersonAge(int age) {
        String sql = "SELECT age from person WHERE id=?";

        return jdbcTemplate.queryForObject(sql, new Object[]{age}, Integer.class);


    }

    public Person getDriverByCar(String carName) {
        String driverIdSql = "SELECT driver_id FROM car WHERE car_name = ?";

        int driverId = jdbcTemplate.queryForObject(driverIdSql, new Object[]{carName}, Integer.class);
        String personSql = "SELECT * FROM person WHERE id =" + driverId;
        return jdbcTemplate.queryForObject(personSql, new personMapper());


    }

    //rowmapper
    private static final class personMapper implements RowMapper<Person> {

        @Nullable
        @Override
        public Person mapRow(ResultSet resultSet, int i) throws SQLException {
            Person person = new Person();
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            return person;
        }
    }

    //namedParameterJdbcTemplate map
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insertCar(Car car) {
        Person person = car.getDriver();
        String carSql = "INSERT INTO car(driver_id ,car_name) VALUES (:driver_id,:car_name)";
        String personSql = "INSERT INTO person(name,age) VALUES (:name,:age)";
        Map personNamedMap = new HashMap<String, Object>();
        personNamedMap.put("name", person.getName());
        personNamedMap.put("age", person.getAge());
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("drivr_id", 88)
                .addValue("car_name", car.getName());

        namedParameterJdbcTemplate.update(personSql, personNamedMap);
        namedParameterJdbcTemplate.update(carSql, sqlParameterSource);
        return true;

    }

    public void DeletePerson(int id) {
        String sql = "DELETE FROM person WHERE id =?";

        jdbcTemplate.update(sql, id);


    }
}
