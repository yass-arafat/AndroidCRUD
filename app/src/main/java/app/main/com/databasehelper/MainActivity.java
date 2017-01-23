package app.main.com.databasehelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editTextName, editTextSurname, editTextMarks;
    Button btnAddData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        editTextName = (EditText) findViewById(R.id.editText_name);
        editTextSurname = (EditText) findViewById(R.id.editText_surname);
        editTextMarks = (EditText) findViewById(R.id.editText_marks);
        btnAddData = (Button) findViewById(R.id.button_add_button);
        AddData();
    }

    private void AddData() {
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(editTextName.toString(),editTextSurname.toString(),editTextMarks.toString());
                if(isInserted){
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
