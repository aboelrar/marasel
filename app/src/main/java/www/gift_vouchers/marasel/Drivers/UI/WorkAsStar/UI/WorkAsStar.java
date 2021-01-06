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
import www.gift_vouchers.marasel.Drivers.UI.WorkAsStar.Model.ActiveDriverRoot;
import www.gift_vouchers.marasel.R;
import www.gift_vouchers.marasel.databinding.WorkAsStarBinding;
import www.gift_vouchers.marasel.local_data.saved_data;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class WorkAsStar extends Fragment implements CompoundButton.OnCheckedChangeListener {
    WorkAsStarBinding binding;
    WorkAsStarModelView WorkAsStarModelView;

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


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.switcher.setOnCheckedChangeListener(this);
        WorkAsStarModelView = new WorkAsStarModelView();
        getData();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked == true) {
            WorkAsStarModelView.getData("Bearer "+new saved_data().get_token(getContext()));
            binding.progressCircular.setVisibility(View.VISIBLE);
        }
    }

    void getData() {
        WorkAsStarModelView.MutableLiveData.observe(this, new Observer<ActiveDriverRoot>() {
            @Override
            public void onChanged(ActiveDriverRoot activeDriverRoot) {
                Toasty.success(getContext(), activeDriverRoot.getMessage(), Toasty.LENGTH_SHORT).show();
                binding.progressCircular.setVisibility(View.GONE);

            }
        });
    }
}