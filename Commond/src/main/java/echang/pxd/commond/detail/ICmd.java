package echang.pxd.commond.detail;

/**
 * @Description 定义指令
 * @Author 彭孝东
 * @QQ 932056657
 */
public interface ICmd {
    String LS  = "ls";             //列出当前目录内容
    String MKDIR = "mkdir";        //创建目录
    String COPY = "copy";          //拷贝
    String RM = "rm";              //删除
    String CD = "cd";              //进入一个目录
    String CDP = "cd..";           //进入上一层目录

    String[] COMMONDS = new String[]{LS,MKDIR,COPY,RM,CD,CDP};
}
