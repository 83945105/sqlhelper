package pub.avalonframework.sqlhelper.proxy;

/**
 * Created by 白超 on 2019/7/13.
 */
public class Teacher implements People {
    @Override
    public String work() {
        System.out.println("老师教书育人");
        return "教书";
    }
}
