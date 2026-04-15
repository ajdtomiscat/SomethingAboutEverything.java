package persontest;

public class test {
    static void main(String[] args) {
        student t=new student("小呆呆","男",20);
        System.out.println(t.getSex()+","+t.getName()+","+t.getAge());
        t.doing();
        t.sleep();
        teacher n=new teacher("小施","女",25);
        System.out.println(n.getSex()+","+n.getName()+","+n.getAge());
        n.doing();
        n.sleep();
        person x=new student();
        System.out.println(x instanceof teacher);
    }
}
