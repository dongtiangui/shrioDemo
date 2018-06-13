package com.Mapper;

import com.EntiryPackage.userEntiry;

public interface LoginInterface {

    boolean insert(userEntiry userEntiry);

    userEntiry getName(String name);

}
