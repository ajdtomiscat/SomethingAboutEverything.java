public class StringUtils {

    public static void main(String[] args) {
        String str1 = null;
        String str2 = "";
        String str3 = " ";
        String str4 = "abc";
        String str5 = null;
        String str6 = "123";
        String str7 = "123q";
        String str8 = "0.33";
        System.out.println(StringUtils.isEmpty(str1));
        System.out.println(StringUtils.isEmpty(str2));
        System.out.println(StringUtils.isEmpty(str3));
        System.out.println(StringUtils.isEmpty(str4));
        System.out.println("=====");
        System.out.println(StringUtils.isNotEmpty(str1));
        System.out.println(StringUtils.isNotEmpty(str2));
        System.out.println(StringUtils.isNotEmpty(str3));
        System.out.println(StringUtils.isNotEmpty(str4));
        System.out.println("=====");
        System.out.println(StringUtils.isBlank(str1));
        System.out.println(StringUtils.isBlank(str2));
        System.out.println(StringUtils.isBlank(str3));
        System.out.println(StringUtils.isBlank(str4));
        System.out.println("=====");
        System.out.println(StringUtils.isNotBlank(str1));
        System.out.println(StringUtils.isNotBlank(str2));
        System.out.println(StringUtils.isNotBlank(str3));
        System.out.println(StringUtils.isNotBlank(str4));
        System.out.println("=====");
        System.out.println(StringUtils.split(str5,","));
        System.out.println(str5.split(","));
        System.out.println("=====");
        System.out.println(StringUtils.isNumeric(str6));
        System.out.println(StringUtils.isNumeric(str7));
        System.out.println(StringUtils.isNumeric(str8));
        System.out.println("=====");
        List<String> list = Lists.newArrayList("a", "b", "c");
        List<Integer> list2 = Lists.newArrayList(1, 2, 3);
        System.out.println(StringUtils.join(list, ","));
        System.out.println(StringUtils.join(list2, " "));
        }
}