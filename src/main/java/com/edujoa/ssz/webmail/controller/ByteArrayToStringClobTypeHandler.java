package com.edujoa.ssz.webmail.controller;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.*;

public class ByteArrayToStringClobTypeHandler extends BaseTypeHandler<byte[]> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, byte[] parameter, JdbcType jdbcType) throws SQLException {
        String string = new String(parameter, StandardCharsets.UTF_8);
        ps.setString(i, string);
    }

    @Override
    public byte[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Clob clob = rs.getClob(columnName);
        return clobToByteArray(clob);
    }

    @Override
    public byte[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Clob clob = rs.getClob(columnIndex);
        return clobToByteArray(clob);
    }

    @Override
    public byte[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Clob clob = cs.getClob(columnIndex);
        return clobToByteArray(clob);
    }

    private byte[] clobToByteArray(Clob clob) throws SQLException {
        if (clob == null) {
            return null;
        }
        try (InputStreamReader reader = new InputStreamReader(clob.getAsciiStream(), StandardCharsets.UTF_8);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            char[] buffer = new char[2048];
            int length;
            while ((length = reader.read(buffer)) != -1) {
                baos.write(new String(buffer, 0, length).getBytes(StandardCharsets.UTF_8));
            }
            return baos.toByteArray();
        } catch (IOException e) {
            throw new SQLException("Error converting CLOB to byte[]", e);
        }
    }
}
