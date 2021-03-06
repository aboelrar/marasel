package www.gift_vouchers.marasel.Settings.Ui.MaraselWork.UI;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.Settings.Model.AppInfoRoot;
import www.gift_vouchers.marasel.Settings.Model.Datum;
import www.gift_vouchers.marasel.Settings.Ui.MarsilInfoModelView;
import www.gift_vouchers.marasel.databinding.WorkWithMaraselBinding;
import www.gift_vouchers.marasel.local_data.saved_data;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class WorkWithMarasel extends Fragment {
 WorkWithMaraselBinding binding;
    Datum datum;
    MarsilInfoModelView marsilInfoModelView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.work_with_marasel, container, false);
        View view = binding.getRoot();

        marsilInfoModelView = new MarsilInfoModelView();

        getData();

        return view;
    }

    private void getData() {
        marsilInfoModelView.getData("Bearer " + new saved_data().get_token(getContext()));
        marsilInfoModelView.MutableLiveData.observe(this, new Observer<AppInfoRoot>() {
            @Override
            public void onChanged(AppInfoRoot appInfoRoot) {
                binding.progressCircular.setVisibility(View.GONE);
                datum = appInfoRoot.getData();
                binding.howDoIWorkWithMrasil.setText(datum.getHowWork());
            }
        });
    }
}