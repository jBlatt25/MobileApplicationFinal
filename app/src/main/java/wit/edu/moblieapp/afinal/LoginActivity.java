package wit.edu.moblieapp.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String path = "/data/data/" + getPackageName() + "/login.db";
        db = SQLiteDatabase.openOrCreateDatabase(path, null);

        String sql = "CREATE TABLE IF NOT EXISTS Users" +
                "(uuid INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT NOT NULL, password TEXT NOT NULL);";
        db.execSQL(sql);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        TextView createButton = (TextView) findViewById(R.id.createButton);
        EditText usernameLogin = (EditText) findViewById(R.id.usernameLogin);
        EditText passwordLogin = (EditText) findViewById(R.id.passwordLogin);

        loginButton.setOnClickListener(v -> {

            if(usernameLogin.getText().toString().matches("") && passwordLogin.getText().toString().matches("")){
                Toast.makeText(this, "no info provided", Toast.LENGTH_SHORT).show();
            } else if(usernameLogin.getText().toString().matches("")){
                Toast.makeText(this,"username is empty", Toast.LENGTH_SHORT).show();
            } else if(passwordLogin.getText().toString().matches("")){
                Toast.makeText(this,"password is empty", Toast.LENGTH_SHORT).show();
            } else{
                if(userExist(usernameLogin.getText().toString(), db)){
                    String[] columns = {"uuid", "username", "password"};
                    String selection = "username=?";
                    String[] selectionArgs = {usernameLogin.getText().toString()};

                    Cursor cLogin = db.query("Users", columns, selection, selectionArgs, null, null, null);
                    while(cLogin.moveToNext()) {
                        int uuid = cLogin.getInt(cLogin.getColumnIndexOrThrow("uuid"));
                        String passwordDB = cLogin.getString(cLogin.getColumnIndexOrThrow("password"));
                        if (passwordLogin.getText().toString().equals(passwordDB)) {
                            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                        } else{
                            Toast.makeText(this, "Password incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                    cLogin.close();
                } else{
                    Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        });

        createButton.setOnClickListener(v ->{
            Intent intent = new Intent(LoginActivity.this, CreateActivity.class);
            db.close();
            startActivity(intent);
            finish();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}