package echang.pxd.commond.detail;

import java.io.File;

/**
 * @Description 接收用户的指令 和 处理指令
 * @Author 彭孝东
 * @QQ 932056657
 */
public class CommondTool implements ICommond{
    //默认请动起来操作的目录
    private static final String DESKTOP_PATH = "/Users/pengxiaodong/Desktop";
    //记录当前操作的目录路径
    private StringBuilder currentDirPath;

    public CommondTool() {
        currentDirPath = new StringBuilder(DESKTOP_PATH);
    }

    //启动命令行工具
    public void start(){
        //创建读取指令的类的对象
        CommondOperation operation = new CommondOperation();
        //欢迎界面
        System.out.println("欢迎使用上海鹅厂教育科技有限公司定制版命令行工具！");

        while(true) {
            //显示目录提示
            showParent();
            //输入指令
            try {
                operation.readCommond(this);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void showParent(){
        //获取最后一个/的index
        int start = currentDirPath.lastIndexOf("/");
        //获取最后的内容
        String parent = currentDirPath.substring(start);
        //输出提示内容
        //parent#
        System.out.print(parent+"#");
    }

    @Override
    public boolean list() {
        File[] files = FileManager.getInstance().list(currentDirPath.toString());
        for (File file :files) {
            //获取文件名
            String name = file.getName();
            //获取文件长度 字节  23.3kb  1KB = 1024byte
            long size = file.length();
            long kb = size / 1024;
            long by = size % 1024;

            System.out.print(name);
            for (int i = 0; i < 40 - name.length(); i++){
                System.out.print(" ");
            }

            System.out.println(kb+"."+by+"kb");

        }
        return false;
    }

    @Override
    public boolean mkdir(String path) {
        //
        //拼接完整路径
        String dirPath = currentDirPath.toString() + "/" + path;
        FileManager.getInstance().mkdir(dirPath);
        return false;
    }

    @Override
    public boolean copy(String src, String des) {
        //拼接完整路径
        String srcPath = currentDirPath.toString() + "/" + src;
        String desPath = currentDirPath.toString() + "/" + des;
        FileManager.getInstance().copy(srcPath,desPath);
        return false;
    }

    @Override
    public boolean remove(String path) {
        //拼接完整路径
        String dirPath = currentDirPath.toString() + "/" + path;
        FileManager.getInstance().remove(dirPath);
        return false;
    }

    @Override
    public boolean cd_to_child(String path) {
        currentDirPath.append("/"+path);
        return false;
    }

    @Override
    public boolean cd_to_parent() {
        int start = currentDirPath.toString().lastIndexOf("/");
        int end = currentDirPath.length();
        currentDirPath.delete(start,end);
        return false;
    }
}










