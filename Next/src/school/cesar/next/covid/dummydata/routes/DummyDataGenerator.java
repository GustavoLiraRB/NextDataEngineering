package school.cesar.next.covid.dummydata.routes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DummyDataGenerator implements Runnable {
	private Date start;
	private long incrementMillis;
	private DummyDataProcessor processor;
	private long points;
	private String route;
	private List<LatLng> pointList;

	public DummyDataGenerator(String route, Date start, long incrementMillis, long points, DummyDataProcessor processor) {
		this.start = start;
		this.incrementMillis = incrementMillis;
		this.points = points;
		this.processor = processor;
		this.route = route;
	}
	
	public void run() {
		try {
			Date epoch = this.start;
			preprocessRoute();
			long i=0;
			int j=0;
			boolean backwards = false;
			while (true) {
				LatLng latLng = pointList.get(j);
				processor.onData(new DummyData(latLng.lat, latLng.lng, epoch));
				epoch = new Date(epoch.getTime()+incrementMillis);
				if (backwards) {
					j-=1;
					if (j<0) {
						j=0;
						backwards = false;
					}
				}
				else {
					j+=1;
					if (j==pointList.size()) {
						j=pointList.size()-1;
						backwards = true;
					}
				}
				System.out.println(Thread.currentThread().getName() + " registro " + i + " / " + points + " " + route);
				if (++i>points)
					break;
			}
		} catch (IOException e) {
			System.out.println("Coloque os arquivos de rota no C:");
			throw new RuntimeException(e);
		}
		
	}

	private void preprocessRoute() throws IOException {
		pointList = new ArrayList<>();
		//BufferedReader bufferedTextIn = null;
		//String arquivo = "/src/resources/school/cesar/next/covid/dummydata/routes/" + this.route + ".txt";

		//bufferedTextIn = new BufferedReader(new FileReader(arquivo));
		//System.out.println(bufferedTextIn.readLine());
		String arquivo = "C:" + File.separator + this.route + ".txt";
		BufferedReader reader = new BufferedReader(new FileReader(arquivo));
		String s = null;
		while ((s = reader.readLine()) != null) {
			pointList.add(new LatLng(s));
		}
	}
}
