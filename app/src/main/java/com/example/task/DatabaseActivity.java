package com.example.task;

import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DatabaseActivity extends AppCompatActivity {
    private EditText name,contact, dob ;
    private Button insert,update,delete,read;
    DBaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name =findViewById(R.id.ETname);
        contact = findViewById(R.id.contact);
        dob = findViewById(R.id.dob);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        read = findViewById(R.id.btnView);

        DB = new DBaseHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTxt = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob.getText().toString();
                Boolean checkInsertData = DB.insertData(nameTxt,contactTXT,dobTXT);
                if(checkInsertData){
                    Toast.makeText(DatabaseActivity.this,"New Entry Inserted",Toast.LENGTH_SHORT).show();
                    name.setText("");
                    contact.setText("");
                    dob.setText("");
                }else{
                    Toast.makeText(DatabaseActivity.this,"New Entry Not Inserted",Toast.LENGTH_SHORT).show();
                }
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTxt = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob.getText().toString();
                Boolean checkUpdateData = DB.updateData(nameTxt,contactTXT,dobTXT);
                if(checkUpdateData){
                    Toast.makeText(DatabaseActivity.this,"Entry Updated",Toast.LENGTH_SHORT).show();
                    name.setText("");
                    contact.setText("");
                    dob.setText("");
                }else{
                    Toast.makeText(DatabaseActivity.this,"Entry Not Updated",Toast.LENGTH_SHORT).show();
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTxt = name.getText().toString();
                Boolean checkDeleteData = DB.deleteData(nameTxt);
                if(checkDeleteData){
                    Toast.makeText(DatabaseActivity.this,"Entry Deleted",Toast.LENGTH_SHORT).show();
                    name.setText("");
                    contact.setText("");
                    dob.setText("");
                }else{
                    Toast.makeText(DatabaseActivity.this,"Entry Not Deleted",Toast.LENGTH_SHORT).show();
                }
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getData();
                if(res.getCount()==0){
                    Toast.makeText(DatabaseActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name :"+res.getString(0)+"\n");
                    buffer.append("Contact :"+res.getString(1)+"\n");
                    buffer.append("Date of Birth :"+res.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(DatabaseActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}