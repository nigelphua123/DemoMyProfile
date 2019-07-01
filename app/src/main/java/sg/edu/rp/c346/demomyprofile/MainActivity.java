package sg.edu.rp.c346.demomyprofile;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioGroupGender);
        btnSave = findViewById(R.id.buttonSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor prefEdit = prefs.edit();
                prefEdit.commit();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        String strName = etName.getText().toString();
        if (etGPA.getText().toString().isEmpty()) {
            etGPA.setText(0);
        }

        float GPA = Float.parseFloat(etGPA.getText().toString());
        int intGenderID = rgGender.getCheckedRadioButtonId();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor prefEdit = prefs.edit();

        prefEdit.putString("username", strName);
        prefEdit.putFloat("gpa", GPA);
        prefEdit.putInt("genderId", intGenderID);


        prefEdit.commit();
    }


    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String strName = prefs.getString("username", "John");
        float GPA = prefs.getFloat("gpa", 0);
        int intGenderID = prefs.getInt("genderID", R.id.radioButtonGenderMale);

        etName.setText(strName);
        etGPA.setText(GPA + "");
        rgGender.check(intGenderID);

    }
}
