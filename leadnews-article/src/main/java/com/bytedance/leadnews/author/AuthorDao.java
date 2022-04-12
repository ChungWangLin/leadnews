package com.bytedance.leadnews.author;

import com.bytedance.leadnews.common.pojo.entity.ApAuthor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthorDao {

    /**
     * 添加作者
     */
    void save(@Param("authors") List<ApAuthor> authors);
}
