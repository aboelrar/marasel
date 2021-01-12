package www.gift_vouchers.marasel.Drivers.UI.AddOffer.Model;//
//  Image.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 12, 2021

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Image{

	@SerializedName("id")
	private int id;
	@SerializedName("image")
	private String image;

	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setImage(String image){
		this.image = image;
	}
	public String getImage(){
		return this.image;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Image(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		image = jsonObject.optString("image");
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
			jsonObject.put("image", image);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}