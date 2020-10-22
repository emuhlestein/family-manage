package com.intelliviz.resourcemanagement.repository;

import com.intelliviz.resourcemanagement.model.ContainerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContainerTypeDaoImpl implements ContainerTypeDao {

    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public ContainerTypeDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ContainerType> getAll() {
        return jdbcTemplate.query("select * from container_type", new ContainerTypeRowMapper());
    }

    @Override
    public ContainerType findByName(String name) {
        String sql = "SELECT * FROM container_type WHERE name = :name";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        try {
            return jdbcTemplate.queryForObject(sql, params, new ContainerTypeRowMapper());
        } catch(IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public ContainerType findById(long id) {
        String sql = "SELECT * FROM container_type WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        try {
            return jdbcTemplate.queryForObject(sql, params, new ContainerTypeRowMapper());
        } catch(IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    public ContainerType insert(ContainerType containerType) {
        String sql = "insert into container_type(name, description) values(:name, :description)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", containerType.getName())
                .addValue("description", containerType.getDescription());
        int numRows =  jdbcTemplate.update(sql, params, keyHolder);
        containerType.setId(keyHolder.getKey().intValue());
        return containerType;
    }

    public void deleteById(long id) {
        String sql = "DELETE FROM container_type WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbcTemplate.update(sql, params);
    }
}
