package com.example.petplanet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.DatePickerDialog;
import java.util.Calendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import androidx.core.util.Pair;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;


public class RegistroPerroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_perro);
        Toolbar toolbarRegPet = findViewById(R.id.toolbarRegPetW);


        setSupportActionBar(toolbarRegPet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbarRegPet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // luego se pone despues que se revise el rol del usuario a que pantalla ir
                startActivity(new Intent(getApplicationContext(),PerfilUsuarioActivity.class));
            }
        });


        EditText fechanacimiento = findViewById(R.id.registrofechanacimientoperro);
        EditText nombredelperro = findViewById(R.id.registroNombrePet);
        ImageView fotoperro = findViewById(R.id.fotodelperroBTN);
        Button registrarperro = findViewById(R.id.registrarMascotaBT);
        Spinner raza = findViewById(R.id.spinnerraza);
        Spinner sexo = findViewById(R.id.spinnersexo);
        Spinner color = findViewById(R.id.spinnercolor);
        MaterialDatePicker.Builder<Long> materialDateBuilder = MaterialDatePicker.Builder.datePicker();
        materialDateBuilder.setTitleText("Selecciona la fecha de nacimiento");
        final MaterialDatePicker materialDatePicker = materialDateBuilder.build();
        fechanacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
            }
        });
        materialDatePicker.addOnPositiveButtonClickListener(
                new MaterialPickerOnPositiveButtonClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onPositiveButtonClick(Object selection) {

                        // if the user clicks on the positive
                        // button that is ok button update the
                        // selected date
                        fechanacimiento.setText(materialDatePicker.getHeaderText());
                        // in the above statement, getHeaderText
                        // will return selected date preview from the
                        // dialog
                    }
                });

        registrarperro.setOnClickListener(v -> {
            String nombrep =nombredelperro.getText().toString();
            String colorS = color.getSelectedItem().toString();
            String sexoS = sexo.getSelectedItem().toString();
            String razaS = raza.getSelectedItem().toString();
            String fechanacimientoS = fechanacimiento.getText().toString();
            if(nombrep.isEmpty()){
                nombredelperro.setError("Ingrese el nombre de la mascota");
                nombredelperro.requestFocus();
                return;
            }if(razaS.equals("Selecciona una raza")){
                ((TextView)raza.getSelectedView()).setError("Error message");
            }if(sexoS.equals("Seleccione el sexo")){
                ((TextView)sexo.getSelectedView()).setError("Error message");
            }if(colorS.equals("Selecciona un color")){
                ((TextView)color.getSelectedView()).setError("Error message");
            }if(fechanacimientoS.isEmpty()){
                fechanacimiento.setError("Ingrese una fecha de nacimiento valida");
                fechanacimiento.requestFocus();
                return;
            }else{
                Intent intent = new Intent(getApplicationContext(), PerfilUsuarioActivity.class);
                startActivity(intent);
            }
        });



    }
}
