package com.softcaribbean.veterinariaXYZ.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softcaribbean.veterinariaXYZ.util.MessagesConstants;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
@Data
public class Clientes implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private int nmid;
    @Size(max = 10,message = MessagesConstants.MAX_VALIDATION_FIELD+" (10)")
    private String dstipodocumento;
    private int cdidentificacion;
    @Size(max = 150,message = MessagesConstants.MAX_VALIDATION_FIELD+" (150)")
    private String dsnombrecompleto;
    @Size(max = 150,message = MessagesConstants.MAX_VALIDATION_FIELD+" (150)")
    private String dsciudad;
    @Size(max = 150,message = MessagesConstants.MAX_VALIDATION_FIELD+" (150)")
    private String dsdireccion;
    private Long dstelefono;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dtfechacreacion;

    @JsonIgnore
    public void setClientesFromRs(ResultSet rs) throws SQLException {
        nmid = rs.getInt("nmid");
        dstipodocumento = rs.getString("dstipodocumento");
        cdidentificacion = rs.getInt("cdidentificacion");
        dsnombrecompleto = rs.getString("dsnombrecompleto");
        dsciudad = rs.getString("dsciudad");
        dsdireccion = rs.getString("dsdireccion");
        dstelefono = rs.getLong("dstelefono");
        //dtfechacreacion = UtilDate.getLocalDate(rs.getDate("dtfechacreacion"));
    }
    //nmid, dstipodocumento, cdidentificacion, dsnombrecompleto, dsciudad, dsdireccion, dstelefono
}
