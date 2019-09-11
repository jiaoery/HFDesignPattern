
/**
 * @ClassName: SimplePizzaFactory.java
 * @Description:����ģʽ
 * 
 * @author  jixiang
 * @version v1.0.0 
 * @Date    2019��9��6�� ����11:39:59 
 */
public class SimplePizzaFactory {
	public Pizza createPizza(String type) {
		Pizza pizza=null;
		if(type.equals("cheese")) {
			pizza=new CheesePizza();
		}else if (type.equals("greek")) {
			pizza=new GreekPizza();
		}else if (type.equals("pepperoni")) {
			pizza=new PepperoniPizza();
		}
		return pizza;
	}
}
