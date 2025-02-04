package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.ScheduleRequestDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.dto.PageRequestDto;
import com.example.schedulemanagement.entity.PageInfo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ScheduleService {//service 인터페이스
     ScheduleResponseDto saveSchedule(ScheduleRequestDto dto);

     List<ScheduleResponseDto> findAllSchedule();

     List<ScheduleResponseDto> findScheduleByWriter(String writer_id);

     ScheduleResponseDto findScheduleById(Long id);

     ScheduleResponseDto updateSchedule(Long id, String writer_id, String toDo);

     void deleteSchedule(Long id, Map<String, String> password);

     List<ScheduleResponseDto> findScheduleByUpdate(LocalDate upDate);

     List<ScheduleResponseDto> findScheduleByCondition(String writer_name, LocalDate upDate);

   //  PageInfo<ScheduleResponseDto> findPages(PageRequestDto dto);

     PageInfo<ScheduleResponseDto> findPages(int page, int size);
}