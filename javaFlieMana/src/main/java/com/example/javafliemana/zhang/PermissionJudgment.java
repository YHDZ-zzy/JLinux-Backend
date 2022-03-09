package com.example.javafliemana.zhang;

/**
 * 作者: zhang
 * 时间: 2022-03-09 20:08
 **/

public class PermissionJudgment {
    protected int permissionJudgment(String user,String fileUser,int filePermission){
        String [] userStr=user.split(":");
        String [] fileUserStr=fileUser.split(":");
        if (fileUserStr[1].equals(userStr[1])){
            return (filePermission %100)/10;
        }else if (fileUserStr[0].equals(userStr[0])){
            return filePermission /100;
        }
        return filePermission %10;
    }
}
