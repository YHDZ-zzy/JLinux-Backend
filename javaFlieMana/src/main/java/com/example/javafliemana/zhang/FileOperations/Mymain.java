package com.example.javafliemana.zhang.FileOperations;

import com.example.javafliemana.zhang.Utils.*;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;
/**
 * 作者: zhang
 * 时间: 2022-02-23 18:51
 **/
public class Mymain {

    public static void main(String[] args) throws IOException {
        myRootDirectory mrd=new myRootDirectory().getMyRootDirectory();
        FileMkdir fileMkdir=new FileMkdir();
        Stack<MyFile> add=new Stack<>();
        add.push(mrd);
        fileMkdir.mkdir(add,"0:0","c","");
        System.out.println(fileMkdir.mkdir(add,"0:0","a",""));
        System.out.println(fileMkdir.rm(add,"0","c","1"));
        System.out.println(fileMkdir.mkdir(add,"0:0","a/b",""));
        System.out.println("ls");
        for (String s:fileMkdir.ls(add).values()){
            System.out.println(s);
        }
    }
    @Test
//    @Parameterized.Parameters( name =s,{0,1,2})
    public static void test(int[] s){
        System.out.println("pop-----"+s[0]);
    }
}
