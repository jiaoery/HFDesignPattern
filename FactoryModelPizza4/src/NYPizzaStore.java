
/**
 * @ClassName: NYPizzaStore.java
 * @Description:TODO(��һ�仰�������ļ���ʲô) 
 * 
 * @author  jixiang
 * @version v1.0.0 
 * @Date    2019��9��10�� ����3:45:10 
 */
public class NYPizzaStore extends AbstactPizzaStore {

	@Override
	public Pizza createPizza(String type) {
		Pizza pizza=null;
		if(type.equals("cheese")) {
			pizza=new NYStyleCheesePizza();
		}else if (type.equals("greek")) {
			pizza=new NYStyleGreekPizza();
		}else if (type.equals("pepperoni")) {
			pizza=new NYStylePepperoniPizza();
		}
		return pizza;
	}

}
