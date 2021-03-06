package www.gift_vouchers.marasel.Drivers.UI.AddOffer.Model;//
//  Driver.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 12, 2021

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Driver{

	@SerializedName("balance")
	private String balance;
	@SerializedName("delivery")
	private Delivery delivery;
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
	@SerializedName("ordersCounts")
	private int ordersCounts;
	@SerializedName("phone")
	private String phone;
	@SerializedName("ratesCounts")
	private int ratesCounts;
	@SerializedName("social")
	private int social;
	@SerializedName("status")
	private int status;
	@SerializedName("token")
	private Object token;

	public void setBalance(String balance){
		this.balance = balance;
	}
	public String getBalance(){
		return this.balance;
	}
	public void setDelivery(Delivery delivery){
		this.delivery = delivery;
	}
	public Delivery getDelivery(){
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
	public void setOrdersCounts(int ordersCounts){
		this.ordersCounts = ordersCounts;
	}
	public int getOrdersCounts(){
		return this.ordersCounts;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}
	public String getPhone(){
		return this.phone;
	}
	public void setRatesCounts(int ratesCounts){
		this.ratesCounts = ratesCounts;
	}
	public int getRatesCounts(){
		return this.ratesCounts;
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
	public void setToken(Object token){
		this.token = token;
	}
	public Object getToken(){
		return this.token;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Driver(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		balance = jsonObject.optString("balance");
		email = jsonObject.optString("email");
		image = jsonObject.optString("image");
		lang = jsonObject.optString("lang");
		name = jsonObject.optString("name");
		phone = jsonObject.optString("phone");
		deliveryMode = jsonObject.optInt("delivery_mode");
		deliveryStatus = jsonObject.optInt("delivery_status");
		gender = jsonObject.optInt("gender");
		id = jsonObject.optInt("id");
		ordersCounts = jsonObject.optInt("ordersCounts");
		ratesCounts = jsonObject.optInt("ratesCounts");
		social = jsonObject.optInt("social");
		status = jsonObject.optInt("status");
		token = jsonObject.optString("token");
		delivery = new Delivery(jsonObject.optJSONObject("delivery"));
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("balance", balance);
			jsonObject.put("delivery_mode", deliveryMode);
			jsonObject.put("delivery_status", deliveryStatus);
			jsonObject.put("email", email);
			jsonObject.put("gender", gender);
			jsonObject.put("id", id);
			jsonObject.put("image", image);
			jsonObject.put("lang", lang);
			jsonObject.put("name", name);
			jsonObject.put("ordersCounts", ordersCounts);
			jsonObject.put("phone", phone);
			jsonObject.put("ratesCounts", ratesCounts);
			jsonObject.put("social", social);
			jsonObject.put("status", status);
			jsonObject.put("token", token);
			jsonObject.put("delivery", delivery.toJsonObject());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}