package school.cesar.next.covid.dummydata.routes;

public class StdoutDummyDataProcessor implements DummyDataProcessor {
	private boolean asLong;
	public StdoutDummyDataProcessor() {
	}
	public StdoutDummyDataProcessor(boolean asLong) {
		this.asLong = asLong;
	}
	public void onData(DummyData data) {
		System.out.println(data.lat + ";" + data.lng + ";" + (asLong ? data.date.getTime() : data.date));
	}

}
