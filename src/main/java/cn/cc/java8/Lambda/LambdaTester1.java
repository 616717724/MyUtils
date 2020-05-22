package cn.cc.java8.Lambda;

public class LambdaTester1 {

	
	/*变量作用域
      lambda 表达式只能引用标记了 final 的外层局部变量，这就是说不能在 lambda 内部修改定义在域外的局部变量，否则会编译错误。
	  */

	final static String salutation = "Hello! ";

	public static void main(String args[]){

		GreetingService greetService1 = message -> System.out.println(salutation + message);

		greetService1.sayMessage("Runoob");
	}

	interface GreetingService {
		void sayMessage(String message);
	}

}
