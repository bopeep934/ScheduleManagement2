package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.dto.WriterRequestDto;
import com.example.schedulemanagement.dto.WriterResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.entity.Writer;
import com.example.schedulemanagement.repository.WriterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WriterServiceImpl implements WriterService{

    private final WriterRepository writerRepository;

    public WriterServiceImpl(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }


    @Override
    public WriterResponseDto saveWriter(WriterRequestDto requestDto) {
        // 요청받은 데이터로 Memo 객체 생성
        Writer writer = new Writer(requestDto.getWriter_id(), requestDto.getName(), requestDto.getEmail(),requestDto.getRegistration_date(),requestDto.getModification_date());

        // 저장
        return writerRepository.saveWriter(writer);
    }

    @Override
    public List<WriterResponseDto> listAllWriter() {
        return writerRepository.listAllWriter();
    }

    @Override
    public WriterResponseDto findWriterById(String writer_id) {
        Writer writer =writerRepository.findWriterById(writer_id);

        return new WriterResponseDto(writer);
    }

    @Override
    public void deleteWriter(String id) {
        int deletedRow = writerRepository.deleteWriter(id);
        // 삭제된 row가 0개 라면
        if (deletedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
    }

}
