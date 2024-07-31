package com.edujoa.ssz.webmail.controller;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

@MappedTypes(List.class)
public class ListToStringTypeHandler extends BaseTypeHandler<List<String>> {
    private static final String DELIMITER = ",";

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
        String value = parameter.stream().collect(Collectors.joining(DELIMITER));
        ps.setString(i, value);
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return convertToList(rs.getString(columnName));
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return convertToList(rs.getString(columnIndex));
    }

    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return convertToList(cs.getString(columnIndex));
    }

    private List<String> convertToList(String value) {
        if (value == null || value.trim().isEmpty()) {
            return Collections.emptyList(); // 빈 리스트로 반환
        }
        return Arrays.asList(value.split(DELIMITER));
    }
	
}
