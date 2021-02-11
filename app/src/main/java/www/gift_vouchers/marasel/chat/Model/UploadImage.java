package www.gift_vouchers.marasel.chat.Model;//
//  UploadImage.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on February 6, 2021

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class UploadImage{

	@SerializedName("data")
	private String data;
	@SerializedName("message")
	private String message;
	@SerializedName("status")
	private int status;

	public void setData(String data){
		this.data = data;
	}
	public String getData(){
		return this.data;
	}
	public void setMessage(String message){
		this.message = message;
	}
	public String getMessage(){
		return this.message;
	}
	public void setStatus(int status){
		this.status = status;
	}
	public int getStatus(){
		return this.status;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public UploadImage(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		data = jsonObject.optString("data");
		message = jsonObject.optString("message");
		status = jsonObject.optInt("status");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("data", data);
			jsonObject.put("message", message);
			jsonObject.put("status", status);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}