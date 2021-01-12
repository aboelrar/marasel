package www.gift_vouchers.marasel.Drivers.UI.AddOffer.Model;//
//  DiscountCode.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 12, 2021

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class DiscountCode{

	@SerializedName("amount")
	private String amount;
	@SerializedName("code")
	private String code;
	@SerializedName("code_type")
	private int codeType;
	@SerializedName("desc")
	private String desc;
	@SerializedName("id")
	private int id;
	@SerializedName("is_use")
	private Object isUse;
	@SerializedName("logo")
	private String logo;
	@SerializedName("name")
	private String name;
	@SerializedName("store")
	private String store;

	public void setAmount(String amount){
		this.amount = amount;
	}
	public String getAmount(){
		return this.amount;
	}
	public void setCode(String code){
		this.code = code;
	}
	public String getCode(){
		return this.code;
	}
	public void setCodeType(int codeType){
		this.codeType = codeType;
	}
	public int getCodeType(){
		return this.codeType;
	}
	public void setDesc(String desc){
		this.desc = desc;
	}
	public String getDesc(){
		return this.desc;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setIsUse(Object isUse){
		this.isUse = isUse;
	}
	public Object getIsUse(){
		return this.isUse;
	}
	public void setLogo(String logo){
		this.logo = logo;
	}
	public String getLogo(){
		return this.logo;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setStore(String store){
		this.store = store;
	}
	public String getStore(){
		return this.store;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public DiscountCode(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		amount = jsonObject.optString("amount");
		code = jsonObject.optString("code");
		desc = jsonObject.optString("desc");
		logo = jsonObject.optString("logo");
		name = jsonObject.optString("name");
		store = jsonObject.optString("store");
		codeType = jsonObject.optInt("code_type");
		id = jsonObject.optInt("id");
		isUse = jsonObject.optString("is_use");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("amount", amount);
			jsonObject.put("code", code);
			jsonObject.put("code_type", codeType);
			jsonObject.put("desc", desc);
			jsonObject.put("id", id);
			jsonObject.put("is_use", isUse);
			jsonObject.put("logo", logo);
			jsonObject.put("name", name);
			jsonObject.put("store", store);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}