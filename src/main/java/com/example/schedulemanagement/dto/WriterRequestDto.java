package com.example.schedulemanagement.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class WriterRequestDto {

    private String writer_id;
    private String name;
    private String email;
    private LocalDateTime registration_date;
    private LocalDateTime modification_date;
}
