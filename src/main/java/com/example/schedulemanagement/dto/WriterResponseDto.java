package com.example.schedulemanagement.dto;

import com.example.schedulemanagement.entity.Writer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
public class WriterResponseDto {

    private String writer_id;
    private String name;
    private String email;
    private LocalDateTime registration_date;
    private LocalDateTime modification_date;

    public WriterResponseDto(Writer writer){
        writer_id=writer.getWriter_id();
        name=writer.getName();
        email=writer.getEmail();
        registration_date=writer.getRegistration_date();
        modification_date=writer.getModification_date();
    }
}
