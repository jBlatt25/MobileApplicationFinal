package wit.edu.moblieapp.afinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

public class loginscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);

        String path = "/data/data/" + getPackageName() + "/login.db";
        SQLiteDatabase db;
        db = SQLiteDatabase.openOrCreateDatabase(path, null);

        String sql = "CREATE TABLE IF NOT EXISTS Users" +
                "(uuid INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT NOT NULL, password TEXT NOT NULL);";
        db.execSQL(sql);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        TextView createButton = (TextView) findViewById(R.id.createLogin);

        loginButton.setOnClickListener(v -> {
                Log.v("myApp", "loginLogin");


                String[] columns = {"uuid", "username", "password"};
                String selection = "username=?";
                String[] selectionArgs = {"abc123"};

                Cursor c = db.query("Users", columns,selection,selectionArgs,null,null,null);
                int count = c.getCount();
                if(count>0){
                    Log.v("myApp", String.format("%d matches the query",count));
                }
                while(c.moveToNext()){
                    int uuid = c.getInt(c.getColumnIndexOrThrow("uuid"));
                    String username = c.getString(c.getColumnIndexOrThrow("username"));
                    String password = c.getString(c.getColumnIndexOrThrow("password"));
                    Log.v("myApp", String.format("%d / %s / %s", uuid, username, password));

                }
                c.close();


        });

        createButton.setOnClickListener(v ->{
            Log.v("myApp", "createLogin");
            Intent intent = new Intent(loginscreen.this, createaccount.class);
            db.close();
            startActivity(intent);
            finish();
        });

    }
}