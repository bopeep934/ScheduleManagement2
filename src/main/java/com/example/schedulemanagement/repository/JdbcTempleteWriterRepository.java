package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.dto.WriterRequestDto;
import com.example.schedulemanagement.dto.WriterResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.entity.Writer;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTempleteWriterRepository implements WriterRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTempleteWriterRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public WriterResponseDto saveWriter(Writer writer) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        jdbcInsert.withTableName("writer");


        Map<String, Object> parameters = new HashMap<>();
        parameters.put("writer_id", writer.getWriter_id());
        parameters.put("name", writer.getName());
        parameters.put("email", writer.getEmail());
        parameters.put("registration_date", writer.getRegistration_date());
        parameters.put("modification_date", writer.getModification_date());

        jdbcInsert.execute(new MapSqlParameterSource(parameters));

        return new WriterResponseDto(writer.getWriter_id(), writer.getName(), writer.getEmail(), writer.getRegistration_date(), writer.getModification_date());
    }

    @Override
    public List<WriterResponseDto> listAllWriter() {
        return jdbcTemplate.query("select * from writer", writerRowMapper());
    }

    @Override
    public Writer findWriterById(String writer_id) {
        List<Writer> result = jdbcTemplate.query("select * from writer where writer_id = ?", writerRowMapperV2(), writer_id);

        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + writer_id));

    }

    @Override
    public int deleteWriter(String writer_id) {
        return jdbcTemplate.update("delete from writer where writer_id = ?", writer_id);

    }


    private RowMapper<WriterResponseDto> writerRowMapper() {
        return new RowMapper<WriterResponseDto>() {
            @Override
            public WriterResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new WriterResponseDto(
                        rs.getString("writer_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getTimestamp("registration_date").toLocalDateTime(),
                        rs.getTimestamp("modification_date").toLocalDateTime()
                );
            }

        };
    }

    private RowMapper<Writer> writerRowMapperV2() {
        return new RowMapper<Writer>() {
            @Override
            public Writer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Writer(
                        rs.getString("writer_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getTimestamp("registration_date").toLocalDateTime(),
                        rs.getTimestamp("modification_date").toLocalDateTime()
                );
            }
        };
    }


}
