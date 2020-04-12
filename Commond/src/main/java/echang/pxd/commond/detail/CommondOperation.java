package echang.pxd.commond.detail;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @Description 接收用户输入和解析输入类型
 * @Author 彭孝东
 * @QQ 932056657
 */
public class CommondOperation {
    //回调对象
    private ICommond listener;
    //获取输入信息
    private Scanner mScanner;
    //保存所有指令
    private List<String> commonds;

    public CommondOperation() {
        mScanner = new Scanner(System.in);
        //将普通的Array类型转化为List
        commonds = Arrays.asList(ICmd.COMMONDS);
    }

    //copy file1 file2
    //next()  以空格作为结束符
    //nextLine() 读取一行数据 以\n结尾
    //接收用户输入的指令
    public void readCommond(ICommond listener) throws PXDException.CommondNotExistException, PXDException.CommondArgumentErrorException {
        this.listener = listener;

        //接收指令
        String commond = mScanner.nextLine();

        //解析指令
        parseCommond(commond);
    }

    //ls
    //rm file
    //copy file1 file2
    private void parseCommond(String commond) throws PXDException.CommondNotExistException, PXDException.CommondArgumentErrorException {
        //将指令以空格未分割符 分开
        String[] componts = commond.split(" ");

        //获取用户指令
        String cmd = componts[0];

        //判断指令是否存在
        if (!commonds.contains(cmd)){
            //输入的指令不存在
            //抛出异常
            throw new PXDException.CommondNotExistException("指令不存在");
        }

        //存在就解析是那种指令
        switch (cmd){
            case ICmd.CD:
                //[cd file]
                if (componts.length != 2){
                    throw new PXDException.CommondArgumentErrorException("cd 参数不正确");
                }
                listener.cd_to_child(componts[1]);
                break;
            case ICmd.CDP:
                //cd..
                if (componts.length != 1){
                    throw new PXDException.CommondArgumentErrorException("cd.. 不需要参数");
                }
                listener.cd_to_parent();
                break;
            case ICmd.MKDIR:
                //mkdir file
                if (componts.length != 2){
                    throw new PXDException.CommondArgumentErrorException("mkdir 参数不正确");
                }
                listener.mkdir(componts[1]);
                break;
            case ICmd.RM:
                //rm file
                if (componts.length != 2){
                    throw new PXDException.CommondArgumentErrorException("rm 参数不正确");
                }
                listener.remove(componts[1]);
                break;
            case ICmd.LS:
                //ls
                if (componts.length != 1){
                    throw new PXDException.CommondArgumentErrorException("ls 不需要参数");
                }
                listener.list();
                break;
            case ICmd.COPY:
                //copy file1 file2
                if (componts.length != 3){
                    throw new PXDException.CommondArgumentErrorException("copy 参数不正确");
                }
                listener.copy(componts[1],componts[2]);
                break;
        }
    }


}
