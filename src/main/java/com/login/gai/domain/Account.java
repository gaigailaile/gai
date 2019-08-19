package com.login.gai.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Account extends AccountKey {
    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("用户信息关联ID")
    private Integer usersId;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date modifyTime;

    @ApiModelProperty("是否有效")
    private Integer isvalid;

    @ApiModelProperty("版本号")
    private Integer rversion;

    @ApiModelProperty("预留字段")
    private String reserved1;

    @ApiModelProperty("预留字段")
    private String reserved2;

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1 == null ? null : reserved1.trim();
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2 == null ? null : reserved2.trim();
    }
}