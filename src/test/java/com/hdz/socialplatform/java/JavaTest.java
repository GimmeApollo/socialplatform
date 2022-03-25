package com.hdz.socialplatform.java;

import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * @author hdz
 * @description TODO
 * @create 2022年03月23日 19:04
 */
public class JavaTest {

    @Test
    public void test1(){
        String destDirName = "E:\\Project\\IdeaProjects\\socialplatform\\src\\main\\resources\\static\\pictures\\2\\3\\4\\";
        File dir = new File(destDirName);
        if (dir.exists()) {
            System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");
            System.out.println(File.separator);
            return;
        }
        //创建目录
        if (dir.mkdirs()) {
            System.out.println("创建目录" + destDirName + "成功！");
        } else {
            System.out.println("创建目录" + destDirName + "失败！");
        }
    }

    @Test
    public void test2(){
        File dir = new File("E:\\AppData\\socialplatform\\1\\22-03-24-235827-455\\");
        String[] children = dir.list();
        if (children == null) {
            System.out.println( "目录不存在或它不是一个目录");
        }
        else {
            for (int i=0; i< children.length; i++) {
                String filename = children[i];
                System.out.println(filename);
            }
        }
    }

    @Test
    public void test3(){
        String path="E:\\AppData\\socialplatform\\1\\22-03-24-235827-455\\";


    }




}
