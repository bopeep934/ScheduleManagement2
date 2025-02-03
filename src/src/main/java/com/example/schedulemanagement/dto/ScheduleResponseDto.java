package com.example.schedulemanagement.dto;

import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.entity.Writer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor// 이 annotation을 쓰면 필드에 따른 생성자를 자동으로 생성시켜줌. 그래서 따로 생성자를 작성하지 않아도 괜찮음.
public class ScheduleResponseDto {

    private Long id;
    private String writer_name;
    private LocalDateTime date;
    private LocalDateTime upDate;
    private String toDo;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.writer_name = schedule.getWriter_name();
        this.date = schedule.getDate();
        this.upDate= schedule.getUpDate();
        this.toDo = schedule.getToDo();
    }

}
