package sample1.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import sample1.model.Lugar;

public class HelperVarios {

	/**
		 * Calculate distance between two points in latitude and longitude taking
		 * into account height difference. If you are not interested in height
		 * difference pass 0.0. Uses Haversine method as its base.
		 * 
		 * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
		 * el2 End altitude in meters
		 * @returns Distance in Kilo Meters
		 */
		public static double distance(double lat1, double lat2, double lon1,
		        double lon2) {
	//		, double el1, double el2
		    final int R = 6371; // Radius of the earth
	
		    Double latDistance = Math.toRadians(lat2 - lat1);
		    Double lonDistance = Math.toRadians(lon2 - lon1);
		    Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
		            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
		            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		    Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		    double distance = R * c ; // convert to meters
//		    * 1000
	//	    double height = el1 - el2;
	
	//	    distance = Math.pow(distance, 2) + Math.pow(height, 2);
	
		    return Math.sqrt(distance);
		}

	public static boolean filtraDistancia(Lugar lugar,Double x, Double y , Double radio) {
	//		Double distance = Math.sqrt(Math.pow((lugar.getX()-x), 2) + Math.pow((lugar.getY()-y), 2));
			Double distance = distance(lugar.getX(),x,lugar.getY(),y);
			if (distance <= radio) {
				return true;
			}
			return false;
		}
	
	public static String objectToJson(Object o){
    	Gson gsonInstance = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();

    	return gsonInstance.toJson(o) ;
	}

}
