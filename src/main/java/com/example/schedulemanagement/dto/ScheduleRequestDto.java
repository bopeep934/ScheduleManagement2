package com.example.schedulemanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class ScheduleRequestDto {

    private String writer_id;

    private String writer_name;

    @NotNull(message = "비밀번호를 입력해주세요")
    private String password;

    private LocalDateTime date;

    private LocalDateTime upDate;

    private LocalDate findDate;

    @NotNull
    @Size(min = 1, max = 200, message = "200자 이내로 입력해주세요.")
    private String toDo;


}
