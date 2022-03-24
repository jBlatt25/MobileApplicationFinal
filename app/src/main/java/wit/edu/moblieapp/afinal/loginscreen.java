package wit.edu.moblieapp.afinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
        String path = "data/data" + getPackageName() + "/sample.db";


        Button loginButton = (Button) findViewById(R.id.log_in_button);

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.v("myApp", "BUTTON PRESS");

                SQLiteDatabase db;
                db = SQLiteDatabase.openOrCreateDatabase();
            }
        });
    }
}