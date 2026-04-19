import java.util.ArrayList;

public class listlearn {
    static void main(String[] args) {
        ArrayList<String> list=new ArrayList<>();
        list.add("asd");
        list.add("java");
        list.add("forever");
        list.add("nb");
        boolean a=list.remove("asd");
//        boolean a=list.remove(0);
        String b=list.set(2,"ok");
        String c=list.get(1);
        System.out.println(a+b+c);
        System.out.println(list);
//        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
        }
    }
}
