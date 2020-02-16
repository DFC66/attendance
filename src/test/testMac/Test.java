import java.util.Calendar;

/**
 * @author: zsh
 * @Date:10:43 2018/5/19
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar calendar= Calendar.getInstance();
        System.out.println("今天是"+weekDays[calendar.get(Calendar.DAY_OF_WEEK)-1]);
    }
}
