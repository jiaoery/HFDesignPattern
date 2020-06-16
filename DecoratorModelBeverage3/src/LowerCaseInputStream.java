import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Project Name:DecoratorModelBeverage
 * File Name:LowerCaseInputStream.java
 * Package Name:
 * Date:2019-1-15����1:48:29
 * Copyright (c) 2019, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:LowerCaseInputStream <br/>
 * Function: ��д��ĸתСд��ĸ��������
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-1-15 ����1:48:29 <br/>
 * @author   ����
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
       //�ж���ص��ַ��Ƿ�Ϊ��д����תΪСд
       return (c==-1?c:Character.toLowerCase((char)c));
    }
    
    /**
     * 
     *����ַ�������д�дתСд����
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
