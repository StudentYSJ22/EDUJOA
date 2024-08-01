package com.edujoa.ssz.webmail.controller;


import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class StringToClobTypeHandler extends BaseTypeHandler<String>{
	
	
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) {
            ps.setCharacterStream(i, new StringReader(parameter), parameter.length());
        } else {
            ps.setNull(i, Types.CLOB);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Clob clob = rs.getClob(columnName);
        return clob != null ? clobToString(clob) : null;
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Clob clob = rs.getClob(columnIndex);
        return clob != null ? clobToString(clob) : null;
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Clob clob = cs.getClob(columnIndex);
        return clob != null ? clobToString(clob) : null;
    }

    private String clobToString(Clob clob) throws SQLException {
        if (clob == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try (Reader reader = clob.getCharacterStream()) {
            char[] buffer = new char[1024];
            int numCharsRead;
            while ((numCharsRead = reader.read(buffer)) != -1) {
                sb.append(buffer, 0, numCharsRead);
            }
        } catch (IOException e) {
            throw new SQLException("Error reading CLOB", e);
        }
        return sb.toString();
    }
}
