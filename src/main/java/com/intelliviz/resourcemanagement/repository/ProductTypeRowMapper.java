package com.intelliviz.resourcemanagement.repository;

import com.intelliviz.resourcemanagement.model.ProductType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductTypeRowMapper implements RowMapper<ProductType> {
    @Override
    public ProductType mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductType productType = new ProductType();
        productType.setId(rs.getInt("id"));
        productType.setName(rs.getString("name"));
        productType.setDescription(rs.getString("description"));
        return productType;
    }
}
