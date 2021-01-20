package www.gift_vouchers.marasel.MainScreen.ui.Offers.Model;//
//  Order.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on January 20, 2021

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class Order{

	@SerializedName("acceptedOffer")
	private Object acceptedOffer;
	@SerializedName("address")
	private String address;
	@SerializedName("client")
	private Client client;
	@SerializedName("discount_price")
	private String discountPrice;
	@SerializedName("discountCode")
	private DiscountCode discountCode;
	@SerializedName("id")
	private int id;
	@SerializedName("is_offer")
	private boolean isOffer;
	@SerializedName("lat")
	private String lat;
	@SerializedName("lng")
	private String lng;
	@SerializedName("maraseelShippingPrices")
	private int[] maraseelShippingPrices;
	@SerializedName("note")
	private String note;
	@SerializedName("payment_method")
	private int paymentMethod;
	@SerializedName("product_price")
	private String productPrice;
	@SerializedName("products")
	private Product[] products;
	@SerializedName("rejectedReason")
	private Object rejectedReason;
	@SerializedName("shipping_price")
	private String shippingPrice;
	@SerializedName("status")
	private int status;
	@SerializedName("store")
	private Store store;
	@SerializedName("storeLat")
	private int storeLat;
	@SerializedName("storeLng")
	private int storeLng;
	@SerializedName("storeName")
	private int storeName;
	@SerializedName("suggest_shipping_price")
	private String suggestShippingPrice;
	@SerializedName("time")
	private Time time;
	@SerializedName("total_price")
	private String totalPrice;
	@SerializedName("type")
	private int type;

	public void setAcceptedOffer(Object acceptedOffer){
		this.acceptedOffer = acceptedOffer;
	}
	public Object getAcceptedOffer(){
		return this.acceptedOffer;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public String getAddress(){
		return this.address;
	}
	public void setClient(Client client){
		this.client = client;
	}
	public Client getClient(){
		return this.client;
	}
	public void setDiscountPrice(String discountPrice){
		this.discountPrice = discountPrice;
	}
	public String getDiscountPrice(){
		return this.discountPrice;
	}
	public void setDiscountCode(DiscountCode discountCode){
		this.discountCode = discountCode;
	}
	public DiscountCode getDiscountCode(){
		return this.discountCode;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setIsOffer(boolean isOffer){
		this.isOffer = isOffer;
	}
	public boolean isIsOffer(){
		return this.isOffer;
	}
	public void setLat(String lat){
		this.lat = lat;
	}
	public String getLat(){
		return this.lat;
	}
	public void setLng(String lng){
		this.lng = lng;
	}
	public String getLng(){
		return this.lng;
	}
	public void setMaraseelShippingPrices(int[] maraseelShippingPrices){
		this.maraseelShippingPrices = maraseelShippingPrices;
	}
	public int[] getMaraseelShippingPrices(){
		return this.maraseelShippingPrices;
	}
	public void setNote(String note){
		this.note = note;
	}
	public String getNote(){
		return this.note;
	}
	public void setPaymentMethod(int paymentMethod){
		this.paymentMethod = paymentMethod;
	}
	public int getPaymentMethod(){
		return this.paymentMethod;
	}
	public void setProductPrice(String productPrice){
		this.productPrice = productPrice;
	}
	public String getProductPrice(){
		return this.productPrice;
	}
	public void setProducts(Product[] products){
		this.products = products;
	}
	public Product[] getProducts(){
		return this.products;
	}
	public void setRejectedReason(Object rejectedReason){
		this.rejectedReason = rejectedReason;
	}
	public Object getRejectedReason(){
		return this.rejectedReason;
	}
	public void setShippingPrice(String shippingPrice){
		this.shippingPrice = shippingPrice;
	}
	public String getShippingPrice(){
		return this.shippingPrice;
	}
	public void setStatus(int status){
		this.status = status;
	}
	public int getStatus(){
		return this.status;
	}
	public void setStore(Store store){
		this.store = store;
	}
	public Store getStore(){
		return this.store;
	}
	public void setStoreLat(int storeLat){
		this.storeLat = storeLat;
	}
	public int getStoreLat(){
		return this.storeLat;
	}
	public void setStoreLng(int storeLng){
		this.storeLng = storeLng;
	}
	public int getStoreLng(){
		return this.storeLng;
	}
	public void setStoreName(int storeName){
		this.storeName = storeName;
	}
	public int getStoreName(){
		return this.storeName;
	}
	public void setSuggestShippingPrice(String suggestShippingPrice){
		this.suggestShippingPrice = suggestShippingPrice;
	}
	public String getSuggestShippingPrice(){
		return this.suggestShippingPrice;
	}
	public void setTime(Time time){
		this.time = time;
	}
	public Time getTime(){
		return this.time;
	}
	public void setTotalPrice(String totalPrice){
		this.totalPrice = totalPrice;
	}
	public String getTotalPrice(){
		return this.totalPrice;
	}
	public void setType(int type){
		this.type = type;
	}
	public int getType(){
		return this.type;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Order(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		address = jsonObject.optString("address");
		discountPrice = jsonObject.optString("discount_price");
		lat = jsonObject.optString("lat");
		lng = jsonObject.optString("lng");
		note = jsonObject.optString("note");
		productPrice = jsonObject.optString("product_price");
		shippingPrice = jsonObject.optString("shipping_price");
		suggestShippingPrice = jsonObject.optString("suggest_shipping_price");
		totalPrice = jsonObject.optString("total_price");
		acceptedOffer = jsonObject.optString("acceptedOffer");
		id = jsonObject.optInt("id");
		isOffer = jsonObject.optBoolean("is_offer");
		paymentMethod = jsonObject.optInt("payment_method");
		rejectedReason = jsonObject.optString("rejectedReason");
		status = jsonObject.optInt("status");
		storeLat = jsonObject.optInt("storeLat");
		storeLng = jsonObject.optInt("storeLng");
		storeName = jsonObject.optInt("storeName");
		type = jsonObject.optInt("type");
//		maraseelShippingPrices = jsonObject.optInt[]("maraseelShippingPrices");
		client = new Client(jsonObject.optJSONObject("client"));
		discountCode = new DiscountCode(jsonObject.optJSONObject("discountCode"));
		store = new Store(jsonObject.optJSONObject("store"));
		time = new Time(jsonObject.optJSONObject("time"));
		JSONArray productsJsonArray = jsonObject.optJSONArray("products");
		if(productsJsonArray != null){
			ArrayList<Product> productsArrayList = new ArrayList<>();
			for (int i = 0; i < productsJsonArray.length(); i++) {
				JSONObject productsObject = productsJsonArray.optJSONObject(i);
				productsArrayList.add(new Product(productsObject));
			}
			products = (Product[]) productsArrayList.toArray();
		}
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("acceptedOffer", acceptedOffer);
			jsonObject.put("address", address);
			jsonObject.put("discount_price", discountPrice);
			jsonObject.put("id", id);
			jsonObject.put("is_offer", isOffer);
			jsonObject.put("lat", lat);
			jsonObject.put("lng", lng);
			jsonObject.put("maraseelShippingPrices", maraseelShippingPrices);
			jsonObject.put("note", note);
			jsonObject.put("payment_method", paymentMethod);
			jsonObject.put("product_price", productPrice);
			jsonObject.put("rejectedReason", rejectedReason);
			jsonObject.put("shipping_price", shippingPrice);
			jsonObject.put("status", status);
			jsonObject.put("storeLat", storeLat);
			jsonObject.put("storeLng", storeLng);
			jsonObject.put("storeName", storeName);
			jsonObject.put("suggest_shipping_price", suggestShippingPrice);
			jsonObject.put("total_price", totalPrice);
			jsonObject.put("type", type);
			jsonObject.put("client", client.toJsonObject());
			jsonObject.put("discountCode", discountCode.toJsonObject());
			jsonObject.put("store", store.toJsonObject());
			jsonObject.put("time", time.toJsonObject());
			if(products != null && products.length > 0){
				JSONArray productsJsonArray = new JSONArray();
				for(Product productsElement : products){
					productsJsonArray.put(productsElement.toJsonObject());
				}
				jsonObject.put("products", productsJsonArray);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}