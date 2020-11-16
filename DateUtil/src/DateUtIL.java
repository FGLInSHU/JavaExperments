import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;

public class DateUtIL {
    private static SimpleDateFormat fromatYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat fromat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat formatHH = new SimpleDateFormat("HH");
    public static void main(String[] args) {
        ThreadLocal
        Integer intHH = Integer.parseInt(formatHH.format(new Date()));
        String str1 = fromatYYMMDD.format(new Date());
        Date date1 = new Date();
        date1.setTime(date1.getTime() - 10 * 60 * 1000);
        System.out.println(date1.toString());
        try {
            Date date = fromat.parse(str1 + " " + "09:00:00");
            System.out.println(date.toString());
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
