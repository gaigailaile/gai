package com.login.gai.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by lenovo on 2018/12/18.
 */
@ApiModel("holle模型")
public class HolleWorld {
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("说了啥")
    private String talk;

    public void setName(String name) {
        this.name = name;
    }

    public void setTalk(String talk) {
        this.talk = talk;
    }

    public String getName() {

        return name;
    }

    public String getTalk() {
        return talk;
    }
}
