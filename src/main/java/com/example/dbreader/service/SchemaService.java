package com.example.dbreader.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SchemaService {

    private final JdbcTemplate jdbcTemplate;

    public SchemaService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> listTables() {
        String sql = "SELECT table_name FROM information_schema.tables WHERE table_schema = DATABASE() ORDER BY table_name";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("table_name"));
    }

    public List<Map<String, Object>> getColumns(String tableName) {
        String sql = "SELECT column_name, data_type, is_nullable, column_default, character_maximum_length, numeric_precision, numeric_scale " +
                "FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = ? ORDER BY ordinal_position";

        RowMapper<Map<String, Object>> mapper = new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
                Map<String, Object> row = new HashMap<>();
                row.put("name", rs.getString("column_name"));
                row.put("dataType", rs.getString("data_type"));
                row.put("isNullable", "YES".equalsIgnoreCase(rs.getString("is_nullable")));
                row.put("defaultValue", rs.getObject("column_default"));
                row.put("maxLength", rs.getObject("character_maximum_length"));
                row.put("numericPrecision", rs.getObject("numeric_precision"));
                row.put("numericScale", rs.getObject("numeric_scale"));
                return row;
            }
        };

        return jdbcTemplate.query(sql, mapper, tableName);
    }
}








