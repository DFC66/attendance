package com.dfc.util;

import sun.misc.BASE64Decoder;

import java.io.*;

public class ImageUtil {

    public static boolean GenerateImage(String imgData, String imgFilePath) throws IOException { // 对字节数组字符串进行Base64解码并生成图片
        // 图像数据为空
        if (imgData == null){
            return false;
        }

        BASE64Decoder decoder = new BASE64Decoder();
        OutputStream out = null;
        try {
            out = new FileOutputStream(imgFilePath);
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgData);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            out.write(b);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return  false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return  false;
        } finally {
            out.flush();
            out.close();
            return true;
        }
    }
    public static void makeSureFolderExists(String folderPath) {
//        String folderPath = filePath.substring(0, filePath.lastIndexOf("/"));
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }


}
