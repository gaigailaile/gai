package com.login.gai.domain;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {
    private Integer id;

    private String name;

    private Integer sex;

    private String idCode;

    private Integer usersId;

    private Date createTime;

    private Date modifyTime;

    private Integer isvalid;

    private Integer rversion;

    private String reserved1;

    private String reserved2;

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1 == null ? null : reserved1.trim();
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2 == null ? null : reserved2.trim();
    }
}