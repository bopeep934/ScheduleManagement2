package com.example.schedulemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PageRequestDto {
    private int page;
    private int size;

}
