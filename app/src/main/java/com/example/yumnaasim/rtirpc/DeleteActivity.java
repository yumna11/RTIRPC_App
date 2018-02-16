package com.example.yumnaasim.rtirpc;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

import Databases.Database;
import Databases.Schema;

public class DeleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        handleUserInput();
    }

    private void handleUserInput() {

        Button clear = (Button) findViewById(R.id.buttonClear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
    }

    private void showAlertDialog() {

        new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_warning_black_24dp)
                .setTitle("Clearing Data")
                .setMessage(getResources().getString(R.string.alert_msg))
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Database(DeleteActivity.this).deleteData();
                        deleteFiles();
                        Toast.makeText(getApplicationContext(),"Deleted successfully!",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", null)
                .show();

    }

    private void deleteFiles() {
        new File(Environment.getExternalStorageDirectory(),getResources().getString(R.string.file2_name)+".csv").delete();
        new File(Environment.getExternalStorageDirectory(),getResources().getString(R.string.file1_name)+".csv").delete();
        new File(Environment.getExternalStorageDirectory(),getResources().getString(R.string.file3_name)+".csv").delete();
        new File(Environment.getExternalStorageDirectory(),getResources().getString(R.string.file4_name)+".csv").delete();
    }

}
