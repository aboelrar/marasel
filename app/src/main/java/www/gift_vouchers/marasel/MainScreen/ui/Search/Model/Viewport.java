package www.gift_vouchers.marasel.MainScreen.ui.Search.Model;//
//  Viewport.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 30, 2021

import org.json.*;

import com.google.gson.annotations.SerializedName;


public class Viewport{

	@SerializedName("northeast")
	private Northeast northeast;
	@SerializedName("southwest")
	private Southwest southwest;

	public void setNortheast(Northeast northeast){
		this.northeast = northeast;
	}
	public Northeast getNortheast(){
		return this.northeast;
	}
	public void setSouthwest(Southwest southwest){
		this.southwest = southwest;
	}
	public Southwest getSouthwest(){
		return this.southwest;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Viewport(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		northeast = new Northeast(jsonObject.optJSONObject("northeast"));
		southwest = new Southwest(jsonObject.optJSONObject("southwest"));
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("northeast", northeast.toJsonObject());
			jsonObject.put("southwest", southwest.toJsonObject());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}