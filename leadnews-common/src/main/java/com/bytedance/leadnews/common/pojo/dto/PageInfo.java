package com.bytedance.leadnews.common.pojo.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageInfo<E> {
    /**
     * 当前页
     */
    private Integer page;

    /**
     * 每页大小
     */
    private Integer size;

    /**
     * 总数
     */
    private Long total;

    /**
     * 总页数
     */
    private Long totalPage;

    /**
     * 数据
     */
    private List<E> list;

    public PageInfo<E> init(Integer page, Integer size, Long total, List<E> list) {
        this.page = page;
        this.size = size;
        this.total = total;
        this.list = list;
        this.totalPage = total%size==0?total/size:total/size+1;
        return this;
    }
}
