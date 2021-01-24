package www.gift_vouchers.marasel.utils;

import android.content.Context;

import java.util.ArrayList;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import www.gift_vouchers.marasel.AuthScreens.Model.Datum;
import www.gift_vouchers.marasel.local_data.send_data;

public class UserInformation {

    /**
     * ADD DATA TO LOCAL
     */
    public static void addLocalData(Datum datum, Context context) {
        //ADD USER DATA IN ARRAY LIST
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(datum.getName());
        arrayList.add(datum.getEmail());
        arrayList.add(datum.getPhone());
        arrayList.add(datum.getToken());
        arrayList.add("" + datum.getSocial());
        arrayList.add("" + datum.getGender());


        Observable.fromArray(arrayList).
                observeOn(Schedulers.computation()).subscribe(new Consumer<ArrayList<String>>() {
            @Override
            public void accept(ArrayList<String> arrayList) throws Throwable {

                //GET DATA FROM OBSERVABLE AND ADDED IN LOCAL DATA
                send_data.send_name(context, arrayList.get(0)); //ADD USER NAME
                send_data.send_email(context, arrayList.get(1)); //ADD Email
                send_data.send_phone(context, arrayList.get(2)); //ADD PHONE
                send_data.send_token(context, arrayList.get(3)); //ADD TOKEN
                send_data.send_type(context, arrayList.get(4)); //ADD TYPE
                send_data.set_user_gender(context, arrayList.get(5)); //ADD GENDER

            }
        });
    }
}
