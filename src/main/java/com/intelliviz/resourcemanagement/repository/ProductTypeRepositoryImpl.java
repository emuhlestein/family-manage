package com.intelliviz.resourcemanagement.repository;

import com.intelliviz.resourcemanagement.model.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("ProductTypeJdbcTemplateRepository")
public class ProductTypeRepositoryImpl implements ProductTypeRepository {

    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public ProductTypeRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
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

    public ProductType insert(ProductType productType) {
        String sql = "insert into product_type(name, description) values(:name, :description)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", productType.getName())
                .addValue("description", productType.getDescription());
        int numRows =  jdbcTemplate.update(sql, params, keyHolder);
        productType.setId(keyHolder.getKey().intValue());
        return productType;
    }

    public Long deleteById(long id) {
        String sql = "DELETE FROM product_type WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbcTemplate.update(sql, params);
        return id;
    }
}
