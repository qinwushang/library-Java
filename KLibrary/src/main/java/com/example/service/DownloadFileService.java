package com.example.service;

import com.example.domain.DownloadFile;
import jakarta.servlet.http.HttpServletResponse;

public interface DownloadFileService {
    String download(DownloadFile downloadFile,HttpServletResponse response);
}
