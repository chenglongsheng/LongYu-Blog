package com.longyu.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class PathUtil {

    public static String generateFilePath(String fileName) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        String datePath = sdf.format(new Date());
        //uuid作为文件名
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //后缀和文件后缀一致
        int index = fileName.lastIndexOf(".");
        // 截取为文件后缀，如test.jpg->.jpg
        String fileType = fileName.substring(index);
        return datePath + uuid + fileType;
    }

}
