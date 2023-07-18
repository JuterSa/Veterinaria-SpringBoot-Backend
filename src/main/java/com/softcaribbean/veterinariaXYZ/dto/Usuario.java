package com.softcaribbean.veterinariaXYZ.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softcaribbean.veterinariaXYZ.util.MessagesConstants;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class Usuario implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int nmid;

    @Size(max = 150,message = MessagesConstants.MAX_VALIDATION_FIELD+" (150)")
    private String dsnombrecompleto;

    @Size(max = 50,message = MessagesConstants.MAX_VALIDATION_FIELD+" (50)")
    private String dscorreo;

    @Size(max = 50,message = MessagesConstants.MAX_VALIDATION_FIELD+" (50)")
    private String dscontraseña;

    @Size(max = 30,message = MessagesConstants.MAX_VALIDATION_FIELD+" (30)")
    private String dsrol;

    @JsonIgnore
    public void setClientesFromRs(ResultSet rs) throws SQLException {
        nmid = rs.getInt("nmid");
        dsnombrecompleto = rs.getString("dsnombrecompleto");
        dscorreo = rs.getString("dscorreo");
        dscontraseña = rs.getString("dscontraseña");
        dsrol = rs.getString("dsrol");
    }

}
