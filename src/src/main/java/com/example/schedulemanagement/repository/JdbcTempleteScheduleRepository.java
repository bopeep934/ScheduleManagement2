package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.entity.PageInfo;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcTempleteScheduleRepository implements ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTempleteScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule) {
        //INSERT Query를 직접 작성하지 않아도 된다.
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("writer_id", schedule.getWriter_id());
        parameters.put("writer_name", schedule.getWriter_name());
        parameters.put("password", schedule.getPassword());
        parameters.put("registration_date", schedule.getDate());
        parameters.put("modification_date", schedule.getUpDate());
        parameters.put("todo", schedule.getToDo());

        //저장 후 생성된 key값 number 타입으로 반환하는 메서드
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleResponseDto(key.longValue(),  schedule.getWriter_name(), schedule.getDate(), schedule.getUpDate(), schedule.getToDo());


    }
    public PageInfo<ScheduleResponseDto> findPages(int page, int size){

        int offset = (page - 1) * size;

        List<ScheduleResponseDto> pages = jdbcTemplate.query("SELECT * FROM schedule ORDER BY id LIMIT ? OFFSET ?",scheduleRowMapper(), size, offset);

        // 전체 데이터 개수 조회
        int totalElements = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM schedule", Integer.class);

        return new PageInfo<ScheduleResponseDto>(pages, page, size, totalElements);
    }


    @Override
    public List<ScheduleResponseDto> findAllSchedule() {
        return jdbcTemplate.query("select * from schedule", scheduleRowMapper());
    }

    @Override
    public List<ScheduleResponseDto> findScheduleByWriter(String writer_id) {
        return jdbcTemplate.query("select * from schedule where writer_id=?", scheduleRowMapper(), writer_id);
    }

    @Override
    public List<ScheduleResponseDto> findScheduleByCondition(String writer_id, LocalDate upDate) {
        return  jdbcTemplate.query("select * from schedule where writer_id = ? and DATE(modification_date) >= ? order by modification_date desc", scheduleRowMapper(), writer_id, upDate);
    }

    @Override
    public List<ScheduleResponseDto> findScheduleByUpdate(LocalDate upDate) {
        return jdbcTemplate.query("select * from schedule where DATE(modification_date) >= ? order by modification_date desc", scheduleRowMapper(), upDate);
    }

//    @Override
//    public Optional<Schedule> findScheduleById(Long id) {//사용 안함
//        List<Schedule> result = jdbcTemplate.query("select * from schedule where id=?", scheduleRowMapperV2(), id);
//
//        return result.stream().findAny();//findAny()가 null값도 에러없이 처리해준다고 함.
//
//    }

    @Override
    public Schedule findScheduleByIdOrElseThrow(Long id) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapperV2(), id);


        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));
    }//에러메시지가 뜨질 않음.

    @Override
    public int updateSchedule(Long id, String writer_id, String todo) {
        System.out.println(LocalDateTime.now());
        return jdbcTemplate.update("update schedule set writer_id = ?, todo = ? , modification_date = ? where id = ?", writer_id, todo, LocalDateTime.now(), id);
    }

//    @Override

    /// /    public int updateTodo(Long id, String todo) { 시간 남으면 추가 하기;
    /// /        return jdbcTemplate.update("update schedule set todo = ? where id = ?", todo, id);
    /// /    }

    @Override
    public int deleteSchedule(Long id, String password) {
        //    if(jdbcTemplate.query("select * from schedule where id = ? and password =?", id, password )!=0)
        return jdbcTemplate.update("delete from schedule where id = ? and password= ? ", id, password);

    }



    private RowMapper<ScheduleResponseDto> scheduleRowMapper() {
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("writer_name"),
                        rs.getTimestamp("registration_date").toLocalDateTime(),
                        rs.getTimestamp("modification_date").toLocalDateTime(),
                        rs.getString("todo")
                );
            }

        };
    }

    private RowMapper<Schedule> scheduleRowMapperV2() {
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("id"),
                        rs.getString("writer_name"),
                        rs.getTimestamp("registration_date").toLocalDateTime(),
                        rs.getTimestamp("modification_date").toLocalDateTime(),
                        rs.getString("todo")
                );
            }
        };
    }
}
