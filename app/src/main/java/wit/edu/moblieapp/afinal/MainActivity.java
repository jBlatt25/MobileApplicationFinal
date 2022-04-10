package wit.edu.moblieapp.afinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String path = "/data/data/" + getPackageName() + "/login.db";
        db = SQLiteDatabase.openOrCreateDatabase(path, null);

        String sql = "CREATE TABLE IF NOT EXISTS Video" +
                "(vid INTEGER PRIMARY KEY AUTOINCREMENT, path TEXT NOT NULL);";
        db.execSQL(sql);

        Bundle bundle = this.getIntent().getExtras();
        int uuid = bundle.getInt("uuid");
        Log.v("myApp", String.format("UUID: %d received",uuid));

        if(savedInstanceState == null){
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            Fragment recyclerView = new recyclerFragment();
            transaction.replace(R.id.container, recyclerView);
            transaction.commit();
        }
    }
}