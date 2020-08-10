package zerenlianmos;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;

public class TTT {


    public static void main(String[] args) {
        // 0  0 00000 00000 00000    00010 00000 00000
        // 1  1 11111 11111 11111    11110 00000 00000
//        int id = 1 ^ 1;
//        System.out.println(id);
//        System.out.println(id ^= 1);
//        System.out.println(id);
        long i = 0x4000000000000000L;
        System.out.println(i << 1);
        System.out.println(Long.MAX_VALUE / 2);

        for (int j = 1; true;j++) {

            i = i >> 1;

            if (i == 0) {

                System.out.println(j);
                break;
            }
        }
    }

    public void test(Object... a) {
        for (Object o : a) {

        }
        System.out.println(a.getClass().getName());
    }


}
