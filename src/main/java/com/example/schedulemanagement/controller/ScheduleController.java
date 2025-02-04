package com.example.schedulemanagement.controller;

import com.example.schedulemanagement.dto.PageRequestDto;
import com.example.schedulemanagement.dto.ScheduleRequestDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.PageInfo;
import com.example.schedulemanagement.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController //@controller+ @ResponseBody
@RequestMapping("/schedule") //Prefix
public class ScheduleController {//main에서 가장 처음 데이터를 처리하는 클래스


    private final ScheduleService scheduleService;

    /**
     * 생성자 주입
     * 클래스가 필요로 하는 의존성을 생성자를 통해 전달하는 방식
     *
     * @param scheduleService @Service로 등록된 MemoService 구현체인 Impl
     */
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto dto) {//일정생성 메소드
/*
        // 식별자가 1씩 증가 하도록 만듦
        Long memoId = memoList.isEmpty() ? 1 : Collections.max(memoList.keySet()) + 1;

        // 요청받은 데이터로 Memo 객체 생성
        Memo memo = new Memo(memoId, dto.getTitle(), dto.getContents());

        // Inmemory DB에 Memo 메모
        memoList.put(memoId, memo);
*/ //역할이 분담되므로 전부 지운다.
        return new ResponseEntity<>(scheduleService.saveSchedule(dto), HttpStatus.CREATED);//상태메시지 반환과 동시에 c-> s호출하며 요청 dto보냄.
    }

    @GetMapping
    public List<ScheduleResponseDto> findAllSchedule() {//전체일정조회

        return scheduleService.findAllSchedule();

    }

    @GetMapping("/list")
    public List<ScheduleResponseDto> findScheduleByWriter(@RequestBody ScheduleRequestDto dto) {//작성자id별+ 기간별 조회
        List<ScheduleResponseDto> findScheduleByCondition = null;

        if (dto.getWriter_name() != null && dto.getFindDate() != null)
            findScheduleByCondition = scheduleService.findScheduleByCondition(dto.getWriter_name(), dto.getFindDate());
        if (dto.getWriter_name() != null && dto.getFindDate() == null)
            findScheduleByCondition = scheduleService.findScheduleByWriter(dto.getWriter_name());
        if (dto.getFindDate() != null && dto.getWriter_name() == null)
            findScheduleByCondition = scheduleService.findScheduleByUpdate(dto.getFindDate());

        return findScheduleByCondition;

    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable("id") Long id) {//id별 선택한 일정 조회
        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(//선택한 일정의 작성자명, 할 일 수정하기(패스워드도 인자로 받아야함)
                                                              @PathVariable("id") Long id,
                                                              @RequestBody ScheduleRequestDto dto
    ) {
        return new ResponseEntity<>(scheduleService.updateSchedule(id, dto.getWriter_name(), dto.getToDo()), HttpStatus.OK);
    }//요청객체의 정보를 받아 응답받기

//    @PatchMapping("/{id}") //시간이 남으면 구현
//    public ResponseEntity<ScheduleResponseDto> updateToDo(
//            @PathVariable Long id,
//            @RequestBody ScheduleRequestDto dto
//    ) {
//        return new ResponseEntity<>(scheduleService.updateSchedule(id, dto.getWriter(), dto.getToDo()), HttpStatus.OK);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable("id") Long id, @RequestBody Map<String, String> passwordMap) {//선택한 일정 아이디 받아 삭제하기
        try {
            scheduleService.deleteSchedule(id, passwordMap);
        } catch (ResponseStatusException e) {
            log.error(e.getReason());
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/pages")
    public PageInfo<ScheduleResponseDto> findPages(    @RequestParam("page") int page,
                                                       @RequestParam("size") int size) {
        log.info("page={}, size={}", page, size);
   //     PageRequestDto dto=new PageRequestDto(page,size);
        return scheduleService.findPages(page,size);
    }

}
