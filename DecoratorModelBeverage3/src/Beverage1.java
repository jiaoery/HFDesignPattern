/**
 * Project Name:DecoratorModelBeverage
 * File Name:Beverage1.java
 * Package Name:
 * Date:2019-1-14下午5:58:07
 * Copyright (c) 2019, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:Beverage1 <br/>
 * Function: 装饰者模式的Beverage类
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-1-14 下午5:58:07 <br/>
 * @author   吉祥
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public abstract class Beverage1 {
    String description="Unknown Beverage";
    
    public String getDescription(){
        return description;
    }
    
    public abstract double cost();
}
