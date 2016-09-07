package huzykamz.jdhau.huzy.ask_doctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by HUZY_KAMZ on 2/7/2016.
 */
public class HealthCentreInformation extends AppCompatActivity {

    String health_centrename="";
    String Area="";
    String opening_hours="";
    String type ="";
    String Information="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_centre_information);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Health Centres Information");


        Intent in = getIntent();
        if (null != in) {
            health_centrename = in.getStringExtra(HealthCentres.KEY_HEALTH_CENTRE_NAME);
            Area = in.getStringExtra(HealthCentres.KEY_AREA);
            opening_hours = in.getStringExtra(HealthCentres.KEY_OPENING_HOURS);
            type = in.getStringExtra(HealthCentres.KEY_TYPE);
            Information = in.getStringExtra(HealthCentres.KEY_INFORMATION);


        }


        TextView name = (TextView) findViewById(R.id.centre_name);
        name.setText(health_centrename);

        TextView area = (TextView) findViewById(R.id.area_i);
        area.setText(Area);

        TextView openh = (TextView) findViewById(R.id.opening_hours_i);
        openh.setText(opening_hours);

        TextView ty = (TextView) findViewById(R.id.type_healthcentre_i);
        ty.setText(type);
        TextView inf = (TextView) findViewById(R.id.information_i);
        inf.setText(Information);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.location:

                break;


        }
        return super.onOptionsItemSelected(item);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.health_centre_info, menu);
        return true;
    }



}
