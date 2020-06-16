/**
 * Project Name:ObserverModelWeather
 * File Name:StatisticsDisplay.java
 * Package Name:
 * Date:2018-10-31����3:36:45
 * Copyright (c) 2018, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:StatisticsDisplay <br/>
 * Function: ͳ�Ʋ����
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-10-31 ����3:36:45 <br/>
 * @author   ����
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class StatisticsDisplay implements Observer,DisplayElement{
    private float temperature;
    private float humidity;
    private float pressure;
    private Subject weatherData;
    
    public StatisticsDisplay(Subject weatherData){
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
        System.out.println("Avg/Max/Min temperature= "+temperature
                +"/"+temperature+"/"+temperature);
    }

}
