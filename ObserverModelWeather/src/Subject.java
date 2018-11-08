/**
 * Project Name:ObserverModelWeather
 * File Name:Subject.java
 * Package Name:
 * Date:2018-11-8����11:25:54
 * Copyright (c) 2018, Changan Company All Rights Reserved.
 *
 */

/**
 * ClassName:Subject <br/>
 * Function: ����ӿڣ����۲���
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018-11-8 ����11:25:54 <br/>
 * @author   ����
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface Subject {

    //ע��۲���
    public void registerObserver(Observer o);
    
    //ɾ���۲���
    public void removeObserver(Observer o);
    
    //�����ⷢ�����ݱ仯ʱ��֪ͨ���й۲�
    public void notifyObservers();
    
}
