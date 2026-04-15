package persontest;

public class teacher extends person implements bedroom{
    public teacher() {
    }
    public teacher(String name,String sex,int age){
        super.setName(name);
        super.setSex(sex);
        super.setAge(age);
    }

    @Override
    public void doing() {
        System.out.println("教书");
    }
    public void sleep(){
        System.out.println("办公室");
    }
}
