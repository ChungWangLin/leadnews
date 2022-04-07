package channel;

import com.bytedance.leadnews.common.pojo.dto.PageInfo;
import com.bytedance.leadnews.common.pojo.entity.AdChannel;

public interface ChannelApi {
    PageInfo<AdChannel> getApChannelByPage(Integer page, Integer size, String channelName, Byte status);
}
