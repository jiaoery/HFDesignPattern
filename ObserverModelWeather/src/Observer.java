/**
 * Project Name:ObserverModelWeather
 * File Name:Observer.java
 * Package Name:
 * Date:2018-11-8����11:29:11
 * Copyright (c) 2018, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:Observer <br/>
 * Function: �۲���
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-11-8 ����11:29:11 <br/>
 * @author   ����
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface Observer {

   /**
    * 
    * update:������վ�Ĺ۲����ݷ����ı�ʱ����������ᱻ����
    * @author ����
    * @param temp �¶�
    * @param hunmidity ʪ��
    * @param pressure  ��ѹ
    * @since JDK 1.6
    */
    public void update(float temp,float hunmidity,float pressure);
}
