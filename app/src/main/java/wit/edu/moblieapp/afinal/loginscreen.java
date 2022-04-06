package wit.edu.moblieapp.afinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class loginscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);

        // Set database path
        String path = "/data/data/" + getPackageName() + "/login.db";
        SQLiteDatabase db;
        db = SQLiteDatabase.openOrCreateDatabase(path, null);

        String sql = "CREATE TABLE IF NOT EXISTS Users" +
                "(uuid INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT NOT NULL, password TEXT NOT NULL);";
        db.execSQL(sql);

//        ContentValues values = new ContentValues();
////        values.put("username","abc123");
////        values.put("password","abc123");
////        db.insert("Users", null, values);


        Button loginButton = (Button) findViewById(R.id.button);
        loginButton.setOnClickListener((v) -> {

                Log.v("myApp", "BUTTON PRESS");
                String[] columns = {"uuid"};
                String selection = "username=?";
                String[] selectionArgs = {"abc123"};

                Cursor c = db.query("Users", columns,selection,selectionArgs,null,null,null);
                int count = c.getCount();
                if(count>0){
                    Log.v("myApp", String.format("%d matches the query",count));
                }
                while(c.moveToNext()){
                    int uuid = c.getInt(c.getColumnIndexOrThrow("uuid"));
                    String[] cCount = c.getColumnNames();
                    for(String s : cCount){
                        Log.v("myApp", s);
                    }
                }
                c.close();
                db.close();


        });


    }
}