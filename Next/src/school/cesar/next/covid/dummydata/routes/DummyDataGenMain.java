package school.cesar.next.covid.dummydata.routes;

import java.util.Calendar;
import java.util.Date;

public class DummyDataGenMain {
	public static void main(String[] args) {
		DummyDataProcessor stdoutProc = new StdoutDummyDataProcessor();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2019);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.MONTH, 0);
		DummyDataGenerator gen = new DummyDataGenerator("pcr-pilar", cal.getTime(), 5000l, 1000000l, stdoutProc);
		gen.run();
	}
}
