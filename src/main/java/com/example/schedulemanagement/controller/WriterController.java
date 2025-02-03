package com.example.schedulemanagement.controller;


import com.example.schedulemanagement.dto.WriterRequestDto;
import com.example.schedulemanagement.dto.WriterResponseDto;
import com.example.schedulemanagement.service.WriterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<WriterResponseDto> createWriter(@RequestBody WriterRequestDto dto) {//일정생성 메소드
/*
        // 식별자가 1씩 증가 하도록 만듦
        Long memoId = memoList.isEmpty() ? 1 : Collections.max(memoList.keySet()) + 1;

        // 요청받은 데이터로 Memo 객체 생성
        Memo memo = new Memo(memoId, dto.getTitle(), dto.getContents());

        // Inmemory DB에 Memo 메모
        memoList.put(memoId, memo);
*/ //역할이 분담되므로 전부 지운다.
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