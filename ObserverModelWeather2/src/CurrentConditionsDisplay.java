/**
 * Project Name:ObserverModelWeather
 * File Name:CurrentConditionsDisplay.java
 * Package Name:
 * Date:2018-10-31����3:27:37
 * Copyright (c) 2018, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:CurrentConditionsDisplay <br/>
 * Function: Ŀǰ״�������
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-10-31 ����3:27:37 <br/>
 * @author   ����
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
     * update:���²��������
     * @author ����
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
     * display:չʾ���������
     * @author ����
     * @since JDK 1.6
     */
    public void display(){
        System.out.println("Current conditons:"+temperature
                +"F degrees and "+humidity+"% humidity");
    }
}
