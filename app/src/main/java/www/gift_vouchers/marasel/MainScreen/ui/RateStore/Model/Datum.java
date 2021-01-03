package www.gift_vouchers.marasel.MainScreen.ui.RateStore.Model;//
//  Datum.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 2, 2021

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Datum{

	@SerializedName("rate")
	private String rate;

	public void setRate(String rate){
		this.rate = rate;
	}
	public String getRate(){
		return this.rate;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Datum(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		rate = jsonObject.optString("rate");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("rate", rate);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}