package com.bytedance.leadnews.service;

import com.bytedance.leadnews.dfs.DFSService;
import com.github.tobato.fastdfs.domain.conn.FdfsWebServer;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FastDFSServiceImpl implements DFSService {
    private final FastFileStorageClient storageClient;
    private final FdfsWebServer fdfsWebServer;

    @Override
    public List<String> uploadFile(MultipartFile[] files) throws IOException {
        List<String> urls = new ArrayList<>();
        for (MultipartFile multipartFile : files) {
            InputStream fileInputStream = multipartFile.getInputStream();
            String extendName = getFileExtendName(multipartFile);
            StorePath storePath = storageClient.uploadFile(fileInputStream, multipartFile.getSize(), extendName, null);
            String url = fdfsWebServer.getWebServerUrl() + storePath.getFullPath();
            urls.add(url);
        }
        return urls;
    }

    @Override
    public void deleteFile(String url) {
        String path = url.replace(fdfsWebServer.getWebServerUrl(),"");
        storageClient.deleteFile(path);
    }

    @Override
    public void downloadFile(String url) {

    }

    private String getFileExtendName(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        return fileName.substring(fileName.lastIndexOf(".")+1);
    }
}
