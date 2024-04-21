package com.example.rysbekov_api.ui.BoardFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.viewpager2.widget.ViewPager2;

import com.example.rysbekov_api.Prefs;
import com.example.rysbekov_api.R;
import com.example.rysbekov_api.databinding.FragmentBoardBinding;


public class BoardFragment extends Fragment {

    private FragmentBoardBinding binding;
    ViewPager2 viewPager2;
    NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentBoardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        new Prefs(requireContext()).saveBoardState();

        binding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                changeColor();
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                changeColor();
            }
        });



        return root;
    }
    @SuppressLint("ResourceAsColor")
    public void changeColor(){
        if(binding.viewPager2.getCurrentItem()==0){
            binding.iv1.setImageResource(R.drawable.img_3);
            binding.iv2.setImageResource(R.drawable.img);
            binding.iv3.setImageResource(R.drawable.img);
        }else{
            if (binding.viewPager2.getCurrentItem()==1){
                binding.iv1.setImageResource(R.drawable.img);
                binding.iv2.setImageResource(R.drawable.img_3);
                binding.iv3.setImageResource(R.drawable.img);
            }else {
                binding.iv1.setImageResource(R.drawable.img);
                binding.iv2.setImageResource(R.drawable.img);
                binding.iv3.setImageResource(R.drawable.img_3);
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BoardAdapter adapter = new BoardAdapter();
        binding.viewPager2.setAdapter(adapter);

        requireActivity().getOnBackPressedDispatcher()
                .addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        requireActivity().fileList();
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}