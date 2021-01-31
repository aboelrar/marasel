package www.gift_vouchers.marasel.MainScreen.ui.Search.Model;//
//  OpeningHour.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 30, 2021

import org.json.*;

import com.google.gson.annotations.SerializedName;


public class OpeningHour{

	@SerializedName("open_now")
	private boolean openNow;

	public void setOpenNow(boolean openNow){
		this.openNow = openNow;
	}
	public boolean isOpenNow(){
		return this.openNow;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public OpeningHour(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		openNow = jsonObject.optBoolean("open_now");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("open_now", openNow);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}