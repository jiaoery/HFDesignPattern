import java.util.Observable;
import java.util.Observer;

/**
 * Project Name:ObserverModelWeather
 * File Name:CurrentConditionsDisplay1.java
 * Package Name:
 * Date:2018-11-8下午3:48:22
 * Copyright (c) 2018, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:CurrentConditionsDisplay1 <br/>
 * Function: JAVA 自带观察者模式
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-11-8 下午3:48:22 <br/>
 * @author   吉祥
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class CurrentConditionsDisplay1 implements Observer,DisplayElement{
    
    private Observable observable;
    
    private float temperature;
    
    private float humidity;
    
    private float pressure;
    
    //构造器需要传入Observable参数，并登记成为观察者
    public CurrentConditionsDisplay1(Observable observable){
        this.observable=observable;
        observable.addObserver(this);
    }
    
    //update方法增加Observable和数据对象作为参数
    public void update(Observable o, Object arg) {
        if(arg instanceof WeatherData2){
            WeatherData2 weatherData2=(WeatherData2) arg;
            this.temperature=weatherData2.getTemperature();
            this.humidity=weatherData2.getHumidity();
            this.pressure=weatherData2.getPressure();
            display();
        }
        
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
