package wit.edu.moblieapp.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // Set database path
        String path = "/data/data/" + getPackageName() + "/login.db";
        SQLiteDatabase db;
        db = SQLiteDatabase.openOrCreateDatabase(path, null);

        Button createBtn = (Button) findViewById(R.id.createButton);
        EditText usernameCreate = (EditText) findViewById(R.id.usernameCreate);
        EditText passwordCreate = (EditText) findViewById(R.id.passwordCreate);

        createBtn.setOnClickListener(v ->{
            String username = usernameCreate.getText().toString().trim();
            String password = passwordCreate.getText().toString().trim();
            if(username.matches("") && password.matches("")){
                Toast.makeText(this, "no info provided", Toast.LENGTH_SHORT).show();
            } else if(username.matches("")){
                Toast.makeText(this,"username is empty", Toast.LENGTH_SHORT).show();
            } else if(password.matches("")){
                Toast.makeText(this,"password is empty", Toast.LENGTH_SHORT).show();
            }else{


                if(!userExist(username, db)){
                    Log.v("myApp", "User does not exist");

                    if(password.length() < 7){
                        Log.v("myApp", "Password needs to be longer than 7 characters");
                        Toast.makeText(this, "Password needs to be longer than 7 characters", Toast.LENGTH_SHORT).show();
                    } else {

                        ContentValues values = new ContentValues();
                        values.put("username", username);
                        values.put("password", password);
                        db.insert("Users", null, values);


                        Log.v("myApp", "create");
                        db.close();

                        Intent intent = new Intent(CreateActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } else{
                    Log.v("myApp", "User exists");
                    Toast.makeText(this, "Username taken", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean userExist(String username, SQLiteDatabase db){
        String[] columns = {"uuid"};
        String selection = "username=?";
        String[] selectionArgs = {username};

        Cursor c = db.query("Users", columns,selection,selectionArgs,null,null,null);
        int count = c.getCount();

        c.close();
        return count > 0;
    }

}