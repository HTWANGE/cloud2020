import java.time.ZonedDateTime;

/**
 * Created by XB on 2020/5/7.
 */
public class T2 {
    public static void main(String [] args){
        ZonedDateTime zonedDateTime = ZonedDateTime.now(); //默认时区
        System.out.println(zonedDateTime);

        //2020-05-07T11:31:04.765+08:00[Asia/Shanghai]
    }
}
