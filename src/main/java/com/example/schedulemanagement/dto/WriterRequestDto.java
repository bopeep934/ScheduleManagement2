package com.example.schedulemanagement.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class WriterRequestDto {

    private String writer_id;

    private String name;

    @Email
    private String email;

    private LocalDateTime registration_date;

    private LocalDateTime modification_date;
}
