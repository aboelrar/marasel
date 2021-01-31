package www.gift_vouchers.marasel.MainScreen.ui.Search.Model;//
//  Geometry.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 30, 2021

import org.json.*;

import com.google.gson.annotations.SerializedName;


public class Geometry{

	@SerializedName("location")
	private Location location;
	@SerializedName("viewport")
	private Viewport viewport;

	public void setLocation(Location location){
		this.location = location;
	}
	public Location getLocation(){
		return this.location;
	}
	public void setViewport(Viewport viewport){
		this.viewport = viewport;
	}
	public Viewport getViewport(){
		return this.viewport;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Geometry(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		location = new Location(jsonObject.optJSONObject("location"));
		viewport = new Viewport(jsonObject.optJSONObject("viewport"));
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("location", location.toJsonObject());
			jsonObject.put("viewport", viewport.toJsonObject());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}