package com.login.gai.controller;

import com.login.gai.utils.ResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by gaidongxu on 2019/8/8.
 */

@Api(value = "微信文件上传", tags = "Files", description = "微信文件上传接口", basePath = "/Files")
@Slf4j
@RestController
@RequestMapping(value = "/files")
public class FilesController {

    @ApiOperation("微信文件上传,支持多文件")
    @PostMapping(value = "/uploadFile", headers = "content-type=multipart/form-data")
    @ResponseBody
    @CrossOrigin
    public ResponseModel uploadFile(@RequestParam(required = true, value = "files") MultipartFile[] files) {
        ResponseModel responseModel = new ResponseModel();
        String fileName = null;
        File file = null;
        int sussceCount = 0;
        try {
            if (files.length == 0) {
                responseModel.setStatus(false);
                responseModel.setMessage("上传失败");
                return responseModel;
            }
            for (int i = 0; i < files.length; i++) {
                MultipartFile multipartFile = files[i];
                fileName = multipartFile.getOriginalFilename();
                file = new File("e:/图片/" + fileName);
                multipartFile.transferTo(file);
                sussceCount++;
                log.error(fileName + "上传成功");
            }
        } catch (IOException e) {
            log.error("{}上传失败-----", fileName, e);
            responseModel.setStatus(false);
            responseModel.setMessage(fileName + "上传失败");
        } finally {
            if (sussceCount == files.length) {
                responseModel.setStatus(true);
                responseModel.setMessage("上传成功");
            } else if (sussceCount < files.length && sussceCount != 0) {
                responseModel.setStatus(true);
                responseModel.setMessage("部分上传成功");
            }
            fileName = null;
            file = null;
            files = null;
        }
        return responseModel;
    }
}
