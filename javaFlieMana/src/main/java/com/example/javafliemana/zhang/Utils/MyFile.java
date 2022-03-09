package com.example.javafliemana.zhang.Utils;

import java.io.File;
import java.sql.Time;
import java.util.*;

/**
 * 作者: zhang
 * 时间: 2022-02-09 10:37
 **/

public class MyFile {
    protected File file;
    protected String name;//文件名
    protected String users;//格式为int：int 例如0:0的字符串
    protected int Permissions;//文件属性 -1根目录 1文件夹 2文件
    protected int Permissions1;//文件权限

    protected MyFile topFile;//父文件
    protected Long creationTime; //创建时间
    protected Long LastModifiedTime;//最后修改时间
    protected Map<String,MyFile> nextFilesToNextFilesName=new HashMap<>();//下级文件名与其对应方便查询
    public MyFile(){}
    public MyFile(File file,String name,String users,int Permissions, MyFile topFile,Long creationTime){
        this.file=file;
        this.name=name;
        this.users=users;
        this.Permissions=Permissions;
        this.topFile=topFile;
        this.creationTime=creationTime;
        this.LastModifiedTime =creationTime;
    }
    public String getName() {
        return this.name;
    }
    public File getFile(){return  this.file;}
//    public int getPermissions() {
//        return this.Permissions;
//    }
    public MyFile getTopFile() {
        return this.topFile;
    }
//    public Set<MyFile> getNextFiles() {
//        return this.nextFiles;
//    }
//    public Set<String> getNextFilesNmae() {
//        return this.nextFilesName;
//    }
    public int getPermissions(){
    return this.Permissions;
    }
    public int getPermissions1() {
        return this.Permissions1;
    }
    public MyFile getMyFile(){
        return this;
    }
    public Map<String,MyFile> getNextFilesToNextFilesName(){
        return this.nextFilesToNextFilesName;
    }
    public long getCreationTime() {
        return this.creationTime;
    }
    public long getLastModifiedTime() {
        return this.LastModifiedTime;
    }
    public void setLastModifiedTime(long LastModifiedTime) {
        this.LastModifiedTime=LastModifiedTime;
    }
    public void setCreationTime(long creationTime) {
        this.creationTime=creationTime;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setFile(File file){
        this.file=file;
    }
    public void setPermissions(int Permissions){
        this.Permissions=Permissions;
    }
    public void setTopFile(MyFile topFile){
        this.topFile=topFile;
    }
    public void setNextFilesToNextFilesName(String NextFilesName ,MyFile NextFiles){
        this.nextFilesToNextFilesName.put(NextFilesName,NextFiles);
    }


}

