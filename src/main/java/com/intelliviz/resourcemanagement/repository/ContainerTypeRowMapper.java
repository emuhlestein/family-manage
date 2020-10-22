package com.intelliviz.resourcemanagement.repository;

import com.intelliviz.resourcemanagement.model.ContainerType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContainerTypeRowMapper implements RowMapper<ContainerType> {
    @Override
    public ContainerType mapRow(ResultSet rs, int rowNum) throws SQLException {
        ContainerType containerType = new ContainerType();
        containerType.setId(rs.getInt("id"));
        containerType.setName(rs.getString("name"));
        containerType.setDescription(rs.getString("description"));
        return containerType;
    }
}
