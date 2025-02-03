package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.ScheduleRequestDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.PageInfo;
import com.example.schedulemanagement.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    ScheduleResponseDto saveSchedule(Schedule schedule);//새로운 일정 추가. 스케쥴 객체를 받아 저장하고 응답 반환.

    List<ScheduleResponseDto> findAllSchedule();//모든 일정 반환

    List<ScheduleResponseDto> findScheduleByWriter(String writer_id);

    //   Optional<Schedule> findScheduleById(Long id);//선택한 일정 반환

    Schedule findScheduleByIdOrElseThrow(Long id);

    int updateSchedule(Long id, String writer_id, String contents);

    //  int updateTodo(Long id, String toDo);

    PageInfo<ScheduleResponseDto> findPages(int page, int size);

    int deleteSchedule(Long id, String password);

    List<ScheduleResponseDto> findScheduleByCondition(String writer, LocalDate upDate);

    List<ScheduleResponseDto> findScheduleByUpdate(LocalDate upDate);
}
