package www.gift_vouchers.marasel.MainScreen.ui.MyOrder.Model;//
//  Category.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 3, 2021

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Category{

	@SerializedName("icon")
	private String icon;
	@SerializedName("id")
	private int id;
	@SerializedName("name")
	private String name;

	public void setIcon(String icon){
		this.icon = icon;
	}
	public String getIcon(){
		return this.icon;
	}
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
	public Category(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		icon = jsonObject.optString("icon");
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
			jsonObject.put("icon", icon);
			jsonObject.put("id", id);
			jsonObject.put("name", name);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}