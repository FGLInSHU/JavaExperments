/**
 * @author FGL_S
 */
public class IntegerUtil {
    public static void equalsTest() {
        Integer a = Integer.valueOf(1);
        Integer b = Integer.valueOf(1);
        Integer d = new Integer(1);
        if (a == b) {
            System.out.println("a==b");
        }

        int c = 1;
        if (c == a) {
            System.out.println("c ==a");
        }
        if (a == c) {
            System.out.println("a == c");
        }
         if (a == d){
             System.out.println("a==d");
         }
         if (c == d) {
             System.out.println("c==d");
         }
         if (d == d){
             System.out.println("d==c");
         }
        System.out.println("compare " + Integer.compareUnsigned(1, -1));
    }
}
