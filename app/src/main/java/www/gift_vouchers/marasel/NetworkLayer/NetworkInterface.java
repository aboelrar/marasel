package www.gift_vouchers.marasel.NetworkLayer;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;
import www.gift_vouchers.marasel.AuthScreens.Model.AuthRoot;

public interface NetworkInterface {

    @POST("Auth_general/login")
    Call<AuthRoot> login(
            @Query("emailOrphone") String emailOrPhone,
            @Query("password") String password,
            @Query("social") String social
    );
}
