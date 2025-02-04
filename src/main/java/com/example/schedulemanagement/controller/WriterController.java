package com.example.schedulemanagement.controller;


import com.example.schedulemanagement.dto.WriterRequestDto;
import com.example.schedulemanagement.dto.WriterResponseDto;
import com.example.schedulemanagement.service.WriterService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
        if (result.hasErrors()) {

            // 에러 메시지를 담을 리스트 생성
            List<String> errorMessages = new ArrayList<>();

            // 필드별 에러 메시지 추출
            for (FieldError error : result.getFieldErrors()) {
                String errorMessage = error.getDefaultMessage();  // 해당 필드의 에러 메시지 가져오기
                errorMessages.add(errorMessage);
            }

            // 에러 메시지들을 한 번에 반환
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.join(", ", errorMessages));
        }
        return new ResponseEntity<>(writerService.saveWriter(dto), HttpStatus.CREATED);

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