package com.dfc.api.impl;


import com.dfc.WebConfig;
import com.dfc.api.StorageApi;
import com.dfc.api.entity.UploadResult;
import com.dfc.util.BaseCommonUtil;
import com.dfc.util.BizException;
import com.dfc.util.ImageUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.dfc.constant.DataConstant.PATH_SPLIT;


/**
 * @author aiyou
 */
@Service
public class StorageApiImpl implements StorageApi, ApplicationContextAware {

    @Value("${file-service.profile}")
    private String homePath;

    @Value("${file-service.netAddress}")
    private String netFilesPath;




//    @Autowired
//    private DocumentInfoBO documentInfoBO;

    private static Logger log = LoggerFactory.getLogger(StorageApiImpl.class);

    private static final String PATH = "/";

    private static String allFilesExt = "jpg|jpeg|png|bmp|msg|pdf|doc|docx|rar|zip|xls|xlsx|txt|mp3|wav|cda|ttf|tif|mp4|flv|mpeg|rmvb|avi|mov|tif";

    private static List<String> allowTypes = Arrays.asList(allFilesExt.split("\\|"));

    /**
     * 文件上传接口
     *
     * @param request 请求
     * @param folder  类型
     * @return 上传的返回类型
     * @throws Exception 抛异常
     */
    @Override
    public UploadResult uploadByServletRequest(HttpServletRequest request, String folder) throws Exception {
        String encoding = "utf-8";
        String path = this.homePath;
        request.setCharacterEncoding(encoding);

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
        servletFileUpload.setSizeMax(2*1024 * 1024 * 1024L);
        List<FileItem> fileItems = servletFileUpload.parseRequest(request);

        // 上传后的路径
        String uploadObjectName = null;

        // 依次处理每个上传的文件
        for (final FileItem item : fileItems) {
            if (!item.isFormField()) {
                String fileFullName = item.getName();
                int tmpIndex = fileFullName.lastIndexOf(".");
                if (tmpIndex == -1) {
                    throw new BizException("上传类型不支持");
                }

                String ext = fileFullName.substring(tmpIndex + 1).toLowerCase();
                if (!allowTypes.contains(ext)) {
                    throw new BizException("上传类型不支持:" + ext);
                }

                InputStream is = item.getInputStream();
                long fileSize = is.available();

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int len = -1;
                byte[] buf = new byte[50240];
                while ((len = is.read(buf)) != -1) {
                    bos.write(buf, 0, len);
                }
                bos.flush();
                //名称随机
                uploadObjectName = folder + PATH + BaseCommonUtil.uuid() + "." + ext;
                //保存到本地
                String filePath = upload(uploadObjectName, bos.toByteArray());
//                //保存到数据库中
//                DocumentInfoDTO documentInfoDTO = new DocumentInfoDTO();
//                documentInfoDTO.setTitle(fileFullName);
//                documentInfoDTO.setFilePath(filePath);
//                documentInfoDTO.setFileSize((int) fileSize);
//                documentInfoDTO.setDocumentType(folder);
//                LoginUser loginUser = ThreadUser.get();
//                if (loginUser != null) {
//                    documentInfoDTO.setUserId(loginUser.getUserId());
//                }
//
//                documentInfoBO.insert(documentInfoDTO);

                bos.close();
                //写返回值
                UploadResult result = new UploadResult();
                result.setFileExt(ext);
                result.setFileName(fileFullName);
                result.setFilePath(filePath);
                result.setFileSize(fileSize);
                result.setUploadTime(new Date());


                return result;
            }
        }
        return null;
    }

    @Override
    public void list() {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(String objectName) {
        String filePath = homePath + PATH + objectName;
        File f = new File(filePath);

        if (f.exists()) {
            f.delete();
        }
    }

    @Override
    public String upload(String objectName, byte[] data) {
        String filePath = getFilePath(objectName);
        FileOutputStream fos = null;
        try {
            makeSureFolderExists(filePath);

            fos = new FileOutputStream(filePath);
            fos.write(data);
            fos.flush();
            return filePath;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BizException(e);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception ignored) {}
            }

        }
    }

    @Override
    public void upload(String objectName, InputStream is) {
        String filePath = getFilePath(objectName);

        FileOutputStream fos = null;
        try {
            makeSureFolderExists(filePath);

            fos = new FileOutputStream(filePath);

            byte[] buff = new byte[4096];
            int len = 0;
            while ((len = is.read(buff)) != -1) {
                fos.write(buff, 0, len);
            }
            fos.flush();

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BizException(e);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception ignored) {}
            }
        }

    }

    private String getFilePath(String objectName) {
        if (objectName.startsWith(PATH)) {
            throw new BizException("objectName 不能以 / 开头");
        }
        return homePath + PATH + objectName;
    }

    @Override
    public byte[] download(String objectName) {
        String filePath = getFilePath(objectName);

        FileInputStream fis = null;
        byte[] bufs = null;
        try {

            fis = new FileInputStream(filePath);

            bufs = new byte[fis.available()];

            fis.read(bufs, 0, bufs.length);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BizException(e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e2) {}
            }
        }
        return bufs;
    }

    @Override
    public void downloadOutputStream(OutputStream os, String objectName) {
        String filePath = getFilePath(objectName);

        FileInputStream is = null;
        try {

            is = new FileInputStream(filePath);

            byte[] bufs = new byte[4096];
            int len = 0;
            while ((len = is.read(bufs)) != -1) {
                os.write(bufs, 0, len);
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BizException(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception ignored) {}
            }
        }

    }

    @Override
    public Map<String, String> generateUploadSession() {
        return null;
    }

    @Override
    public String uploadFaceImage(Integer courseCode,String stuNumber, String folder, String base64) throws IOException {

        String dictoryPath = WebConfig.getFilePath(courseCode,folder);
        makeSureFolderExists2(dictoryPath);
        String fileName ="学号__"+stuNumber+BaseCommonUtil.uuid() + ".png";
        String imgFilePath = dictoryPath + PATH_SPLIT + fileName;

        boolean b = ImageUtil.GenerateImage(base64, imgFilePath);
        if (b){
            String modulePath = WebConfig.getModulePath(courseCode, folder);
           String faceNetUrl = this.netFilesPath + PATH_SPLIT+modulePath+ PATH_SPLIT+fileName;
           return  faceNetUrl;
        }else{
            return null;
        }

    }

    @Override
    public String uploadCourseImage(Integer courseCode, String objectName, MultipartFile multipartFile) throws IOException {
        String netCoursePath = null;

        if (multipartFile != null && !multipartFile.isEmpty()) {
            String dictoryPath = WebConfig.getFilePath(courseCode, objectName);
            makeSureFolderExists2(dictoryPath);
            String filename = multipartFile.getOriginalFilename() + ".png";
            try {
                File trueFile = new File(dictoryPath,filename);
                multipartFile.transferTo(trueFile);
                netCoursePath  = this.netFilesPath +PATH_SPLIT+ WebConfig.getModulePath(courseCode,objectName)+PATH_SPLIT+filename;

            } catch (IOException e) {
                e.printStackTrace();
                return null;

            }
        }else
        {
            return  null;
        }


        return netCoursePath;
    }

    private static void makeSureFolderExists(String filePath) {
        String folderPath = filePath.substring(0, filePath.lastIndexOf("/"));
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    public static void makeSureFolderExists2(String folderPath) {
//        String folderPath = filePath.substring(0, filePath.lastIndexOf("/"));
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }



    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("[kyubi storage] main path: " + homePath);
    }
}
