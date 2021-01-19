package www.gift_vouchers.marasel.Drivers.UI.MyOffers.Model;//
//  Rate.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 19, 2021

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Rate{

	@SerializedName("comment")
	private String comment;
	@SerializedName("created_at")
	private String createdAt;
	@SerializedName("id")
	private int id;
	@SerializedName("image")
	private String image;
	@SerializedName("name")
	private String name;
	@SerializedName("rate")
	private int rate;

	public void setComment(String comment){
		this.comment = comment;
	}
	public String getComment(){
		return this.comment;
	}
	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}
	public String getCreatedAt(){
		return this.createdAt;
	}
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
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setRate(int rate){
		this.rate = rate;
	}
	public int getRate(){
		return this.rate;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Rate(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		comment = jsonObject.optString("comment");
		createdAt = jsonObject.optString("created_at");
		image = jsonObject.optString("image");
		name = jsonObject.optString("name");
		id = jsonObject.optInt("id");
		rate = jsonObject.optInt("rate");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("comment", comment);
			jsonObject.put("created_at", createdAt);
			jsonObject.put("id", id);
			jsonObject.put("image", image);
			jsonObject.put("name", name);
			jsonObject.put("rate", rate);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}