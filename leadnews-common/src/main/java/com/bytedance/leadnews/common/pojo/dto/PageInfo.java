package com.bytedance.leadnews.common.pojo.dto;

import com.bytedance.leadnews.common.exception.ParamRequestException;
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

    /**
     * 校验传来的分页参数是否合法
     * @param page 当前页
     * @param size 每页大小
     */
    public static void checkedPage(Integer page, Integer size) {
        if (page==null || page<1 || size==null || size<1) {
            throw new ParamRequestException("参数不合法");
        }
    }

    /**
     * 计算分页起始位置
     * @param page 当前页
     * @param size 每页大小
     */
    public static Long limit(Integer page, Integer size) {
        return Long.parseLong(String.valueOf((page-1)*size));
    }
}
