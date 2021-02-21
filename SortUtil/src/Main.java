// 本题为考试单行多行输入输出规范示例，无需提交，不计分。
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
        IntegerUtil.equalsTest();

        Scanner in = new Scanner(System.in);
        /* 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例 */
        while (in.hasNextLine()) {
            try {
                int b = Integer.parseInt(in.nextLine());
                if (b < 1) {
                    System.out.println("ERROR");
                    continue;
                }
                if (checkNumber(b + "")) {
                    System.out.println(b);
                } else {
                    int high = b, low = b;
                    while (true) {
                        high++;
                        low--;
                        if (checkNumber(high + "") || checkNumber(low + "")) {
                            if (checkNumber(high + "") && checkNumber(low + "")) {
                                System.out.println(low + "," + high);
                            } else if (checkNumber(high + "")) {
                                System.out.println(high);
                            } else if (checkNumber(low + "")) {
                                System.out.println(low);
                            }
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("ERROR");
            }
        }
    }
    public static boolean checkNumber(String in){
        if(in.length() == 1){
            return  true;
        }
        for(int i = 0; i < in.length() / 2; i++){
            if(in.charAt(i) != in.charAt(in.length() -1 - i)){
                return  false;
            }
        }
        return true;
    }
}