package shejimos;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Filter1 implements Filter {
    @Override
    public void filter(Invoker invoker, String param) {
        System.out.println("第一个过滤器" + param);
    }

    private static final ConcurrentMap<String, Object> locks = new ConcurrentHashMap<>();
    public static void main(String[] args)  {
        System.out.println(2 | 0x1);
    }

    public void aaa(String aa) {

    }


}
