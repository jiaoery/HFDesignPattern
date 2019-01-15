import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Project Name:DecoratorModelBeverage
 * File Name:InputTest.java
 * Package Name:
 * Date:2019-1-15下午1:57:53
 * Copyright (c) 2019, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:InputTest <br/>
 * Function: 输入相关测试类
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-1-15 下午1:57:53 <br/>
 * @author   吉祥
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class InputTest {
    public static void main(String[] args) {
        int c;
        try {
            InputStream inputStream=new LowerCaseInputStream(new BufferedInputStream(new FileInputStream("test.txt")));
            while((c=inputStream.read())>=0){
                System.out.print((char)c);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
