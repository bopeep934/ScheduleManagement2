package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.ScheduleRequestDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.dto.WriterRequestDto;
import com.example.schedulemanagement.dto.WriterResponseDto;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface WriterService {
    WriterResponseDto saveWriter(WriterRequestDto dto);

    List<WriterResponseDto> listAllWriter();

    WriterResponseDto findWriterById(String id);

    void deleteWriter(String id);

}
