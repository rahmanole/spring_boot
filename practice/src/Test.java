public class Test {
    static boolean isPrime(int n) {
        boolean flag = true;
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static void main(String[] args) {

        for (int i = 2; i < 100; i++) {
            if(i>2){
                if (isPrime(i)) {
                    System.out.println(i);
                }
            }

        }

    }

}
