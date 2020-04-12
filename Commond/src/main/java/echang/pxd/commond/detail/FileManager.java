package echang.pxd.commond.detail;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;

/**
 * @Description
 * @Author 彭孝东
 * @QQ 932056657
 */
public class FileManager {
    private static FileManager manager;

    private FileManager(){}

    //单例方法
    public static FileManager getInstance(){
        if (manager == null){
            synchronized (FileManager.class){
                if (manager == null){
                    manager = new FileManager();
                }
            }
        }
        return manager;
    }

    /**
     * 创建目录
     * @param path
     * @return
     */
    public boolean mkdir(String path){
        File file = new File(path);
        if (file.exists()){
            return false;
        }
        //deskTop/file/image/1.jpg
        //mkdir要求路径中的目录都存在
        //mkdirs如果路劲中的目录不存在 也会自动创建
        return file.mkdir();
    }

    /**
     * 删除文件/目录
     * @param path
     * @return
     */
    public boolean remove(String path){
        File file = new File(path);
        if (!file.exists()){
            return false;
        }

        return file.delete();
    }

    /**
     * 列出文件信息
     * @param path
     * @return
     */
    public File[] list(String path){
        File file = new File(path);
        if (!file.exists()){
            return null;
        }

        return file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File file, String s) {
                if (s.startsWith(".")){
                    return false;
                }
                return true;
            }
        });
    }

    /**
     * 文件或者目录的copy
     * @param src
     * @param des
     * @return
     */
    public boolean copy(String src,String des){
        File srcFile = new File(src);
        File desFile = new File(des);

        //判断文件是否存在
        if (!srcFile.exists() || desFile.exists()){
            return false;
        }

        //判断是不是文件
        if (srcFile.isFile()){
            //直接copy文件
            copyFile(src, des);
        }else{
            //是目录
            //创建当前的目录
            desFile.mkdir();

            //需要将源目录的所有内容copy到des目录
            for (File file: srcFile.listFiles()){
                copy(file.getAbsolutePath(),des+"/"+file.getName());
            }
        }
        return true;
    }

    private void copyFile(String src, String des){
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            //创建输入流
            fis = new FileInputStream(src);
            bis = new BufferedInputStream(fis);

            //创建输出流
            fos = new FileOutputStream(des);
            bos = new BufferedOutputStream(fos);

            //创建buffer
            byte[] buffer = new  byte[1024];
            int len = 0;
            while ((len = bis.read(buffer)) != -1){
                bos.write(buffer);
            }
            bos.flush();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                bis.close();
                fis.close();
                bos.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}












