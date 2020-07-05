package com.dao.config.typeconvertor;


import org.apache.ibatis.type.LocalDateTypeHandler;
import org.springframework.stereotype.Component;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.sql.Date;

@Component
public class LocalDateTypeHandler2 extends LocalDateTypeHandler {

    @Override
    public LocalDate getResult(ResultSet rs, String columnName) throws SQLException {
        Object object = rs.getObject(columnName);
        if (object == null) {
            return null;
        }
        if(object instanceof Date){
            Date object1 = (Date) object;
            return object1.toLocalDate();
        }
        return super.getResult(rs, columnName);
    }


}
