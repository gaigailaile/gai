package com.login.gai.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Users {
    private Integer id;

    private String mailbox;

    private String username;

    private String personalNote;

    private String signature;

    private Date createTime;

    private Date modifyTime;

    private Integer isvalid;

    private Integer rversion;

    private String reserved1;

    private String reserved2;

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox == null ? null : mailbox.trim();
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public void setPersonalNote(String personalNote) {
        this.personalNote = personalNote == null ? null : personalNote.trim();
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1 == null ? null : reserved1.trim();
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2 == null ? null : reserved2.trim();
    }
}