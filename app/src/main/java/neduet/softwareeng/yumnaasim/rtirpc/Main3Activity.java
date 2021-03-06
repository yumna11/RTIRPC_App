package neduet.softwareeng.yumnaasim.rtirpc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.yumnaasim.rtirpc.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import Databases.Database;
import model.AccidentDetails;
import model.AccidentRecord;
import model.Patient;
import model.PatientHealth;

public class Main3Activity extends Activity {

    EditText editTextTimestamp,editTextDataCollector,editTextNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Button button = (Button) findViewById(R.id.btnSave);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getDateTime();

        editTextTimestamp = (EditText) findViewById(R.id.timestamp);
        editTextDataCollector = (EditText) findViewById(R.id.dataCollectorName);
        editTextNotes = (EditText) findViewById(R.id.notes);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*retrieving all intent extras*/
                PatientHealth health = (PatientHealth)
                        getIntent().getSerializableExtra("PatientHealth");

                AccidentRecord accidentRecord = (AccidentRecord) getIntent().getSerializableExtra("AccidentRecord");

                Patient patient = (Patient) getIntent().getSerializableExtra("Patient");

                AccidentDetails details = (AccidentDetails) getIntent().getSerializableExtra("AccidentDetails");
                /*taking user input*/

                String timestamp = editTextTimestamp.getText().toString();
                String dataCollectorName = editTextDataCollector.getText().toString();

                accidentRecord.setTimestamp(timestamp);
                accidentRecord.setDataCollectorName(dataCollectorName);

                Spinner spinner1 = (Spinner) findViewById(R.id.headISS);
                String headISS = spinner1.getSelectedItem().toString();

                Spinner spinner2 = (Spinner) findViewById(R.id.spinner1);
                String chestISS = spinner2.getSelectedItem().toString();

                Spinner spinner3 = (Spinner) findViewById(R.id.spinner2);
                String extermityISS = spinner3.getSelectedItem().toString();

                Spinner spinner4 = (Spinner) findViewById(R.id.spinner3);
                String faceISS = spinner4.getSelectedItem().toString();

                Spinner spinner5 = (Spinner) findViewById(R.id.spinner4);
                String abdomenISS = spinner5.getSelectedItem().toString();

                Spinner spinner6 = (Spinner) findViewById(R.id.spinner5);
                String externalISS = spinner6.getSelectedItem().toString();

                String dtNotes = editTextNotes.getText().toString();

                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.disposal);
                int id = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(id);
                String disposal = radioButton.getText().toString();
                patient.setDisposal(disposal);

                health.setHeadISS(headISS);
                health.setChestISS(chestISS);
                health.setExtermityISS(extermityISS);
                health.setFaceISS(faceISS);
                health.setAbdomenISS(abdomenISS);
                health.setExternalISS(externalISS);
                health.setDoctorNotes(dtNotes);

                new Database(Main3Activity.this).insertData(patient,
                        accidentRecord,
                        details,
                        health);

               /* for (int i=0;i<5;i++) {
                    new Database(Main3Activity.this).insertData(patient,
                            accidentRecord,
                            details,
                            health);
                }*/

                Toast.makeText(getApplicationContext(),"Record saved!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Main3Activity.this,Main4Activity.class);
                intent.putExtra("Location",details.getLocation());
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
        Button buttonPrev = (Button) findViewById(R.id.btnPrevious);
        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        /*setting data on spinner*/
        Spinner spinner = (Spinner) findViewById(R.id.headISS);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        Spinner spinner4 = (Spinner) findViewById(R.id.spinner4);
        Spinner spinner5 = (Spinner) findViewById(R.id.spinner5);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.iss_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);
        spinner4.setAdapter(adapter);
        spinner5.setAdapter(adapter);
    }

    private void getDateTime() {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String formattedDate = df.format(c.getTime());

        EditText date = (EditText) findViewById(R.id.timestamp);
        date.setText(formattedDate);

    }

    public void clearForm3(View view)
    {
        editTextDataCollector.setText(null);
        editTextNotes.setText(null);
        //editTextTimestamp.setText(null);
    }


}
