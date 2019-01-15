import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Project Name:DecoratorModelBeverage
 * File Name:LowerCaseInputStream.java
 * Package Name:
 * Date:2019-1-15下午1:48:29
 * Copyright (c) 2019, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:LowerCaseInputStream <br/>
 * Function: 大写字母转小写字母的输入流
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-1-15 下午1:48:29 <br/>
 * @author   吉祥
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class LowerCaseInputStream extends FilterInputStream{
    
    public LowerCaseInputStream(InputStream inputStream){
        super(inputStream);
    }
    
    
    public int read() throws IOException {
       int c=super.read();
       //判断相关的字符是否为大写，并转为小写
       return (c==-1?c:Character.toLowerCase((char)c));
    }
    
    /**
     * 
     *针对字符数组进行大写转小写操作
     * @see java.io.FilterInputStream#read(byte[], int, int)
     */
    public int read(byte[] b, int off, int len) throws IOException {
        int result=super.read(b,off,len);
        for(int i=off;i<off+result;i++){
            b[i]=(byte) Character.toLowerCase((char)b[i]);
        }
        return result;
    }

}
