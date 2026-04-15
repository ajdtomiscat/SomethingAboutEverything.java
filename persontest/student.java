package persontest;

public class student extends person implements bedroom{
    public student() {
    }
    public student(String name, String sex, int age){
        super.setName(name);
        super.setSex(sex);
        super.setAge(age);
    }

    @Override
    public void doing() {
        System.out.println("学习");
    }
    public void sleep(){
        System.out.println("教室");
    }
}
