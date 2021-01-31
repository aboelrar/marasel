package www.gift_vouchers.marasel.MainScreen.ui.Search.Model;//
//  PlusCode.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 30, 2021

import org.json.*;

import com.google.gson.annotations.SerializedName;


public class PlusCode{

	@SerializedName("compound_code")
	private String compoundCode;
	@SerializedName("global_code")
	private String globalCode;

	public void setCompoundCode(String compoundCode){
		this.compoundCode = compoundCode;
	}
	public String getCompoundCode(){
		return this.compoundCode;
	}
	public void setGlobalCode(String globalCode){
		this.globalCode = globalCode;
	}
	public String getGlobalCode(){
		return this.globalCode;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public PlusCode(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		compoundCode = jsonObject.optString("compound_code");
		globalCode = jsonObject.optString("global_code");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("compound_code", compoundCode);
			jsonObject.put("global_code", globalCode);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}