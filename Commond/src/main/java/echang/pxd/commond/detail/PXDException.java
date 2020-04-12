package echang.pxd.commond.detail;

/**
 * @Description 自定义异常
 * @Author 彭孝东
 * @QQ 932056657
 */
public class PXDException {
    /**
     * 指令不存在
     */
    static class CommondNotExistException extends Exception{
        public CommondNotExistException(String s) {
            super(s);
        }
    }

    static class CommondArgumentErrorException extends Exception{
        public CommondArgumentErrorException(String s) {
            super(s);
        }
    }
}
