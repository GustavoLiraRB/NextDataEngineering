package school.cesar.next.covid.dummydata.routes;

public class LatLng {
	public double lat;
	public double lng;
	public LatLng(String latLng) {
		String[] parts = latLng.split(",");
		this.lat = Double.parseDouble(parts[0]);
		this.lng = Double.parseDouble(parts[1]);
	}
}
