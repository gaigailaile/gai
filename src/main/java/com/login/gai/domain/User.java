package com.login.gai.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by lenovo on 2018/12/18.
 */
@ApiModel("用户模型")
public class User {
    @ApiModelProperty("用户id")
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return id;
    }
}
