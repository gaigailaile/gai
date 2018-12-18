package com.login.gai.controller;

import com.login.gai.domain.HolleWorld;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lenovo on 2018/12/18.
 */
@Api(value = "holle接口",tags = "holle",description = "holle接口",basePath = "/holle")
@RestController
@RequestMapping(value = "${api.basePath}/holle")
public class HolleController {
    @ApiOperation("惯例holle World")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", paramType = "query", dataType = "String", required = true, defaultValue = "")
    })
    @CrossOrigin
    @RequestMapping(value = "/getHolleWorld",method = RequestMethod.GET)
    public HolleWorld getHolleWorld(@RequestParam(value = "name", required = false, defaultValue = "") String name){
        String str = "hello world!!!!";
        HolleWorld holleWorld = new HolleWorld();
        if(Strings.isNotBlank(name)){
            holleWorld.setName(name);
        }
        holleWorld.setTalk(str);
        return holleWorld;
    }
}
