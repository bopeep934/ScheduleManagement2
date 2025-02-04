package com.example.schedulemanagement.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class WriterRequestDto {

    private String writer_id;

    private String name;

    @Email(message = "이메일 형식에 맞게 입력해주세요.")
    private String email;

    private LocalDateTime registration_date;

    private LocalDateTime modification_date;
}
