package com.softcaribbean.veterinariaXYZ.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softcaribbean.veterinariaXYZ.util.UtilDate;
import lombok.Data;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Data
public class Mascotas implements Serializable {

    private static final long serialVersionUID = 1L;
    private int nmid;
    private String dsnombrecompleto;
    private String dsespecie;
    private String dsraza;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dtfechanacimiento;
    private int nmcliente;

    @JsonIgnore
    public void setClientesFromRs(ResultSet rs) throws SQLException {
        nmid = rs.getInt("nmid");
        dsnombrecompleto = rs.getString("dsespecie");
        dsespecie = rs.getString("dsnombrecompleto");
        dsraza = rs.getString("dsraza");
        dtfechanacimiento = UtilDate.getLocalDate(rs.getDate("dtfechanacimiento"));
        nmcliente = rs.getInt("nmcliente");
    }

}
