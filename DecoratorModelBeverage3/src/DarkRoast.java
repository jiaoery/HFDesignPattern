/**
 * Project Name:DecoratorModelBeverage
 * File Name:DarkRoast.java
 * Package Name:
 * Date:2019-1-14ÏÂÎç3:17:11
 * Copyright (c) 2019, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:DarkRoast <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-1-14 ÏÂÎç3:17:11 <br/>
 * @author   ¼ªÏé
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class DarkRoast extends Beverage{

    public DarkRoast(){
        description="Most Excellent Dark Roast!";
    }
    
    public double cost(){
        return 1.99+super.cost();
    }
}
