package com.newland.mybatis.handler;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newland.mall.utils.JsonUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StringArrayTypeHandler extends BaseTypeHandler<String[]> {
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.setSerializationInclusion(Include.NON_NULL);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String[] ts, JdbcType jdbcType) throws SQLException {
        if (ts.length > 0) {
            StringBuffer result = new StringBuffer();
            for (String value : ts) {
                result.append(value).append(",");
            }
            result.deleteCharAt(result.length() - 1);
            ps.setString(i, result.toString());
        } else {
            ps.setString(i, null);
        }
    }

    @Override
    public String[] getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        return toArray(resultSet.getString(columnName));
    }

    @Override
    public String[] getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        return toArray(resultSet.getString(columnIndex));
    }

    @Override
    public String[] getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        return toArray(callableStatement.getString(columnIndex));
    }

    String[] toArray(String columnValue) {
        if (columnValue == null) {
            return new String[0];
        }
        if (columnValue.startsWith("[")) {
            return JsonUtil.parseList(columnValue, String.class).toArray(String[]::new);
        } else {
            return columnValue.split(",");
        }
    }

}