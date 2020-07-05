package shejimos;

import com.yibu.SS;

import javax.validation.constraints.Max;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class zuida {

    public static void main(String[] args) throws IOException {
        int[][] a = new int[5][10];
        System.out.println(a.length);
        File file = new File("C:\\Users\\aaa\\Desktop\\zuida.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = null;

        List<List<Integer>> lists = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            String[] split = line.split(",");
            ArrayList<Integer> objects = new ArrayList<>();
            for (String value : split) {
                objects.add(Integer.parseInt(value));
            }
            lists.add(objects);
        }
        int hang = lists.size();
        int lie = lists.get(0).size();
        int[][] ints = new int[hang][lie];

        for (int i = 0; i < ints.length; i++) {
            int[] onehang = ints[i];
            List<Integer> integers = lists.get(i);
            for (int j = 0; j < integers.size(); j++) {
                onehang[j] = integers.get(j);
            }
        }

        print(ints);


        System.out.println(max(ints));

        Solution solution = new Solution();
        System.out.println(solution.maxAreaOfIsland(ints));
        print(ints);
    }


    public static void print(int[][] a) {
        for (int[] ints1 : a) {
            for (int x : ints1) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }

    public static int max(int[][] ints) {
        int max = 0;

        for (int i = 0; i < ints.length; i++) {
            int[] hang = ints[i];
            for (int j = 0; j < hang.length; j++) {
                int lie = hang[j];
                if (lie > 0) {
                    List<Point> list = new ArrayList<>();
                    list.add(new Point(i, j));
                    max = Math.max(max, getSize(ints, i, j, list));
                }
            }
        }


        return max;
    }

    private static int getSize(int[][] ints, int hang, int lie, List<Point> points) {
        int cur;
        try {
            cur = ints[hang][lie];
        } catch (Exception e) {
            return 0;
        }

        if (cur == 0) {
            return 0;
        }
        Point z1 = new Point(hang, lie - 1);
        Point y1 = new Point(hang, lie + 1);
        Point s1 = new Point(hang - 1, lie);
        Point x1 = new Point(hang + 1, lie);
        if (!points.contains(z1)) {
            points.add(z1);
            cur += getSize(ints, hang, lie - 1, points);
        }
        if (!points.contains(y1)) {
            points.add(y1);
            cur += getSize(ints, hang, lie + 1, points);
        }
        if (!points.contains(s1)) {
            points.add(s1);
            cur += getSize(ints, hang - 1, lie, points);
        }
        if (!points.contains(x1)) {
            points.add(x1);
            cur += getSize(ints, hang + 1, lie, points);
        }


        return cur;
    }
}
