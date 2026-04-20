public class Stu {
    String name;
    int age;
    int ID;

    public Stu() {
    }

    public Stu(String name, int age, int ID) {
        this.name = name;
        this.age = age;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public static boolean len(Stu[] list){
        int count=0;
        for (int i = 0; i < list.length; i++) {
            if(list[i] !=null){
                count++;
            }
        }
        if(count==list.length){
            return false;
        }
        return true;
    }
    public static boolean ifid(Stu[] list, Stu s){
        for (int i = 0; i < list.length; i++) {
            if(list[i]!=null&&list[i].ID==s.ID){
                return false;
            }
        }
        return true;
    }
    public static void addstu(Stu[] list, Stu s) {
        // 1. 先判断 ID 是否重复
        // 注意：这里修改了 ifid 的调用逻辑，防止空指针异常
        if (!ifid(list, s)) {
            System.out.println("ID重复！");
            return; // 如果ID重复，直接结束方法，不再执行后续代码
        }

        // 2. 再判断数组是否已满 (使用 len 方法)
        // len(list) 返回 true 表示有空位(未满)，返回 false 表示已满
        if (len(list)) {
            // 数组未满，直接添加
            for (int i = 0; i < list.length; i++) {
                if (list[i] == null) {
                    list[i] = s;
                    System.out.println("添加成功！");
                    return;
                }
            }
        } else {
            // 数组已满，提示无法添加（原代码的扩容逻辑在void方法中无效，建议仅做提示或改用返回值）
            System.out.println("数组已满，无法添加！");

            // 下面的扩容代码仅作为逻辑展示，但在 void 方法中无法真正改变外部数组
            /*
            Stu[] newlist = new Stu[list.length + 1];
            for (int i = 0; i < list.length; i++) {
                newlist[i] = list[i];
            }
            newlist[list.length] = s;
            // 注意：这里 newlist 无法传回 main 方法
            */
        }
    }

    public static void main(String[] args){
        Stu[] stulist=new Stu[3];
        Stu s1=new Stu("张三",22,1234);
        Stu s2=new Stu("李四",20,1235);
        Stu s3=new Stu("王五",21,1236);
        Stu s4=new Stu("吊毛",21,1237);
        stulist[0]=s1;
        stulist[1]=s2;
//        stulist[2]=s3;
        addstu(stulist,s4);
        for (int i = 0; i < stulist.length; i++) {
            System.out.println(stulist[i].name+" "+stulist[i].age+" "+stulist[i].ID);
        }
    }
}
