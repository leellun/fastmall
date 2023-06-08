package com.newland.mybatis.handler;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class LongArrayTypeHandler extends BaseTypeHandler<Long[]> {
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.setSerializationInclusion(Include.NON_NULL);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Long[] ts, JdbcType jdbcType) throws SQLException {
        if (ts.length > 0) {
            StringBuffer result = new StringBuffer();
            for (Long value : ts) {
                result.append(value).append(",");
            }
            result.deleteCharAt(result.length() - 1);
            ps.setString(i, result.toString());
        } else {
            ps.setString(i, null);
        }
    }

    @Override
    public Long[] getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        return toArray(resultSet.getString(columnName));
    }

    @Override
    public Long[] getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        return toArray(resultSet.getString(columnIndex));
    }

    @Override
    public Long[] getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        return toArray(callableStatement.getString(columnIndex));
    }

    Long[] toArray(String columnValue) {
        if (!StringUtils.hasText(columnValue)) {
            return new Long[0];
        }
        String[] values = columnValue.split(",");
        return Arrays.stream(values).map(Long::valueOf).toArray(Long[]::new);
    }

}