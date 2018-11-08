/**
 * Project Name:ObserverModelWeather
 * File Name:CurrentConditionsDisplay.java
 * Package Name:
 * Date:2018-10-31下午3:27:37
 * Copyright (c) 2018, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:CurrentConditionsDisplay <br/>
 * Function: 目前状况布告板
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-10-31 下午3:27:37 <br/>
 * @author   吉祥
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class CurrentConditionsDisplay implements Observer,DisplayElement{
    
    private float temperature;
    private float humidity;
    private float pressure;
    private Subject weatherData;
    
    public CurrentConditionsDisplay(Subject weatherData){
        this.weatherData=weatherData;
        weatherData.registerObserver(this);
    }
    
    /**
     * 
     * update:更新布告板内容
     * @author 吉祥
     * @param temperature
     * @param humidity
     * @param pressure
     * @since JDK 1.6
     */
    public void update(float temperature,float humidity,float pressure){
        this.temperature=temperature;
        this.humidity=humidity;
        this.pressure=pressure;
        display();
    }

    /**
     * 
     * display:展示布告板内容
     * @author 吉祥
     * @since JDK 1.6
     */
    public void display(){
        System.out.println("Current conditons:"+temperature
                +"F degrees and "+humidity+"% humidity");
    }
}
