package sg.edu.rp.c346.id20039583.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvName, tvGPA;
    EditText etName, etGPA;
    RadioGroup rgGender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvName = findViewById(R.id.textViewName);
        tvGPA = findViewById(R.id.textViewGPA);
        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioGroupGender);
    }

    //implementing the onPause
    @Override
    protected void onPause() {
        super.onPause();

        //step 1: get user input from the edittext and store it in a varaiable
        String StrName = etName.getText().toString();
        float gpa = Float.parseFloat(etGPA.getText().toString());

        String gender;
        if (rgGender.getCheckedRadioButtonId() == R.id.radioButtonGenderMale){
            gender = "Male";
        } else {
            gender = "Female";
        }
        //Step 1b: obtain an instance of shared preferences
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);

        //step 1c obtian an instance of the sharedpreference editor for update later
        SharedPreferences.Editor prefEdit = prefs.edit();

        //step 1d Add the key-value pair
        prefEdit.putString("name", StrName );
        prefEdit.putFloat("gpa",gpa);
        prefEdit.putString("gender", gender);

        //Call commit to sace the chnges into sharedPreferences
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //step 2a obtain an instanve of the shared preference
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);

        //step 2b retrieve the saved data from the sharedpreferences object
        String StrName = prefs.getString("name", "Unnamed");
        Float gpa = prefs.getFloat("gpa", Float.parseFloat("0.00"));
        String gender = prefs.getString("gender", "Male");


        //Step 2c: Update the UI element with the value
        etName.setText(StrName);
        etGPA.setText(gpa + "");

        if (gender.equals("Male") ){
            rgGender.check(R.id.radioButtonGenderMale);
        } else if ( gender.equals("Female") ){
            rgGender.check(R.id.radioButtonGenderFemale);
        }



    }



}