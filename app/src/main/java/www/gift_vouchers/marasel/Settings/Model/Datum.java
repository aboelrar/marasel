package www.gift_vouchers.marasel.Settings.Model;//
//  Datum.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 30, 2021

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Datum{

	@SerializedName("about")
	private String about;
	@SerializedName("facebook")
	private String facebook;
	@SerializedName("howUse")
	private String howUse;
	@SerializedName("howWork")
	private String howWork;
	@SerializedName("id")
	private int id;
	@SerializedName("instagram")
	private String instagram;
	@SerializedName("policy")
	private String policy;
	@SerializedName("telegram")
	private String telegram;
	@SerializedName("twitter")
	private String twitter;

	public void setAbout(String about){
		this.about = about;
	}
	public String getAbout(){
		return this.about;
	}
	public void setFacebook(String facebook){
		this.facebook = facebook;
	}
	public String getFacebook(){
		return this.facebook;
	}
	public void setHowUse(String howUse){
		this.howUse = howUse;
	}
	public String getHowUse(){
		return this.howUse;
	}
	public void setHowWork(String howWork){
		this.howWork = howWork;
	}
	public String getHowWork(){
		return this.howWork;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setInstagram(String instagram){
		this.instagram = instagram;
	}
	public String getInstagram(){
		return this.instagram;
	}
	public void setPolicy(String policy){
		this.policy = policy;
	}
	public String getPolicy(){
		return this.policy;
	}
	public void setTelegram(String telegram){
		this.telegram = telegram;
	}
	public String getTelegram(){
		return this.telegram;
	}
	public void setTwitter(String twitter){
		this.twitter = twitter;
	}
	public String getTwitter(){
		return this.twitter;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Datum(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		about = jsonObject.optString("about");
		facebook = jsonObject.optString("facebook");
		howUse = jsonObject.optString("howUse");
		howWork = jsonObject.optString("howWork");
		instagram = jsonObject.optString("instagram");
		policy = jsonObject.optString("policy");
		telegram = jsonObject.optString("telegram");
		twitter = jsonObject.optString("twitter");
		id = jsonObject.optInt("id");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("about", about);
			jsonObject.put("facebook", facebook);
			jsonObject.put("howUse", howUse);
			jsonObject.put("howWork", howWork);
			jsonObject.put("id", id);
			jsonObject.put("instagram", instagram);
			jsonObject.put("policy", policy);
			jsonObject.put("telegram", telegram);
			jsonObject.put("twitter", twitter);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}