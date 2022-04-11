package com.bytedance.leadnews.common.pojo.entity;

import com.bytedance.leadnews.common.pojo.param.admin.ChannelParam;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 频道
 */
@Data
public class AdChannel implements Serializable {
    private static final long serialVersionUID = 57887078897108313L;
    private Integer id;
    private String name;
    private String description;
    private Byte isDefault=1;
    private Byte status;
    private Byte ord;
    private LocalDateTime createdTime;

    public AdChannel convertFromCreateParam(ChannelParam.Create param) {
        BeanUtils.copyProperties(param,this);
        return this;
    }

    public AdChannel coverFromUpdateParam(ChannelParam.Update param) {
        BeanUtils.copyProperties(param,this);
        return this;
    }
}
