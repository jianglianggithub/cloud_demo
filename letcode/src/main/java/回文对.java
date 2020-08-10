import java.util.ArrayList;
import java.util.List;



public class 回文对 {

    public static void main(String[] args) {
        List<List<Integer>> init = init(new String[]{"a",""});
        System.out.println(init);
    }

    private static List<List<Integer>> init(String[] strings) {
        List<List<Integer>> rs = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {

            for (int j = 0; j < strings.length; j++) {
                if (i == j) {
                    continue;
                }
                boolean temp = test(strings[i],strings[j]);
                if (temp) {
                    List<Integer> in = new ArrayList<>();
                    in.add(i);
                    in.add(j);
                    rs.add(in);
                }

            }

        }
        return rs;
    }

    private static boolean test(String r1, String r2) {
        String r3 = r1 + r2;
        if ((r3.length() & 1) != 0) {
           return test1(r3,0);
        }else {
           return test1(r3,1);
        }
    }

    private static boolean test1(String r3,int e) {
        char[] chars = r3.toCharArray();

        boolean temp = true;
        for (int i = 0,y = chars.length - 1; i < chars.length; i++,y--) {
            if (chars[i] != chars[y]) {
                temp = false;
            }

            if (Math.abs(i - y) == e) {
                break;
            }
        }

        return temp;
    }


}
