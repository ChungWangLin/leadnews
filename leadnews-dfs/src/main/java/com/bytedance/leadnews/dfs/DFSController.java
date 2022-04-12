package com.bytedance.leadnews.dfs;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class DFSController {
    private final DFSService dfsService;

    @PostMapping("/dfs")
    public List<String> uploadFile(MultipartFile[] files) throws IOException {
        if (files.length<1) {
            return null;
        }
        return dfsService.uploadFile(files);
    }
}
