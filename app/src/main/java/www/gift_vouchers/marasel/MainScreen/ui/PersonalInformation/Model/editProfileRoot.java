package www.gift_vouchers.marasel.MainScreen.ui.PersonalInformation.Model;//
//  editProfileRoot.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 23, 2021

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class editProfileRoot{

	@SerializedName("data")
	private Datum data;
	@SerializedName("message")
	private String message;
	@SerializedName("status")
	private int status;

	public void setData(Datum data){
		this.data = data;
	}
	public Datum getData(){
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
	public editProfileRoot(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		message = jsonObject.optString("message");
		status = jsonObject.optInt("status");
		data = new Datum(jsonObject.optJSONObject("data"));
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("message", message);
			jsonObject.put("status", status);
			jsonObject.put("data", data.toJsonObject());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}