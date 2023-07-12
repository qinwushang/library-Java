package com.example.service.impl;

import com.example.domain.DownloadFile;
import com.example.service.DownloadFileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
public class DownloadFileServiceImpl implements DownloadFileService {

    @Override
    public String download(DownloadFile downloadFile, HttpServletResponse response){
        String realPath = downloadFile.getBookUrl();
        String fileName = downloadFile.getName()+ "."+realPath.substring(realPath.lastIndexOf(".") + 1).toLowerCase();
        //设置文件路径
        File file = new File(realPath);
        if (file.exists()) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));//设置文件名
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                System.out.println(fileName + " download success");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }
}
