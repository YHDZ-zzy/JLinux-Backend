package com.example.javafliemana.zhang.FileOperations;
import com.example.javafliemana.zhang.Utils.MyFile;
import com.example.javafliemana.zhang.Utils.myRootDirectory;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 作者: zhang
 * 时间: 2022-02-09 10:35
 * 文件的创建
 **/

public class FileMkdir {
    myRootDirectory mrd;
    public void pwd(){

    }

    /**
     * @param users   用户组
     * @param operate  操作
     * @param supplement 追加命令
     *@param addressFile 命令地址
     * @return 文件改变数
     */
    public int mv(Stack<MyFile> addressFile, String users, String operate, String supplement){

        return 0;
    }
    /**
     * @param users   用户组
     * @param operate  操作
     *@param addressFile 命令地址
     * @param supplement 追加命令
     * @return 文件改变数 -1目标为文件夹 -2目标不存在
     */
    public int rm(Stack<MyFile> addressFile, String users, String operate,String supplement){
        String[] sOperate=operate.split("/");
        MyFile tmpMyFile =new MyFile();
        tmpMyFile=addressFile.peek();
        if (operate.charAt(0)=='/')
            tmpMyFile=mrd;
        for (int i=0;i<sOperate.length;i++){
            if (i==operate.length()-1){
                if (tmpMyFile.getNextFilesToNextFilesName().containsKey(sOperate[i])){
                    MyFile tmpMyFile1=tmpMyFile.getNextFilesToNextFilesName().get(sOperate[i]);
                    if (tmpMyFile1.getPermissions()==1){
                        if (supplement.equals("1")){
                            System.out.println("强制删除");
                            tmpMyFile1.getFile().delete();
                            tmpMyFile.getNextFilesToNextFilesName().remove(sOperate[i]);
                            return 1;
                        }
                        else
                        return -1;
                    }
                    tmpMyFile1.getFile().delete();
                    tmpMyFile.getNextFilesToNextFilesName().entrySet().remove(sOperate[i]);
                    return 1;
                }
            }
            if (tmpMyFile.getNextFilesToNextFilesName().containsKey(sOperate[i])){
                tmpMyFile=tmpMyFile.getNextFilesToNextFilesName().get(sOperate[i]);
            }
            else return -2;
        }
        return 0;
    }
    /**
     *
     * @param addressFile 命令地址
     * @param user  用户组
     * @param operate  操作
     * @param supplement 追加命令
     * @return 文件改变数
     */

    //2022-3-8 此事存在问题，一档存在文件且文件不可读时，此时如果创建文件夹，报错。且无法创建。
    public int mkdir(Stack<MyFile> addressFile,String user,String operate ,String supplement) throws IOException {
        int sumFile=0;
        String[] sOperate =operate.split("/");
        MyFile tmpMyFile=addressFile.peek();
        if(operate.charAt(0)=='/'){
            tmpMyFile=mrd;
        }
        // a/a
        for (int i=0;i<sOperate.length;i++){
            if (i==sOperate.length-1){ //
                MyFile tmpMyFile1=tmpMyFile;
                if (true){
                    if (!tmpMyFile1.getNextFilesToNextFilesName().containsKey(sOperate[i])){//判断不存在指定文件
                        String newFileStr=tmpMyFile1.getFile().toString()+"\\"+sOperate[i];
                        File newFile=new File(newFileStr);
                        newFile.mkdir();
                        MyFile newMyFile=new MyFile(newFile,sOperate[i],user,1,tmpMyFile1,new Date().getTime());
                        tmpMyFile.setNextFilesToNextFilesName(sOperate[i],newMyFile);
                        sumFile++;
                        return sumFile;
                    }
                    else {
                        return sumFile;//文件已存在
                    }
                }
                else {
                    return -1;//无写权限
                }
            }
            if (tmpMyFile.getNextFilesToNextFilesName().containsKey(sOperate[i])){
                tmpMyFile=tmpMyFile.getNextFilesToNextFilesName().get(sOperate[i]);
            }
            else if(supplement.equals('p')){//判断是否存在追加权限
                String newFileStr=tmpMyFile.getFile().toString()+"/"+sOperate[i];
                File newFile=new File(newFileStr);
                newFile.createNewFile();
                MyFile newMyFile=new MyFile(newFile,sOperate[i],user,1,tmpMyFile,new Date().getTime());
                tmpMyFile.setNextFilesToNextFilesName(sOperate[i],newMyFile);
                sumFile++;
                tmpMyFile=tmpMyFile.getNextFilesToNextFilesName().get(sOperate[i]);
            }
            else {
                return -3;//路径不存在，父文件夹不存在
            }
        }
        return 0;
    }
    /**
     *@param addressFile 命令地址
     * -a 显示所有文件及目录 (. 开头的隐藏文件也会列出)
     * -l 除文件名称外，亦将文件型态、权限、拥有者、文件大小等资讯详细列出
     * -r 将文件以相反次序显示(原定依英文字母次序)
     * -t 将文件依建立时间之先后次序列出
     * -A 同 -a ，但不列出 "." (目前目录) 及 ".." (父目录)
     * -F 在列出的文件名称后加一符号；例如可执行档则加 "*", 目录则加 "/"
     * -R 若目录下有文件，则以下之文件亦皆依序列出
     * @return 文件改变数 -1目标为文件夹 -2目标不存在
     */
    public Map<String,String> ls(Stack<MyFile> addressFile){
        Map outMap=new HashMap();
        MyFile myFile=addressFile.peek();
        Set<String> myFileName=myFile.getNextFilesToNextFilesName().keySet();
        for (String s:myFileName){
            MyFile tmpFile=myFile.getNextFilesToNextFilesName().get(s);
            String s1="name--"+tmpFile.getName()+"\tPermissions--"+tmpFile.getPermissions()+"\tPermissions1--"
                    +tmpFile.getPermissions1()+"\nCreationTime---"+tmpFile.getCreationTime()
                    +"\tLastModifiedTime--"+tmpFile.getLastModifiedTime()+"\tsize--"+tmpFile.getNextFilesToNextFilesName().size();
            outMap.put(s,s1);
        }
        return outMap;
    }
    public int permissionJudgment(String user,String fileUser,int filePermission){
        String [] userStr=user.split(":");
        String [] fileUserStr=fileUser.split(":");
        if (fileUserStr[1].equals(userStr[1])){
            return (filePermission %100)/10;
        }else if (fileUserStr[0].equals(userStr[0])){
            return filePermission /100;
        }
        return filePermission %10;
    }
    public MyFile AccessJudgment(MyFile myFile,String nextFileName){
        if ()
        return myFile;
    }
}
