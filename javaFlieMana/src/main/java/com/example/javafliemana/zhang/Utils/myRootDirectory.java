package com.example.javafliemana.zhang.Utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * 作者: zhang
 * 时间: 2022-02-23 18:56
 * 个目录，单例，饿汉式
 **/

public class myRootDirectory extends MyFile {

    public myRootDirectory() throws IOException {
    }
    public myRootDirectory getMyRootDirectory(){
        File myroot=new File("F://shiXun//myRootTree");
        myroot.delete();
        file=myroot;
        if (!myroot.exists()){
            if (!new File("F://shiXun").exists())
                new File("F://shiXun").mkdir();
            myroot.mkdir();
            this.name="my_root_directory";
            System.out.println(myroot.toString());
            this.file=myroot;
            this.Permissions=-1;
            this.users="0:0";
            this.creationTime= new Date().getTime();
        }
        if (!myroot.isDirectory()){
            myroot.delete();
            myroot.mkdir();
            this.name="my_root_directory";
            this.file=myroot;
            this.Permissions=-1;
            this.users="0:0";
            this.creationTime= new Date().getTime();
        }
        return this;
    }
}


