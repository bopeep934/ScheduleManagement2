package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.WriterResponseDto;
import com.example.schedulemanagement.entity.Writer;
import java.util.List;
import java.util.Optional;

public interface WriterRepository {
    public WriterResponseDto saveWriter(Writer writer);

    public List<WriterResponseDto> listAllWriter();

    public Writer findWriterById(String id);

    public int deleteWriter(String id);
}
