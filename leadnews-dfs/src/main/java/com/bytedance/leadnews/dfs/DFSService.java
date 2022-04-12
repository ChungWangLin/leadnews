package com.bytedance.leadnews.dfs;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DFSService {
    List<String> uploadFile(MultipartFile[] files) throws IOException;
    void deleteFile(String url);
    void downloadFile(String url);
}
