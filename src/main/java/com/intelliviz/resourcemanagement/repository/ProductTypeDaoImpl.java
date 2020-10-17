package com.intelliviz.resourcemanagement.repository;

import com.intelliviz.resourcemanagement.model.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
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
        return jdbcTemplate.query("select * from product_type", (rs, rowNum) -> {
            ProductType productType = new ProductType();
            productType.setId(rs.getLong("id"));
            productType.setName(rs.getString("name"));
            productType.setDescription(rs.getString("description"));
            return productType;
        });
    }

    @Override
    public ProductType findByName(String name) {
        String sql = "SELECT * FROM product_type WHERE name = :name";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        return jdbcTemplate.query(sql, params, (rs) -> {
            ProductType productType = new ProductType();
            productType.setId(rs.getLong("id"));
            productType.setName(rs.getString("name"));
            productType.setDescription(rs.getString("description"));
            return productType;
        });
    }

    @Override
    public ProductType findById(long id) {
        String sql = "SELECT * FROM product_type WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return jdbcTemplate.query(sql, params, (rs) -> {
            ProductType productType = new ProductType();
            productType.setId(rs.getLong("id"));
            productType.setName(rs.getString("name"));
            productType.setDescription(rs.getString("description"));
            return productType;
        });
    }

    public ProductType insert(ProductType productType) {
        String sql = "insert into product_type(name, description) values(:name, :description)";
        Map<String, String> params = new HashMap<>();
        params.put("name", productType.getName());
        params.put("description", productType.getDescription());
        jdbcTemplate.update(sql, params);
        return productType;
    }

    public void deleteById(long id) {
        String sql = "DELETE FROM product_type WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbcTemplate.update(sql, params);
    }
}
