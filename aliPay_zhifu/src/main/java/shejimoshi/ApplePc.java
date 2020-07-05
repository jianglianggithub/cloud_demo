package shejimoshi;


// 工厂模式
public class ApplePc implements Pc {
    @Override
    public void getPc() {
        System.out.println("Apple Pc");
    }
}


class Lenve implements Pc {

    @Override
    public void getPc() {
        System.out.println("lenevo Pc");
    }
}

class Factory {

    public static void go(String pcName) {
        switch (pcName) {
            case "lenvo":
                // new Apllepc
                break;
            case "apple":
                // new lengvo
                break;
        }
    }
}

// 工厂方法 就是在工厂模式基础上 将 一个工厂提供多个 产品 提供了不同的工厂实现

interface IFactory {
    void getPc();
}


class ApplePcFacotry implements IFactory {

    @Override
    public void getPc() {
        new Lenve();
    }
}

class LenvovePcFacotry implements IFactory {

    @Override
    public void getPc() {
        new ApplePc();
    }
}

class start {
    public void go() {
        new LenvovePcFacotry().getPc(); //忘记写返回值了
    }
}


// 抽象工厂 就是 工厂方法每次添加一种产品就得多一个工厂实现 那么一个工厂 接口提供创建多种产品 就不需要那么多 工厂了

