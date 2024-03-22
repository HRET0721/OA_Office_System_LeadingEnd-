package org.hret.pojo;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Author:HRET Milky Way
 * Date:2024/3/21
 * version:1.0
 * oss文件上传工具类
 * @author HRET
 */
public class OssFileUtil {
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

    // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
    private static final String ACCESS_KEY_ID = System.getenv("ACCESS_KEY_ID");
    //    阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
    private static final String ACCESS_KEY_SECRET = System.getenv("ACCESS_KEY_SECRET");
    // Endpoint以华北2（北京）为例，其它Region请按实际情况填写。
    private static final String ENDPOINT = "oss-cn-beijing.aliyuncs.com";
    // 填写Bucket名称, 也就是存储空间名称
    private static final String BUCKET_NAME = "hret0721";
    // 填写Object完整路径，例如exampled/exampleObject.txt。Object完整路径中不能包含Bucket名称。
    private static final String OBJECT_NAME = "oa-system";

    /*
     * 图片上传
     * @param imgFile 上传的图片文件
     * @return 返回图片的网络连接
     */
    public static String uploadFile(MultipartFile imgFile){

        String uuid = UUID.randomUUID().toString();

        //uuid,编码命名重复
        String oldName = imgFile.getOriginalFilename();
        //截取文件后缀:.jpg
        String suffix = null;
        if (oldName != null) {
            suffix = oldName.substring(oldName.lastIndexOf("."));
        }
        //新文件名
        String newFile = imgFile.getOriginalFilename() + uuid + suffix;

        // 填写Object完整路径，例如exampled/exampleObject.txt。Object完整路径中不能包含Bucket名称。
        String objectName = OBJECT_NAME + "/" + newFile;


        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        try {
            // 创建PutObjectRequest对象
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, objectName, imgFile.getInputStream());

            // 如果需要上传时设置存储类型和访问权限, 可以参考如下代码。
            // ObjectMetadata metadata = new ObjectMetadata();
            // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
            // metadata.setObjectAcl(CannedAccessControlList.Private);
            // putObjectRequest.setMetadata(metadata);

            // 上传文件
            ossClient.putObject(putObjectRequest);
        } catch (IOException e) {
            e.fillInStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return "https://" + BUCKET_NAME + "." + ENDPOINT + objectName;
    }

    /**
     * 文件下载
     */
    public static void downloadFile(String fileName) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);

        try {
            String[] split = fileName.split("/");
            // 使用默认的类加载器获取路径 getResource("").getPath() 获取当前类的绝对路径 + "/img/"
            StringBuilder dir = new StringBuilder(Objects.requireNonNull(Objects.requireNonNull(ClassUtils.getDefaultClassLoader()).getResource("")).getPath() + "/images/");

            for (int i = 0; i < split.length - 1; i++) {
                dir.append(split[i]).append("/");
            }
            File file = new File(dir.toString());
            if (!file.exists()) {
                file.mkdirs();
            }
            dir.append(split[split.length - 1]);
            // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
            ossClient.getObject(new GetObjectRequest(BUCKET_NAME, fileName), new File(dir.toString()));

        } catch (Exception e) {
            e.fillInStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    public static void  deleteFile(String fileName){
        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);

        // 排除图片的链接 例如：https://hret0721.oss-cn-beijing.aliyuncs.com/  只获取后面的路径 例如：oa-system/图片.jpg
        String filePath = fileName.split("https://" + BUCKET_NAME + ENDPOINT + "/")[1];

        try {
            // 删除文件
            ossClient.deleteObject(BUCKET_NAME, filePath);
        } catch (OSSException e) {
            e.fillInStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    //获取题目网络连接 有效期一年
    public static String getImgUrl(String url) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        //过期时间
        long time = new Date().getTime();
        long i = 1000L * 60 * 60 * 24 * 365;
        Date date = new Date(time + i);
        URL url1 = ossClient.generatePresignedUrl(BUCKET_NAME, url, date);
        return url1.toString();
    }


    //上传图片格式
    public static String getContentType(String filenameExtension) {
        if (".bmp".equalsIgnoreCase(filenameExtension)) {
            return "image/bmp";
        }
        if (".gif".equalsIgnoreCase(filenameExtension)) {
            return "image/gif";
        }
        if (".jpeg".equalsIgnoreCase(filenameExtension) ||
                ".jpg".equalsIgnoreCase(filenameExtension) ||
                ".png".equalsIgnoreCase(filenameExtension)) {
            return "image/jpg";
        }
        if (".html".equalsIgnoreCase(filenameExtension)) {
            return "text/html";
        }
        if (".txt".equalsIgnoreCase(filenameExtension)) {
            return "text/plain";
        }
        if (".vsd".equalsIgnoreCase(filenameExtension)) {
            return "application/vnd.visio";
        }
        if (".pptx".equalsIgnoreCase(filenameExtension) ||
                ".ppt".equalsIgnoreCase(filenameExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if (".docx".equalsIgnoreCase(filenameExtension) ||
                ".doc".equalsIgnoreCase(filenameExtension)) {
            return "application/msword";
        }
        if (".xml".equalsIgnoreCase(filenameExtension)) {
            return "text/xml";
        }
        return "image/jpg";
    }
}
