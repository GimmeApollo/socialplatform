package com.hdz.socialplatform.utils;

import com.hdz.socialplatform.controller.PostController;
import com.hdz.socialplatform.entity.PostVO;
import com.hdz.socialplatform.entity.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author hdz
 * @description 动态的图片保存
 * @create 2022年03月23日 19:14
 */
public class LoadUtil {

    private static final Logger logger = LogManager.getLogger(LoadUtil.class);

    //TODO:文件保存了怎么获取呢
    private static String POST_PATH = "E:\\AppData\\socialplatform\\pictures\\";

    public static PostVO uploadFiles(HttpServletRequest request) throws FileUploadException, IOException {

        // 创建 DiskFileItemFactory对象
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 创建ServletFileUpload对象
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 所有获取 FileItem的List数组
        List<FileItem> items = upload.parseRequest(request);

        //拿到userid
        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getId();
        DateFormat df = new SimpleDateFormat("yy-MM-dd-HHmmss-SSS");
        Date date = new Date();
        String postName = df.format(date);
        String path = userId + "\\" + postName + "\\";
        String absolutePath = POST_PATH + path;
        File dir = new File(absolutePath);
        if (dir.mkdirs()) {
            logger.info("创建文件夹" + path + "成功");
        } else {
            logger.warn("创建文件夹" + path + "失败");
        }
        PostVO postVO = new PostVO();
        postVO.setPath(path);
        postVO.setPostTime(date);
        postVO.setUserId(userId);
        //测试
//        File directory = new File("");
//        System.out.println(directory.getCanonicalPath());
//        System.out.println(directory.getAbsolutePath());
//        directory = new File(".\\src\\main\\resources\\static");
//        String[] children = directory.list();
//        if (children == null) {
//            System.out.println( "目录不存在或它不是一个目录");
//        }
//        else {
//            for (int i=0; i< children.length; i++) {
//                String filename = children[i];
//                System.out.println(filename);
//            }
//        }



        logger.info("********处理发送动态请求***************");

        for (FileItem fi : items) {
            if (fi.isFormField()) {     //TODO：普通文本表单元素,之后可以用反射机制调用

                String name = fi.getFieldName();
                String value = fi.getString("utf-8");
                switch (name) {
                    case "ifReprint":
                        postVO.setIfReprint(Integer.valueOf(value));
                        break;
                    case "onlyISee":
                        postVO.setOnlyISee(Integer.valueOf(value));
                        break;
                    case "content":
                        postVO.setContent(value);
                        break;
                    case "reprintId":
                        postVO.setReprintId(Integer.valueOf(value));
                        break;
                    default:
                        logger.warn("没处理" + name + " = " + value);
                        break;
                }
                logger.info("处理文字：name=" + name + " value=" + value);
            } else {
                // 文件上传表单元素
                // 获取上传的文件名
                String fileName = fi.getName();
                if (fileName.length() == 0) {
                    continue;
                }
                logger.info("处理图片，fileName=" + fileName);
                // 获取数据输入流，用于获取从web端传输的数据
                InputStream is = fi.getInputStream();
                // 创建文件输出流
                FileOutputStream fos = new FileOutputStream(new File(absolutePath + fileName));
                int len = -1;
                byte[] b = new byte[1024 * 1024];
                while (-1 != (len = is.read(b))) {

                    fos.write(b, 0, len);
                }
                fos.flush();
                fos.close();
                is.close();
            }
        }
        return postVO;
    }

    public static void setFileName(PostVO postVO) {
        String path = POST_PATH + postVO.getPath();
        ArrayList<String> list = new ArrayList<>();
        postVO.setFileNames(list);
        File dir = new File(path);
        String[] children = dir.list();
        if (children == null) {
            logger.error("目录不存在或它不是一个目录");
        } else {
            for (int i = 0; i < children.length; i++) {
                String filename = children[i];
                list.add(filename);
            }
        }

    }

    public static void setFileName(List<PostVO> list) {
        for (int i = 0; i < list.size(); i++) {
            setFileName(list.get(i));
        }
    }
}
