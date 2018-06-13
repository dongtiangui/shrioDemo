package com.Service;

import com.EntiryPackage.userEntiry;

public interface LoginUser {

    userEntiry getname(String name);

    boolean getInsert(userEntiry userEntiry);

    boolean inuset(userEntiry entiry);
}
