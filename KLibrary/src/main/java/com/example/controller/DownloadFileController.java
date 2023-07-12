package com.example.controller;


import com.example.domain.DownloadFile;
import com.example.service.DownloadFileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/downloadfiles")
public class DownloadFileController {

    @Autowired
    private DownloadFileService downloadFileService;


    @GetMapping
    @ResponseBody
    public void downloadFile(DownloadFile downloadFile,HttpServletResponse response) {
        downloadFileService.download(downloadFile,response);
    }
}
