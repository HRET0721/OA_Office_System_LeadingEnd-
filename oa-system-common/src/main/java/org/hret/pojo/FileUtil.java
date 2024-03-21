package org.hret.pojo;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.ObjectMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

/**
 * Author:HRET Milky Way
 * Date:2024/1/18
 * version:1.0
 * EasyPoi工具类
 * @author HRET
 */
public class FileUtil {
    /**
     * 需要的依赖
     * <!--        oss对象存储-->
     * <dependency>
     * <groupId>com.aliyun.oss</groupId>
     * <artifactId>aliyun-sdk-oss</artifactId>
     * <version>3.15.1</version>
     * </dependency>
     * <dependency>
     * <groupId>javax.xml.bind</groupId>
     * <artifactId>jaxb-api</artifactId>
     * <version>2.3.1</version>
     * </dependency>
     * <dependency>
     * <groupId>javax.activation</groupId>
     * <artifactId>activation</artifactId>
     * <version>1.1.1</version>
     * </dependency>
     * <!-- no more than 2.3.3-->
     * <dependency>
     * <groupId>org.glassfish.jaxb</groupId>
     * <artifactId>jaxb-runtime</artifactId>
     * <version>2.3.3</version>
     * </dependency>
     */

    /*
     *
     * 图片上传
     * @param imgFile 上传的图片文件
     * @param
     * @return
     */
    // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
    private static final String ACCESS_KEY_ID = System.getenv("ACCESS_KEY_ID");
    //    阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
    private static final String ACCESS_KEY_SECRET = System.getenv("ACCESS_KEY_SECRET");
    // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
    private static final String endpoint = "hret0721.oss-cn-beijing.aliyuncs.com";
    // 填写Bucket名称
    private static final String bucketName = "oa-system";

    public static String uploadFile(MultipartFile imgFile){
        String uuid = UUID.randomUUID().toString();

        //uuid,编码命名重复
        String oldName = imgFile.getOriginalFilename();//1.jpg
        //截取文件后缀:.jpg
        String suffix = oldName.substring(oldName.lastIndexOf("."));
        //新文件名
        String newFile = uuid + suffix;

        // 填写Object完整路径，例如exampled/exampleObject.txt。Object完整路径中不能包含Bucket名称。
        String objectName = bucketName + "oa-system" + "/" + newFile;


        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(imgFile.getInputStream());
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(getContentType(objectName.substring(objectName.lastIndexOf("."))));
            ossClient.putObject(bucketName, objectName, bufferedInputStream, objectMetadata);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
//        FileUtil.downloadFile(objectName);
        return objectName;
    }

    /**
     * 图片的下载
     * @param fileName
     */
    public static void downloadFile(String fileName) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, ACCESS_KEY_ID, ACCESS_KEY_SECRET);

        try {
            String[] split = fileName.split("/");
            String dir = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "/img/";

            for (int i = 0; i < split.length - 1; i++) {
                dir += split[i] + "/";
            }
            File file = new File(dir);
            if (!file.exists()) {
                file.mkdir();
            }
            System.out.println(dir);
            dir += split[split.length - 1];
            System.out.println(dir);
            // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
            ossClient.getObject(new GetObjectRequest(bucketName, fileName), new File(dir));


        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    //获取题目网络连接
    public static String getImgUrl(String url) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        //过期时间
        long time = new Date().getTime();
        long i = 1000 * 60 * 60 * 24 * 365;
        Date date = new Date(time + i);
        URL url1 = ossClient.generatePresignedUrl(bucketName, url, date);
        return url1.toString();
    }


    //上传图片格式
    public static String getContentType(String FilenameExtension) {
        if (FilenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (FilenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (FilenameExtension.equalsIgnoreCase(".jpeg") ||
                FilenameExtension.equalsIgnoreCase(".jpg") ||
                FilenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpg";
        }
        if (FilenameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if (FilenameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (FilenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (FilenameExtension.equalsIgnoreCase(".pptx") ||
                FilenameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (FilenameExtension.equalsIgnoreCase(".docx") ||
                FilenameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (FilenameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }
        return "image/jpg";
    }
}
