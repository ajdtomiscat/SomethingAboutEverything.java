import java.util.Scanner;

public class luoma {
    static void main(String[] args) {
        String number;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入数字");
            number = scanner.next();
            if(ifnumber(number)){
                break;
            }else{
                System.out.println("输入有误");
            }
        }
        StringBuilder stringnumber=new StringBuilder();
        String[] luoma={"","Ⅰ","Ⅱ","Ⅲ","Ⅳ","Ⅴ","Ⅵ","Ⅶ","Ⅷ","Ⅸ"};
        for (int i = 0; i < number.length(); i++) {
            int a=number.charAt(i)-48;
            String lmnum=luoma[a];
            stringnumber.append(lmnum);
        }
        System.out.println(stringnumber.toString());
    }

    public static boolean ifnumber(String number) {
        if (number.length() > 9) {
            return false;
        }
        for (int i = 0; i < number.length(); i++) {
            char a = number.charAt(i);
            if (a < '0' || a > '9') {
                return false;
            }
        }
        return true;
    }

}
