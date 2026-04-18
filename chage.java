import java.util.Scanner;
public class chage {

    static void main(String[] args) {

        System.out.println("输入金额：");
        Scanner sc=new Scanner(System.in);
        int meony=sc.nextInt();
        if(meony<0||meony>9999999){
            System.out.println("金额错误！");
        }else {
        String[] number={"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
        String[] meonyname={"佰","拾","万","仟","佰","拾","元"};
        String meonystr="";
        while(true){
            int wei=meony%10;
            meony=meony/10;
            String weiname=number[wei];
            meonystr=weiname + meonystr;
            if(meony==0){
                break;
            }
        }
        String result="";
        if(meonystr.length()<7) {
            while (true) {
                meonystr = "零" + meonystr;
                if (meonystr.length() == 7) {
                    break;
                }
            }
        }
            char[] chars = meonystr.toCharArray();
            for (int i = 0; i < meonystr.length(); i++) {
                result=chars[i]+meonyname[i]+result;
//                System.out.print(chars[i]+meonyname[i]);
            }

            System.out.println(result);


    }
    }
}
