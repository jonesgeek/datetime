/**
 * 
 */
package datetime;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/**
 * @author will
 *
 */
public class ISODateFormat extends DateFormat {
	DateTimeFormatter iso8601Parser = ISODateTimeFormat.dateTimeParser();
	DateTimeFormatter iso8601Printer = ISODateTimeFormat.dateTimeNoMillis().withZoneUTC();

	/* (non-Javadoc)
	 * @see java.text.DateFormat#format(java.util.Date, java.lang.StringBuffer, java.text.FieldPosition)
	 */
	@Override
	public StringBuffer format(Date date, StringBuffer toAppendTo,
			FieldPosition fieldPosition) {
		toAppendTo.append(iso8601Printer.print(new DateTime(date.getTime())));
		return toAppendTo;
	}

	/* (non-Javadoc)
	 * @see java.text.DateFormat#parse(java.lang.String, java.text.ParsePosition)
	 */
	@Override
	public Date parse(String source, ParsePosition pos) {
		DateTime dateTime = iso8601Parser.parseDateTime(source);
		return dateTime.toDate();
	}
	
	//supply our own parse(String) since pos isn't updated during parsing,
    //but the exception should have the right error offset.
    @Override
    public Date parse(String source) throws ParseException {
        return parse(source, new ParsePosition(0));
    }
    
    /*
     * (non-Javadoc)
     * @see java.text.DateFormat#clone()
     */
    @Override
    public Object clone() {
        /* Jackson calls clone for every call. Since this instance is
         * immutable (and hence thread-safe)
         * we can just return this instance
         */
        return this;
    }

}
