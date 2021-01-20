package www.gift_vouchers.marasel.MainScreen.ui.Offers.Model;//
//  Delivery.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 20, 2021

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
	@SerializedName("carFormImage")
	private String carFormImage;
	@SerializedName("countOfRate")
	private int countOfRate;
	@SerializedName("id")
	private int id;
	@SerializedName("id_image")
	private String idImage;
	@SerializedName("image")
	private String image;
	@SerializedName("lat")
	private String lat;
	@SerializedName("license_image")
	private String licenseImage;
	@SerializedName("lng")
	private String lng;
	@SerializedName("name")
	private String name;
	@SerializedName("ordersCount")
	private int ordersCount;
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
	public void setCarFormImage(String carFormImage){
		this.carFormImage = carFormImage;
	}
	public String getCarFormImage(){
		return this.carFormImage;
	}
	public void setCountOfRate(int countOfRate){
		this.countOfRate = countOfRate;
	}
	public int getCountOfRate(){
		return this.countOfRate;
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
	public void setImage(String image){
		this.image = image;
	}
	public String getImage(){
		return this.image;
	}
	public void setLat(String lat){
		this.lat = lat;
	}
	public String getLat(){
		return this.lat;
	}
	public void setLicenseImage(String licenseImage){
		this.licenseImage = licenseImage;
	}
	public String getLicenseImage(){
		return this.licenseImage;
	}
	public void setLng(String lng){
		this.lng = lng;
	}
	public String getLng(){
		return this.lng;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setOrdersCount(int ordersCount){
		this.ordersCount = ordersCount;
	}
	public int getOrdersCount(){
		return this.ordersCount;
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
		carFormImage = jsonObject.optString("carFormImage");
		idImage = jsonObject.optString("id_image");
		image = jsonObject.optString("image");
		lat = jsonObject.optString("lat");
		licenseImage = jsonObject.optString("license_image");
		lng = jsonObject.optString("lng");
		name = jsonObject.optString("name");
		availableOrdersCount = jsonObject.optInt("availableOrdersCount");
		countOfRate = jsonObject.optInt("countOfRate");
		id = jsonObject.optInt("id");
		ordersCount = jsonObject.optInt("ordersCount");
		rate = jsonObject.optInt("rate");
//		rates = jsonObject.optObject[]("rates");
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
			jsonObject.put("carFormImage", carFormImage);
			jsonObject.put("countOfRate", countOfRate);
			jsonObject.put("id", id);
			jsonObject.put("id_image", idImage);
			jsonObject.put("image", image);
			jsonObject.put("lat", lat);
			jsonObject.put("license_image", licenseImage);
			jsonObject.put("lng", lng);
			jsonObject.put("name", name);
			jsonObject.put("ordersCount", ordersCount);
			jsonObject.put("rate", rate);
			jsonObject.put("rates", rates);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}