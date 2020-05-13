package school.cesar.next.covid;

import ch.hsr.geohash.GeoHash;
import ch.hsr.geohash.WGS84Point;

public class MainGeoHash {
	public static void main(String[] args) {
		
		/**
		 * #   km      
			1   ± 2500
			2   ± 630
			3   ± 78
			4   ± 20
			5   ± 2.4
			6   ± 0.61
			7   ± 0.076
			8   ± 0.019
			9   ± 0.0024
			10  ± 0.00060
			11  ± 0.000074
		 */
		WGS84Point pacoFrevo = new WGS84Point(-8.061385, -34.871583);
		WGS84Point school = new WGS84Point(-8.059616, -34.872468);
		WGS84Point tiradentes = new WGS84Point(-8.058286, -34.872132);
		
		WGS84Point calcadaSchool = new WGS84Point(-8.059604, -34.872570);
		
		
		
		String hashTiradentes = GeoHash.geoHashStringWithCharacterPrecision(-8.058286, -34.872132, 10);
		GeoHash geohashCalcadaSchool = GeoHash.withCharacterPrecision(-8.059604, -34.872570, 10);
		System.out.println(geohashCalcadaSchool.getBoundingBox());
		String hashCalcadaSchool = GeoHash.geoHashStringWithCharacterPrecision(-8.059604, -34.872570, 10);
		String hashSchool = GeoHash.geoHashStringWithCharacterPrecision(-8.059605, -34.872572, 10);
		System.out.println(hashTiradentes);
		System.out.println(hashCalcadaSchool);
		System.out.println(hashSchool);
	}
}
