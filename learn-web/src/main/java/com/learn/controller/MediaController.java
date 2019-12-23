package com.learn.controller;

import com.learn.request.vo.CommonReturnType;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @author hao.dai
 * @date 2019/12/15
 */
@CrossOrigin(allowCredentials = "true",maxAge = 3600)
@Api(description = "media upload and download", tags = "")
@RestController
@RequestMapping(path = "/media", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MediaController {

    @Value("${media.upfile.path}")
    private String upfilePath;

    @PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public CommonReturnType uploadFile(@RequestBody MultipartFile file, HttpServletRequest req) {
        if (file == null) {
            return CommonReturnType.retFailed("文件不能为空！");
        }
        String fileSub = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."))
                .toLowerCase();
        if (".jpg".equals(fileSub) || ".jpeg".equals(fileSub) || ".png".equals(fileSub) || ".gif".equals(fileSub)) {
            Random d = new Random();
            String img = UUID.randomUUID().toString().replace("-", "") + d.nextInt(10000) + "" + fileSub;
            try {
                // uploadFile.transferTo(new
                // File(req.getServletContext().getRealPath("WEB-INF/upload"),img));
                File f=new File(upfilePath);
                if(!f.exists()){
                    f.mkdirs();
                }
                file.transferTo(new File(f, img));
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<String, String> map = new HashMap<>();
            map.put("src", img);
            return CommonReturnType.retSuccess(map);
        } else {
            return CommonReturnType.retFailed("文件格式不支持,请重新选择！");
        }
    }

    /**
     * 本地图片回显
     *
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = "/showPic")
    @ResponseBody
    public String showPic(String fileName, HttpServletResponse response) {
        // String fileName="4d857472b93b4b9bb04d65f1506b2a329707.jpeg";
        FileInputStream fis = null;
        OutputStream os = null;
        try {
            //图片的本地全路径
            fis = new FileInputStream(upfilePath+"/"+ fileName);
            os = response.getOutputStream();
            int count = 0;
            byte[] buffer = new byte[1024 * 8];
            while ((count = fis.read(buffer)) != -1) {
                os.write(buffer, 0, count);
                os.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            fis.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    /**
     * 处理文件下载
     * @param request
     * @param response
     * @param fileName
     */
    @GetMapping(value = "/downLoad")
    @ResponseBody
    public void downLoadList(HttpServletRequest request, HttpServletResponse response,
                             String fileName){
        downloadFile(new File(upfilePath,fileName), response, false);
    }

    /**
     * 文件下载
     * @param file
     * @param response
     * @param isDelete
     */
    public void downloadFile(File file, HttpServletResponse response, boolean isDelete) {
        try {
            // 以流的形式下载文件。
            BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().getBytes("UTF-8"),"ISO-8859-1"));
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            if(isDelete)
            {
                file.delete();        //是否将生成的服务器端文件删除
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

