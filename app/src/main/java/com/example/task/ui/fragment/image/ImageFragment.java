package com.example.task.ui.fragment.image;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.example.task.databinding.FragmentImageFragmnetBinding;
import com.example.task.ui.fragment.base.BaseFragment;

public class ImageFragment extends BaseFragment<FragmentImageFragmnetBinding> {
    private SharedPreferences pref;
    private final String SAVE_KEY = "save_key";
    private final String SAVE_BOTTOM= "save_bottom";
    private final String SAVE_URI = "save_uri";
    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected FragmentImageFragmnetBinding getBinding() {
        return FragmentImageFragmnetBinding.inflate(getLayoutInflater());

    }

    @Override
    public void setupUI() {
        initPref();
    }



    @Override
    protected void setupObservers() {

        takePictureFromGalleryOrAnyOtherFolder();
        getImages();
        saveFun();
    }

    private void saveFun() {
        binding.btnSave.setOnClickListener(v -> {
            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = pref.edit();
            editor.putString(SAVE_KEY, binding.edTextBottom.getText().toString());
            editor.putString(SAVE_BOTTOM, binding.edTextTop.getText().toString());
            editor.apply();
            pref.getString(SAVE_URI, " ");
        });
    }

    private void initPref() {
        pref = requireActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        binding.edTextTop.setText(pref.getString(SAVE_KEY, "IWN"));
        binding.edTextBottom.setText(pref.getString(SAVE_BOTTOM, " "));
        pref.getString(SAVE_URI, " ");
    }

    private void takePictureFromGalleryOrAnyOtherFolder() {
        binding.imgNight.setOnClickListener(view -> {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            activityResultLauncher.launch(photoPickerIntent);
        });

    }
            public void getImages() {
              activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                assert result.getData() != null;
                Uri selectedImage = result.getData().getData();
                binding.imgNight.setImageURI(selectedImage);

                String pathData = selectedImage.toString();
                SharedPreferences.Editor editor = pref.edit();
                editor.putString(SAVE_URI, pathData).apply();

            }
              });
            }
}