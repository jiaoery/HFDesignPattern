
/**
 * @ClassName: PizzaStore.java
 * @Description:TODO(��һ�仰�������ļ���ʲô) 
 * 
 * @author  jixiang
 * @version v1.0.0 
 * @Date    2019��9��5�� ����9:40:33 
 */
public class PizzaStore {

	
	SimplePizzaFactory factory;
	
	public PizzaStore(SimplePizzaFactory factory) {
		this.factory=factory;
	}
	
	/**
	 * ������������PIZZA
	 * @param type
	 * @return PIZZA
	 */
	public Pizza orderPizza(String type) {
		Pizza pizza;
		//orderPizzaͨ������type���ͣ�ʹ�ù�����ɴ���
		pizza=factory.createPizza(type);
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza; 
	}


}
