package com.softcaribbean.veterinariaXYZ.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoUtil {

    private DaoUtil() {
    }

    public static void setPrepareStatement(PreparedStatement ps, Object[] data) throws SQLException {
        if (ps == null) {
            return;
        }
        int i = 1;
        for (var column : data) {
            if (column instanceof Long) {
                ps.setLong(i++, (long) column);
            } else if (column instanceof Integer) {
                ps.setInt(i++, (int) column);
            } else if (column instanceof String) {
                ps.setString(i++, (String) column);
            } else if (column instanceof Double) {
                ps.setDouble(i++, (double) column);
            } else {
                ps.setObject(i++, column);
            }
        }

    }

    public static Double getDoubleMapper(ResultSet resultSet, String column) throws SQLException {
        return resultSet.getObject(column) != null ? resultSet.getDouble(column) : null;
    }

    public static String getString(ResultSet rs, String columnName) {
        try {
            return rs.getString(columnName);
        }catch (SQLException e){
            return null;
        }
    }

    public static long getLong(ResultSet rs, String columnName) {
        try {
            return rs.getLong(columnName);
        }catch (SQLException e){
            return 0;
        }
    }

    public static double getDouble(ResultSet rs, String columnName) {
        try {
            return rs.getDouble(columnName);
        }catch (SQLException e){
            return 0;
        }
    }
}
