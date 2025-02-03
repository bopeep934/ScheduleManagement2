package com.example.schedulemanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PageInfo<T> {
    private List<T> content;     // 데이터 목록
    private int page;
    private int size;
    private int totalElements;
    private int totalPages;

    public PageInfo(List<T> content, int page, int size, int totalElements) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;//일정의 총 갯수
        this.totalPages = (int) Math.ceil((double) totalElements / size);//총 요소의 갯수를 size별로 나눠서 페이지수를 구한다.
    }
}
