package www.gift_vouchers.marasel.NetworkLayer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import www.gift_vouchers.marasel.AuthScreens.Model.AuthRoot;
import www.gift_vouchers.marasel.MainScreen.ui.Categories.model.StoreByService;
import www.gift_vouchers.marasel.MainScreen.ui.home.model.homeRoot;

public interface NetworkInterface {

    @POST("Auth_general/login")
    Call<AuthRoot> login(
            @Query("emailOrphone") String emailOrPhone,
            @Query("password") String password,
            @Query("social") String social
    );

    @POST("Auth_general/register")
    Call<AuthRoot> signUp (
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
}
