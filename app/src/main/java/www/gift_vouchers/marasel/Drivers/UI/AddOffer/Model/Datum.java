package www.gift_vouchers.marasel.Drivers.UI.AddOffer.Model;//
//  Datum.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 12, 2021

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Datum{

	@SerializedName("driver")
	private Driver driver;
	@SerializedName("id")
	private int id;
	@SerializedName("note")
	private String note;
	@SerializedName("price")
	private String price;
	@SerializedName("time")
	private int time;
	@SerializedName("time_type")
	private String timeType;

	public void setDriver(Driver driver){
		this.driver = driver;
	}
	public Driver getDriver(){
		return this.driver;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setNote(String note){
		this.note = note;
	}
	public String getNote(){
		return this.note;
	}
	public void setPrice(String price){
		this.price = price;
	}
	public String getPrice(){
		return this.price;
	}
	public void setTime(int time){
		this.time = time;
	}
	public int getTime(){
		return this.time;
	}
	public void setTimeType(String timeType){
		this.timeType = timeType;
	}
	public String getTimeType(){
		return this.timeType;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Datum(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		note = jsonObject.optString("note");
		price = jsonObject.optString("price");
		timeType = jsonObject.optString("time_type");
		id = jsonObject.optInt("id");
		time = jsonObject.optInt("time");
		driver = new Driver(jsonObject.optJSONObject("driver"));
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("id", id);
			jsonObject.put("note", note);
			jsonObject.put("price", price);
			jsonObject.put("time", time);
			jsonObject.put("time_type", timeType);
			jsonObject.put("driver", driver.toJsonObject());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}