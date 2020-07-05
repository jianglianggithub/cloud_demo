package denmo;

public class 回文数 {

    public static void main(String[] args) {
        System.out.println(isPalindrome(11011));
    }

    public static boolean test(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i);
        StringBuilder reverse = stringBuilder.reverse();
        return reverse.toString().equals(i+"");
    }

    public static boolean test1(int i) {
        int res = 0;
        int temp = i;
        while (res < temp) {
            res = res * 10 + i % 10;
            temp /= 10;
        }

        return res == i;
    }

    /**
     *  这个堪称经典了。 比如 12321 这个数字 x = 123 的时候 reversed = 12 那么这时候在进去 就等于 123  x = 12
     *   为什么相等 是因为 偶数。 是偶数的情况的话。 奇数的情况 中位数 不需要判断。经典了这个算法。
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        if (x == 0) return true;
        if (x < 0 || x % 10 == 0) return false;
        int reversed = 0;
        while (x > reversed) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }
        return x == reversed || x == reversed / 10;
    }
}
