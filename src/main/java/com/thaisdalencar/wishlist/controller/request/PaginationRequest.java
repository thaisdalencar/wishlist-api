package com.thaisdalencar.wishlist.controller.request;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


public class PaginationRequest {

    private Integer page = 0;
    private Integer size = 10;

    public PaginationRequest() {
    }

    public Integer getSize() {
        return size;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPage() {
        return page;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Pageable getPagination() {
        return PageRequest.of(page, size);
    }
}

