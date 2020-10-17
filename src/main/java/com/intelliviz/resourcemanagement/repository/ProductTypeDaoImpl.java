package com.intelliviz.resourcemanagement.repository;

import com.intelliviz.resourcemanagement.model.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductTypeDaoImpl implements ProductTypeDao {

    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public ProductTypeDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ProductType> getAll() {
        return jdbcTemplate.query("select * from product_type", new ProductTypeRowMapper());
    }

    @Override
    public ProductType findByName(String name) {
        String sql = "SELECT * FROM product_type WHERE name = :name";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        try {
            return jdbcTemplate.queryForObject(sql, params, new ProductTypeRowMapper());
        } catch(IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public ProductType findById(long id) {
        String sql = "SELECT * FROM product_type WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        try {
            return jdbcTemplate.queryForObject(sql, params, new ProductTypeRowMapper());
        } catch(IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    public int insert(ProductType productType) {
        String sql = "insert into product_type(name, description) values(:name, :description)";
        Map<String, String> params = new HashMap<>();
        params.put("name", productType.getName());
        params.put("description", productType.getDescription());
        return jdbcTemplate.update(sql, params);
    }

    public void deleteById(long id) {
        String sql = "DELETE FROM product_type WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbcTemplate.update(sql, params);
    }
}
