package com.ciner.dongbao.ums.entity;



import lombok.Data;

import java.util.Date;

@Data
public class UserMeberLoginReponse {

    private String Token;
    private UmsMember umsMember;
}
