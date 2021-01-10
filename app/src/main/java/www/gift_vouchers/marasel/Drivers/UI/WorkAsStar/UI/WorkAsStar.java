package www.gift_vouchers.marasel.Drivers.UI.WorkAsStar.UI;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import es.dmoral.toasty.Toasty;
import www.gift_vouchers.marasel.Drivers.UI.DeliveryPersonalInfo.UI.DeliveryPersonalInfo;
import www.gift_vouchers.marasel.Drivers.UI.DriverInfo.UI.DriverInfo;
import www.gift_vouchers.marasel.Drivers.UI.DriverInfo.UI.DriverInfoAccept;
import www.gift_vouchers.marasel.Drivers.UI.WorkAsStar.Model.ActiveDriverRoot;
import www.gift_vouchers.marasel.Drivers.UI.WorkAsStar.Model.Datum;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.WorkAsStarBinding;
import www.gift_vouchers.marasel.local_data.saved_data;
import www.gift_vouchers.marasel.local_data.send_data;
import www.gift_vouchers.marasel.utils.utils;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class WorkAsStar extends Fragment implements CompoundButton.OnCheckedChangeListener {
    WorkAsStarBinding binding;
    WorkAsStarModelView WorkAsStarModelView;
    Datum datum;

    public WorkAsStar() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.work_as_star, container, false);
        View view = binding.getRoot();

        //DEFINE MODEL VIEW
        WorkAsStarModelView = new WorkAsStarModelView();

        //CALL API TO CHECK FOR DRIVER
        checkSwitched();


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.switcher.setOnCheckedChangeListener(this);
        getData();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked == true) {
            send_data.checkActiveStar(getContext(), true); //SET CHECKED TRUE

            //CALL API
            WorkAsStarModelView.getData("Bearer " + new saved_data().get_token(getContext()));
            binding.progressCircular.setVisibility(View.VISIBLE);
        }
    }

    void getData() {
        WorkAsStarModelView.MutableLiveData.observe(this, new Observer<ActiveDriverRoot>() {
            @Override
            public void onChanged(ActiveDriverRoot activeDriverRoot) {
                datum = activeDriverRoot.getData();
                driverStatus();
            }
        });
    }

    //CHECK IF SWITCHED BEFORE OR NOT
    void checkSwitched() {
        if (new saved_data().getCheckActiveStar(getContext()) == true) {
            WorkAsStarModelView.getData("Bearer " + new saved_data().get_token(getContext()));
            binding.progressCircular.setVisibility(View.VISIBLE);
        }
    }

    //RESPONSE FROM SERVER FOR DRIVER
    void driverStatus() {
        binding.progressCircular.setVisibility(View.GONE); //SET PROGRESS DIALOG GONE

        if (datum.getDeliveryMode() == 1) {
            if (datum.getDeliveryStatus() == 0) {
                binding.switcher.setChecked(false);

                new utils().Replace_Fragment(new DriverInfo(), R.id.frag, getContext());
            } else if (datum.getDeliveryStatus() == 1) {
                new utils().Replace_Fragment(new DeliveryPersonalInfo(), R.id.frag, getContext());
            } else if (datum.getDeliveryStatus() == 2) {

                binding.switcher.setChecked(true); //SET SWITCHER TRUE

                DriverInfoAccept driverInfoAccept = new DriverInfoAccept();
                driverInfoAccept.dialog(getContext(), R.layout.driver_info_accept, .90);


            }
        }
    }
}