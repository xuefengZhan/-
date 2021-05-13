public class _00_ {
    public static void test(int n, int k){
        for(int i = 0; i < n; i++){
            System.out.println("test");
        }
        for (int i = 0; i < k; i++){
            System.out.println("test");
        }
    }
    public static int fib1(int n) {
        if (n <= 1) return n;
        return fib1(n - 1) + fib1(n - 2);
    }
}
