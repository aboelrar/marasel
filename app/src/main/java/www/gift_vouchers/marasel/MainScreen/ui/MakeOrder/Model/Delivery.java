package www.gift_vouchers.marasel.MainScreen.ui.MakeOrder.Model;//
//  Delivery.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on December 30, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Delivery{

	@SerializedName("availableOrdersCount")
	private int availableOrdersCount;
	@SerializedName("bank_number")
	private String bankNumber;
	@SerializedName("bank_type")
	private String bankType;
	@SerializedName("car_back_image")
	private String carBackImage;
	@SerializedName("car_front_image")
	private String carFrontImage;
	@SerializedName("id")
	private int id;
	@SerializedName("id_image")
	private String idImage;
	@SerializedName("license_image")
	private String licenseImage;
	@SerializedName("rate")
	private int rate;
	@SerializedName("rates")
	private Object[] rates;

	public void setAvailableOrdersCount(int availableOrdersCount){
		this.availableOrdersCount = availableOrdersCount;
	}
	public int getAvailableOrdersCount(){
		return this.availableOrdersCount;
	}
	public void setBankNumber(String bankNumber){
		this.bankNumber = bankNumber;
	}
	public String getBankNumber(){
		return this.bankNumber;
	}
	public void setBankType(String bankType){
		this.bankType = bankType;
	}
	public String getBankType(){
		return this.bankType;
	}
	public void setCarBackImage(String carBackImage){
		this.carBackImage = carBackImage;
	}
	public String getCarBackImage(){
		return this.carBackImage;
	}
	public void setCarFrontImage(String carFrontImage){
		this.carFrontImage = carFrontImage;
	}
	public String getCarFrontImage(){
		return this.carFrontImage;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setIdImage(String idImage){
		this.idImage = idImage;
	}
	public String getIdImage(){
		return this.idImage;
	}
	public void setLicenseImage(String licenseImage){
		this.licenseImage = licenseImage;
	}
	public String getLicenseImage(){
		return this.licenseImage;
	}
	public void setRate(int rate){
		this.rate = rate;
	}
	public int getRate(){
		return this.rate;
	}
	public void setRates(Object[] rates){
		this.rates = rates;
	}
	public Object[] getRates(){
		return this.rates;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Delivery(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		bankNumber = jsonObject.optString("bank_number");
		bankType = jsonObject.optString("bank_type");
		carBackImage = jsonObject.optString("car_back_image");
		carFrontImage = jsonObject.optString("car_front_image");
		idImage = jsonObject.optString("id_image");
		licenseImage = jsonObject.optString("license_image");
		availableOrdersCount = jsonObject.optInt("availableOrdersCount");
		id = jsonObject.optInt("id");
		rate = jsonObject.optInt("rate");
		//rates = jsonObject.optObject[]("rates");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("availableOrdersCount", availableOrdersCount);
			jsonObject.put("bank_number", bankNumber);
			jsonObject.put("bank_type", bankType);
			jsonObject.put("car_back_image", carBackImage);
			jsonObject.put("car_front_image", carFrontImage);
			jsonObject.put("id", id);
			jsonObject.put("id_image", idImage);
			jsonObject.put("license_image", licenseImage);
			jsonObject.put("rate", rate);
			jsonObject.put("rates", rates);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}