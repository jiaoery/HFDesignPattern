import java.util.Observable;
import java.util.Observer;

/**
 * Project Name:ObserverModelWeather
 * File Name:CurrentConditionsDisplay1.java
 * Package Name:
 * Date:2018-11-8����3:48:22
 * Copyright (c) 2018, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:CurrentConditionsDisplay1 <br/>
 * Function: JAVA �Դ��۲���ģʽ
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-11-8 ����3:48:22 <br/>
 * @author   ����
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class CurrentConditionsDisplay1 implements Observer,DisplayElement{
    
    private Observable observable;
    
    private float temperature;
    
    private float humidity;
    
    private float pressure;
    
    //��������Ҫ����Observable���������Ǽǳ�Ϊ�۲���
    public CurrentConditionsDisplay1(Observable observable){
        this.observable=observable;
        observable.addObserver(this);
    }
    
    //update��������Observable�����ݶ�����Ϊ����
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
     * display:չʾ���������
     * @author ����
     * @since JDK 1.6
     */
    public void display(){
        System.out.println("Current conditons:"+temperature
                +"F degrees and "+humidity+"% humidity");
    }
}
