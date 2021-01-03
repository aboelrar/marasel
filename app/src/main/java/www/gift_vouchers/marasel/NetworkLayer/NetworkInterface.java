package www.gift_vouchers.marasel.NetworkLayer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import www.gift_vouchers.marasel.AuthScreens.Model.AuthRoot;
import www.gift_vouchers.marasel.MainScreen.ui.Cart.Model.CartRoot;
import www.gift_vouchers.marasel.MainScreen.ui.Categories.model.StoreByService;
import www.gift_vouchers.marasel.MainScreen.ui.MakeOrder.Model.DeliveryPlace;
import www.gift_vouchers.marasel.MainScreen.ui.MakeOrder.Model.MakeOrder;
import www.gift_vouchers.marasel.MainScreen.ui.MyOrder.Model.MyOrderRoot;
import www.gift_vouchers.marasel.MainScreen.ui.Product.Model.ProductsByCat;
import www.gift_vouchers.marasel.MainScreen.ui.ProductDetails.Model.AddToCartRoot;
import www.gift_vouchers.marasel.MainScreen.ui.ProductDetails.Model.SingleProduct;
import www.gift_vouchers.marasel.MainScreen.ui.RateStore.Model.RateStoreRoot;
import www.gift_vouchers.marasel.MainScreen.ui.Store.model.SingleStore;
import www.gift_vouchers.marasel.MainScreen.ui.home.model.homeRoot;

public interface NetworkInterface {

    @POST("Auth_general/login")
    Call<AuthRoot> login(
            @Query("emailOrphone") String emailOrPhone,
            @Query("password") String password,
            @Query("social") String social
    );

    @POST("Auth_general/register")
    Call<AuthRoot> signUp(
            @Query("phone") String phone,
            @Query("email") String email,
            @Query("social") String social,
            @Query("password") String password,
            @Query("user_type") String user_type
    );

    @GET("Home/home?lat=30.109760&lng=31.247240")
    Call<homeRoot> home(
            @Header("Authorization") String authorization
    );

    @GET("Home/store_by_service/{id}")
    Call<StoreByService> StoreByService(
            @Header("Authorization") String authorization,
            @Path("id") String id,
            @Query("page") String page
    );

    @GET("Store/single_store/{store_id}")
    Call<SingleStore> SingleStore(
            @Header("Authorization") String authorization,
            @Path("store_id") String store_id
    );

    @GET("Store/products_by_cat")
    Call<ProductsByCat> ProductsByCat(
            @Header("Authorization") String authorization,
            @Query("store_id") String store_id,
            @Query("cat_id") String cat_id
    );

    @GET("Store/single_product/{product_id}")
    Call<SingleProduct> SingleProduct(
            @Header("Authorization") String authorization,
            @Path("product_id") String product_id
    );

    @POST("Order/add_to_cart")
    Call<AddToCartRoot> addToCart(
            @Header("Authorization") String authorization,
            @Query("product_id") String product_id,
            @Query("quantity") String quantity
    );

    @GET("Order/my_cart")
    Call<CartRoot> myCart(
            @Header("Authorization") String authorization
    );

    @GET("Order/orderTimes")
    Call<DeliveryPlace> orderTimes(
            @Header("Authorization") String authorization
    );

    @POST("Order/make_order")
    Call<MakeOrder> makeOrder(
            @Header("Authorization") String authorization,
            @Query("lat") String lat,
            @Query("lng") String lng,
            @Query("time_id") String time_id,
            @Query("payment_method") String payment_method,
            @Query("suggest_shipping_price") String suggest_shipping_price,
            @Query("address") String address,
            @Query("note") String note
    );

    @POST("Order/rate_store/{id}")
    Call<RateStoreRoot> rateStore(
            @Header("Authorization") String authorization,
            @Path("id") String id,
            @Query("rate") int rate
    );

    @GET("Order/myOrders")
    Call<MyOrderRoot> myOrders(
            @Header("Authorization") String authorization
    );


}
