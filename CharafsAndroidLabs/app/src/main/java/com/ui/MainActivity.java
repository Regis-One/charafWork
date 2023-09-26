package com.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.data.MainViewModel;
import com.example.charafsandroidlabs.R;
import com.example.charafsandroidlabs.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private MainViewModel model;
    private ActivityMainBinding variableBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        model = new ViewModelProvider(this).get(MainViewModel.class);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());



        model.isChosen.observe(this, selected -> {
            variableBinding.checkBox.setChecked(selected);
            variableBinding.radioButton.setChecked(selected);
            variableBinding.mySwitch.setChecked(selected);
            CharSequence text = "The value is now: " + model.isChosen.getValue();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this /* MyActivity */, text, duration);
            toast.show();
        });


        model.editString.observe(this, name -> {
            variableBinding.helloMessage.setText("Hello " + name);
        });

        variableBinding.checkBox.setOnCheckedChangeListener((btn, isChecked) -> {
            model.isChosen.postValue(isChecked);
        });
        variableBinding.radioButton.setOnCheckedChangeListener((btn, isChecked) -> {
            model.isChosen.postValue(isChecked);
        });
        variableBinding.mySwitch.setOnCheckedChangeListener((btn, isChecked) -> {
            model.isChosen.postValue(isChecked);
        });

        variableBinding.Btn.setOnClickListener(v -> {
            model.editString.postValue(variableBinding.textInput.getText().toString());
            variableBinding.nameQuestion.setVisibility(View.GONE);
        });

        variableBinding.myImageButton.setOnClickListener( img -> {
            CharSequence textImage = "Picture's width: " + img.getWidth() + " Picture's height: " + img.getHeight();
            int durationTextImage = Toast.LENGTH_SHORT;

            Toast.makeText(this /* MyActivity */, textImage, Toast.LENGTH_SHORT).show();
        });

        variableBinding.myImageView.setOnClickListener( img -> {
            Toast.makeText(this, "Image Clicked", Toast.LENGTH_SHORT).show();
        });



    }
}