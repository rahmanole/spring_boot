public class Fibbo {

    public static void fibNUm(int n){

        int fibNum;
        int fNum=0;
        int lNum = 1;
        System.out.print(fNum+" ");
        System.out.print(lNum+" ");

        for(int i =0;i<=n;i++){
            fibNum = fNum+lNum;
            fNum = lNum;
            lNum = fibNum;
            System.out.print(fibNum+" ");
        }
    }

    public static boolean  leapYear(int i){
        if(i%400==0 || i%4==0)
            return true;

        return  false;
    }
    public static void main(String[] args) {
        //fibNUm(10);

        for(int i =1;i<=2019;i++){
            if(leapYear(i))
                System.out.println(i);
        }
    }



}
