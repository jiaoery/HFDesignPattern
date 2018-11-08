import java.util.Observable;
import java.util.Observer;

/**
 * Project Name:ObserverModelWeather
 * File Name:ForecastDisplay1.java
 * Package Name:
 * Date:2018-11-8下午3:56:34
 * Copyright (c) 2018, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:ForecastDisplay1 <br/>
 * Function: JAVA 内置观察者模式
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-11-8 下午3:56:34 <br/>
 * @author   吉祥
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ForecastDisplay1 implements Observer,DisplayElement{
    private float temperature;
    private float humidity;
    private float pressure;
    private Observable observable;
    
    public ForecastDisplay1(Observable observable){
        this.observable=observable;
        observable.addObserver(this);
    }
    
   
    public void update(Observable o,Object arg){
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
        System.out.println("Forecast: More of the same");
    }

}
