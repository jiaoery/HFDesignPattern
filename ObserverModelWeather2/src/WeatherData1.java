import java.util.ArrayList;
import java.util.Observable;

/**
 * Project Name:ObserverModelWeather
 * File Name:WeatherData1.java
 * Package Name:
 * Date:2018-11-8����1:34:43
 * Copyright (c) 2018, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:WeatherData1 <br/>
 * Function: �۲���ģʽ�µ�WeatherData1
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-11-8 ����1:34:43 <br/>
 * @author   ����
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class WeatherData1 implements Subject{
    
    private ArrayList<Observer> observers;
    
    private float temperature;
    
    private float humiditty;
    
    private float pressure;
    
    public WeatherData1(){
        observers=new ArrayList<Observer>();
    }

   //ע��
    public void registerObserver(Observer o) {
        observers.add(o);
        
    }

    //ɾ��
    public void removeObserver(Observer o) {
       int i=observers.indexOf(o);
       if(i>=0){
           observers.remove(i);
       }
        
    }

    //֪ͨ�۲������ݱ仯
    public void notifyObservers() {
        for(int i=0;i<observers.size();i++){
            Observer observer=observers.get(i);
            observer.update(temperature, humiditty, pressure);
        }
        
    }
    
    public void measurementsChanged(){
        notifyObservers();
    }
    
    public void setMeasurements(float temperature,float humidity,float pressure){
        this.temperature=temperature;
        this.humiditty=humidity;
        this.pressure=pressure;
        measurementsChanged();
    }

}
