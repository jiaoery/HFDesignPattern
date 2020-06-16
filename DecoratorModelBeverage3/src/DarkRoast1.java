/* Project Name:DecoratorModelBeverage
 * File Name:DarkRoast.java
 * Package Name:
 * Date:2019-1-14下午3:17:11
 * Copyright (c) 2019, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:DarkRoast <br/>
 * Function: 装饰者模式的DarkRoast
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-1-14 下午3:17:11 <br/>
 * @author   吉祥
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class DarkRoast1 extends Beverage1{
	
	public DarkRoast1(){
        description="DarkRoast1";
    }
    
    public double cost() {
      
        return 0.99;
    }

}
