package com.example.androidfinalproject2;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.example.androidfinalproject2.databinding.ActivityMainBinding;

import java.time.Year;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ViewModel mvm;
    String etEmailaddress;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mvm =new ViewModelProvider(this).get(ViewModel.class);

        ActivityResultLauncher<String> arl =
                registerForActivityResult(new ActivityResultContracts.GetContent()
                        , new ActivityResultCallback<Uri>() {
                            @Override
                            public void onActivityResult(Uri result) {
                                binding.imgeMain.setImageURI(result);
                            }
                        });
        binding.imgeMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arl.launch("image/*");
            }
        });
        Calendar calendar  =Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);

        binding.etBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog=new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1=month+1;
                        String data=day+"/"+month+"/"+year;
                        binding.etBirth.setText(data);

                    }
                },year,month,day);
                dialog.show();
            }
        });
      binding.btnSave.setOnClickListener(new View.OnClickListener() {
          @Override
           public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),Home_Activity.class);
               startActivity(intent);

                if (!etEmailaddress.matches(emailPattern)) {
                    binding.etEmailaddress.setError("Invalid email address");
                    return;
                }



            }
        });

      binding.btnSave.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent=new Intent(getBaseContext(),Home_Activity.class);
              startActivity(intent);
          }
      });

            }

    }
