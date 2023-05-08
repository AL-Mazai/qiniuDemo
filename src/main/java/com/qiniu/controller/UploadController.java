package com.qiniu.controller;

import com.qiniu.service.UploadImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;

@Slf4j
@Controller
public class UploadController {

    @Resource
    UploadImageService uploadImageService;

    @GetMapping("/upload")
    public String upload(){
        return "upload";
    }


    @PostMapping(value = "/image")
    private String upLoadImage(@RequestParam("file") MultipartFile file, Model model) {

        if (!file.isEmpty()) {
            String path = uploadImageService.uploadQNImg(file);
            System.out.print("七牛云返回的图片链接:" + path);
            model.addAttribute("link",path);
            return "upload";
        }
        return "";
    }

    /**
     * 这里为了方便测试才这么写的 可以根据实际需要 自己写。
     * @Param
     * @return java.lang.String
     */
    @ResponseBody
    @DeleteMapping("/remove")
    public String removeFile(@RequestParam("fileKey") String fileKey){
//        System.out.println("remove........");
        String bucketName = "caicai-test";//空间名
        uploadImageService.removeFile(bucketName,fileKey);
        return "删除成功";
    }

}
