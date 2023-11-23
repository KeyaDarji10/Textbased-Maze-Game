public class Program5 {
    public static void main(String[] args) {
        String str="Hello there";
        System.out.println(str.length());
        if(str.equals("Days"))
        {
            System.out.println("Same");
        }
        else
        {
            System.out.println("Not same");
        }
        if(str.compareTo("Hello")>0)
        {
            System.out.println("hello is greater");
        }
        else
        {
            System.out.println("hello is not greater");
        }
        System.out.println(str.substring(1,8));
    }
}
