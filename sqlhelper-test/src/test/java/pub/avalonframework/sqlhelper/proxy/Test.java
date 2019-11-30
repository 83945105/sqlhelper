package pub.avalonframework.sqlhelper.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by 白超 on 2019/7/13.
 */
public class Test {

    public static void main(String[] args) {
        //要代理的真实对象
        People people = new Teacher();
        //代理对象的调用处理程序，我们将要代理的真实对象传入代理对象的调用处理的构造函数中，最终代理对象的调用处理程序会调用真实对象的方法
        PeopleDynamicProxy peopleDynamicProxy = new PeopleDynamicProxy(new Teacher());
        /**
         * 通过Proxy类的newProxyInstance方法创建代理对象，我们来看下方法中的参数
         * 第一个参数：people.getClass().getClassLoader()，使用handler对象的classloader对象来加载我们的代理对象
         * 第二个参数：people.getClass().getInterfaces()，这里为代理类提供的接口是真实对象实现的接口，这样代理对象就能像真实对象一样调用接口中的所有方法
         * 第三个参数：handler，我们将代理对象关联到上面的InvocationHandler对象上
         */
        People proxy = (People) Proxy.newProxyInstance(peopleDynamicProxy.getClass().getClassLoader(), People.class.getInterfaces(), peopleDynamicProxy);
        //System.out.println(proxy.toString());
        System.out.println(proxy.work());
    }
}
