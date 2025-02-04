package com.example.schedulemanagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {//일정 객체. 일정 하나의 정보를 담고 있다.

    private Long id;// 일정 고유 아이디, 자동 생성

    private String writer_id;//작성자

    private String writer_name;//작성자 이름 넣을 변수

    private String password;//비밀번호

 // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd")
    private final LocalDateTime date;//생성일, 변경 불가

    private LocalDateTime upDate;//수정일,

    private String toDo;//할일


    public Schedule(String writer_id, String password, String writer_name, LocalDateTime date,  LocalDateTime upDate, String toDo) {//최초 생성 시 생성자
        this.writer_id = writer_id;
        this.writer_name = writer_name;
        this.password = password;
        this.date = date;
        this.upDate= upDate;
        this.toDo = toDo;
    }

    public Schedule(Long id, String writer_name, LocalDateTime date,  LocalDateTime upDate, String toDo) {//최초 생성 시 생성자
        this.id = id;
        this.writer_name = writer_name;
        this.date = date;
        this.upDate= upDate;
        this.toDo = toDo;
    }

//    public void update(LocalDateTime upDate, Long writer_id, String toDo) {//수정1: 전체적으로 수정날짜와 할일을 수정 한다. 갑자기 든 생각인데 자동생성 수정날짜를 굳이 메소드로 구현할 필요가 있을까?
//        //생각해보니 답은 yes인게(아마도), 고유 번호는 절대적이지만 이건 절대적이지 않으니까.
//        this.upDate = upDate;
//        this.writer= writer;
//        this.toDo = toDo;
//    }
//
//    public void updateWriter(LocalDateTime upDate, Long writer_id) {//수정2. 작성자만 수정
//        this.upDate = upDate;
//        this.writer = writer;
//    }
//
//    public void updateToDo(LocalDateTime upDate, String toDo) {//수정3. 할 일만 수정
//        this.upDate = upDate;
//        this.toDo = toDo;
//    }
}
