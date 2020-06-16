
/**
 * @ClassName: AbstactPizzaStore.java
 * @Description:����PizzaStore��
 * 
 * @author  jixiang
 * @version v1.0.0 
 * @Date    2019��9��10�� ����3:24:28 
 */
public abstract class AbstactPizzaStore {
	
	/**
	 * ������������PIZZA
	 * @param type
	 * @return PIZZA
	 */
	public Pizza orderPizza(String type) {
		Pizza pizza;
		//orderPizzaͨ������type���ͣ�ʹ�ù�����ɴ���
		pizza=createPizza(type);
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza; 
	}
	
	/**
	 * �����������Ƶ�����
	 * @param type 
	 * @return PIZZA
	 */
	public abstract Pizza createPizza(String type);

}
