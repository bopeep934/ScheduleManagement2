package com.example.schedulemanagement.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class ScheduleRequestDto {

    private String writer_id;
    private String writer_name;
    private String password;
    private LocalDateTime date;
    private LocalDateTime upDate;
    private LocalDate findDate;
    private String toDo;


}
