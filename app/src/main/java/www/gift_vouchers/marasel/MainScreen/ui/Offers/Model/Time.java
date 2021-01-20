package www.gift_vouchers.marasel.MainScreen.ui.Offers.Model;//
//  Time.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 20, 2021

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Time{

	@SerializedName("id")
	private int id;
	@SerializedName("name")
	private String name;

	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Time(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		name = jsonObject.optString("name");
		id = jsonObject.optInt("id");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("id", id);
			jsonObject.put("name", name);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}