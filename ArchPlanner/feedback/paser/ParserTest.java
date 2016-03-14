package feedback.paser;

import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by lifengshuang on 3/8/16.
 */
public class ParserTest {


    @Test
    public void testCalendar() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
        Calendar calendar = Calendar.getInstance();
        Date result;
        try {
            result = dateFormat.parse("7");
            calendar.setTime(result);
        } catch (ParseException e){
            System.out.println("Parse Failed");
        }
//        assertEquals("5.4", calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.DATE));
        assertEquals("1994", calendar.get(Calendar.MONTH) + "");
    }

    @Test
    public void testNetty() throws Exception {
        Parser parser = new Parser();
        List<DateGroup> groups = parser.parse("add sth today");
        for (DateGroup group : groups) {
            List<Date> dates = group.getDates();
            int line = group.getLine();
            int column = group.getPosition();
            String matchingValue = group.getText();
            String syntaxTree = group.getSyntaxTree().toStringTree();
            Map parseMap = group.getParseLocations();
            boolean isRecurreing = group.isRecurring();
            Date recursUntil = group.getRecursUntil();
            for (Date date : dates) {
                System.out.println(date.getMonth() + " " + date.getDay() + " " + date.getHours() + " " + date.getMinutes());
            }
            System.out.println("column: " + column);
            System.out.println("matching value: " + matchingValue);
            System.out.println("tree: " + syntaxTree);
        }

    }
}