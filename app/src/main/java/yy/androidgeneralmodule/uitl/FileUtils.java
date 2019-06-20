package yy.androidgeneralmodule.uitl;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import yy.androidgeneralmodule.uitl.log.LogUtil;

/**
 * Created by Administrator on 2019/3/18 0018.
 */

public class FileUtils {
    private static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 遍历文件夹下的文件
     *
     * @param file 地址
     */
    public static List<File> getFile(File file) {
        List<File> list = new ArrayList<>();
        File[] fileArray = file.listFiles();
        if (fileArray == null) {
            return null;
        } else {
            for (File f : fileArray) {
                if (f.isFile()) {
                    list.add(0, f);
                } else {
                    getFile(f);
                }
            }
        }
        return list;
    }

    /**
     * 删除文件
     *
     * @param
     * @return
     */
    public static boolean deleteFiles(File file) {
        file.delete();
      /*  List<File> files = getFile(new File(filePath));
        if (files.size() != 0) {
            for (int i = 0; i < files.size(); i++) {
                File file = files.get(i);

                *//**  如果是文件则删除  如果都删除可不必判断  *//*
//                if (file.isFile()) {
                    file.delete();
//                }

            }
        }*/
        return true;
    }

    /**
     * 重命名文件
     *
     * @param oldPath 原来的文件地址
     * @param newPath 新的文件地址
     */
    public static void renameFile(String oldPath, String newPath) {
        File oleFile = new File(oldPath);
        File newFile = new File(newPath);
        //执行重命名
        oleFile.renameTo(newFile);
    }

    static StringBuffer stringBuffer = new StringBuffer();

    public static void writeFileAdd(String datas) {
        stringBuffer.delete(0, stringBuffer.length());
        stringBuffer.append(formatter.format(new Date()) + "-----");
        stringBuffer.append(datas);
        LogUtil.writeFile("boxContentLog.txt", stringBuffer.toString() + "\r\n");
    }

    /**
     * 删除超出限制的文件，保留50条日志信息
     *
     * @param path
     */
    public static void deleteOverFile(String path) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                return;
            }
            List<File> fileList = FileUtils.getFile(file);
            if (fileList.size() > 50) {
                File[] files = new File[fileList.size()];
                fileList.toArray(files);
                File temp;
                for (int i = 0; i < files.length - 1; i++) {
                    for (int j = 0; j < files.length - 1 - i; j++) {
                        if (files[j].lastModified() > files[j + 1].lastModified()) {
                            temp = files[j];
                            files[j] = files[j + 1];
                            files[j + 1] = temp;
                        }
                    }
                }
                int overCount = files.length - 40;
                for (int i = 0; i < overCount; i++) {
                    if (i < files.length) {
                        files[i].delete();
                    }
                }
            }
        } catch (Exception e) {
            LogUtil.e("deleteOverFile==" + e);
        }
    }

}
