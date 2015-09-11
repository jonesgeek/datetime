/**
 * 
 */
package datetime;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;

/**
 * @author will
 *
 */
public class ISODateFormatTest {
	private DateFormat df;
	private Date date;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Calendar cal = new GregorianCalendar(2007, 8 - 1, 13, 19, 51, 23);
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));
        cal.set(Calendar.MILLISECOND, 0);
        date = cal.getTime();
        df = new ISODateFormat();
	}

	@Test
	public void testFormat() {
        String result = df.format(date);
        assertEquals("2007-08-13T19:51:23Z", result);
    }

	@Test
    public void testParse() throws Exception {
        Date result = df.parse("2007-08-13T19:51:23Z");
        assertEquals(date, result);

        // Test parsing date-only values with and without a timezone designation
        Date dateOnly = df.parse("2007-08-14");
        Calendar cal = new GregorianCalendar(2007, 8-1, 14);
        assertEquals(cal.getTime(), dateOnly);
        
        Date yearOnly = df.parse("2007");
        cal = new GregorianCalendar(2007, 1-1, 1);
        assertEquals(cal.getTime(), yearOnly);
        
        Date yearDay = df.parse("2007-365");
        cal = new GregorianCalendar(2007, 12-1, 31);
        assertEquals(cal.getTime(), yearDay);
    }

	@Test
    public void testCloneObject() throws Exception {
        DateFormat clone = (DateFormat)df.clone();
        assertSame(df, clone);
    }
}
