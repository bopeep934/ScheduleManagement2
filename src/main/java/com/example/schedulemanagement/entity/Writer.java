package com.example.schedulemanagement.entity;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Writer {
    private String writer_id;

    private String name;

    private String email;

    private final LocalDateTime registration_date;

    private LocalDateTime modification_date;

//    public Writer(String writer_id,String name, String email, LocalDateTime registration_date, LocalDateTime modification_date){
//        this.writer_id=writer_id;
//        this.name=name;
//        this.email=email;
//        this.registration_date=registration_date;
//        this.modification_date=modification_date;
//    }


}
