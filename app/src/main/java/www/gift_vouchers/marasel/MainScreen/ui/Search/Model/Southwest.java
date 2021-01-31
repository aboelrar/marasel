package www.gift_vouchers.marasel.MainScreen.ui.Search.Model;//
//  Southwest.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 30, 2021

import org.json.*;

import com.google.gson.annotations.SerializedName;


public class Southwest{

	@SerializedName("lat")
	private double lat;
	@SerializedName("lng")
	private double lng;

	public void setLat(double lat){
		this.lat = lat;
	}
	public double getLat(){
		return this.lat;
	}
	public void setLng(double lng){
		this.lng = lng;
	}
	public double getLng(){
		return this.lng;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Southwest(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		lat = jsonObject.optDouble("lat");
		lng = jsonObject.optDouble("lng");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("lat", lat);
			jsonObject.put("lng", lng);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}