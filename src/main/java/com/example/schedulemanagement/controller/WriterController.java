package com.example.schedulemanagement.controller;


import com.example.schedulemanagement.dto.WriterRequestDto;
import com.example.schedulemanagement.dto.WriterResponseDto;
import com.example.schedulemanagement.service.WriterService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController //@controller+ @ResponseBody
@RequestMapping("/user") //Prefix
public class WriterController {

    private final WriterService writerService;


    public WriterController(WriterService writerService) {
        this.writerService = writerService;
    }

    @PostMapping
    public ResponseEntity<WriterResponseDto> createWriter(@Valid @RequestBody WriterRequestDto dto, BindingResult result) {//일정생성 메소드
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "형식에 맞게 입력해주세요.");

        }
        return new ResponseEntity<>(writerService.saveWriter(dto), HttpStatus.CREATED);//상태메시지 반환과 동시에 c-> s호출하며 요청 dto보냄.

    }

    @GetMapping
    public List<WriterResponseDto> findAllWriter() {//작성자 전체 데이터 조회


        return writerService.listAllWriter();

    }

    @GetMapping("/{id}")
    public ResponseEntity<WriterResponseDto> findWriterById(@PathVariable("id") String id) {//선택한 작성자 id 조회
        return new ResponseEntity<>(writerService.findWriterById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWriter(@PathVariable("id") String id) {//선택한 작성자 삭제
        try {
            writerService.deleteWriter(id);
        } catch (ResponseStatusException e) {
            log.error(e.getReason());
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }
}