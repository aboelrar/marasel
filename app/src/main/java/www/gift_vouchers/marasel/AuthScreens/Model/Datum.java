package www.gift_vouchers.marasel.AuthScreens.Model;//
//  Datum.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on December 8, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Datum{

	@SerializedName("balance")
	private String balance;
	@SerializedName("delivery")
	private Object delivery;
	@SerializedName("delivery_mode")
	private int deliveryMode;
	@SerializedName("delivery_status")
	private int deliveryStatus;
	@SerializedName("email")
	private String email;
	@SerializedName("gender")
	private int gender;
	@SerializedName("id")
	private int id;
	@SerializedName("image")
	private String image;
	@SerializedName("lang")
	private String lang;
	@SerializedName("name")
	private String name;
	@SerializedName("phone")
	private String phone;
	@SerializedName("social")
	private int social;
	@SerializedName("status")
	private int status;
	@SerializedName("token")
	private String token;
	@SerializedName("user_type")
	private int userType;

	public void setBalance(String balance){
		this.balance = balance;
	}
	public String getBalance(){
		return this.balance;
	}
	public void setDelivery(Object delivery){
		this.delivery = delivery;
	}
	public Object getDelivery(){
		return this.delivery;
	}
	public void setDeliveryMode(int deliveryMode){
		this.deliveryMode = deliveryMode;
	}
	public int getDeliveryMode(){
		return this.deliveryMode;
	}
	public void setDeliveryStatus(int deliveryStatus){
		this.deliveryStatus = deliveryStatus;
	}
	public int getDeliveryStatus(){
		return this.deliveryStatus;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public String getEmail(){
		return this.email;
	}
	public void setGender(int gender){
		this.gender = gender;
	}
	public int getGender(){
		return this.gender;
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
	public void setLang(String lang){
		this.lang = lang;
	}
	public String getLang(){
		return this.lang;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}
	public String getPhone(){
		return this.phone;
	}
	public void setSocial(int social){
		this.social = social;
	}
	public int getSocial(){
		return this.social;
	}
	public void setStatus(int status){
		this.status = status;
	}
	public int getStatus(){
		return this.status;
	}
	public void setToken(String token){
		this.token = token;
	}
	public String getToken(){
		return this.token;
	}
	public void setUserType(int userType){
		this.userType = userType;
	}
	public int getUserType(){
		return this.userType;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Datum(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		balance = jsonObject.optString("balance");
		email = jsonObject.optString("email");
		image = jsonObject.optString("image");
		lang = jsonObject.optString("lang");
		name = jsonObject.optString("name");
		phone = jsonObject.optString("phone");
		token = jsonObject.optString("token");
		delivery = jsonObject.optString("delivery");
		deliveryMode = jsonObject.optInt("delivery_mode");
		deliveryStatus = jsonObject.optInt("delivery_status");
		gender = jsonObject.optInt("gender");
		id = jsonObject.optInt("id");
		social = jsonObject.optInt("social");
		status = jsonObject.optInt("status");
		userType = jsonObject.optInt("user_type");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("balance", balance);
			jsonObject.put("delivery", delivery);
			jsonObject.put("delivery_mode", deliveryMode);
			jsonObject.put("delivery_status", deliveryStatus);
			jsonObject.put("email", email);
			jsonObject.put("gender", gender);
			jsonObject.put("id", id);
			jsonObject.put("image", image);
			jsonObject.put("lang", lang);
			jsonObject.put("name", name);
			jsonObject.put("phone", phone);
			jsonObject.put("social", social);
			jsonObject.put("status", status);
			jsonObject.put("token", token);
			jsonObject.put("user_type", userType);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}