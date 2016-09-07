package huzykamz.jdhau.huzy.ask_doctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by HUZY_KAMZ on 2/21/2016.
 */
public class NewsInformation extends AppCompatActivity {



    String details="";
    String headlines="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_info);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("News Details");


        Intent jhild = getIntent();

        if (null != jhild) {
            details = jhild.getStringExtra(MainActivity.KEY_DETAILS);
            headlines = jhild.getStringExtra(MainActivity.KEY_HEADLINES);


        }


        TextView det = (TextView) findViewById(R.id.details_info);
        det.setText(details);

        TextView head = (TextView) findViewById(R.id.headlines_info);
        head.setText(headlines);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                break;


        }
        return super.onOptionsItemSelected(item);
    }
}
