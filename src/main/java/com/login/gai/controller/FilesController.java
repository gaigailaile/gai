package com.login.gai.controller;

import com.login.gai.utils.ResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by gaidongxu on 2019/8/8.
 */

@Api(value = "微信文件上传",tags = "Files",description = "微信文件上传接口",basePath = "/Files")
@RestController
@RequestMapping(value = "/Files")
public class FilesController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ApiOperation("微信文件上传,支持多文件")
    @PostMapping(value = "/uploadFile",headers = "content-type=multipart/form-data")
    @ResponseBody
    @CrossOrigin
    public ResponseModel uploadFile(@RequestParam(required = true,value = "files") MultipartFile[] files){
        ResponseModel responseModel = new ResponseModel();
        String fileName = null;
        File file = null;
        int sussceCount = 0;
        try{
            if(files.length == 0){
                responseModel.setStatus(false);
                responseModel.setMessage("上传失败");
                return responseModel;
            }
            for (int i = 0;i<files.length;i++){
                MultipartFile multipartFile = files[i];
                fileName = multipartFile.getOriginalFilename();
                file = new File("e:/图片/"+fileName);
                multipartFile.transferTo(file);
                sussceCount++;
                logger.error(fileName + "上传成功");
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(fileName + "上传失败-----" + e.getMessage());
            responseModel.setStatus(false);
            responseModel.setMessage(fileName + "上传失败");
        }finally {
            if(sussceCount == files.length){
                responseModel.setStatus(true);
                responseModel.setMessage("上传成功");
            }else if(sussceCount < files.length && sussceCount != 0){
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
