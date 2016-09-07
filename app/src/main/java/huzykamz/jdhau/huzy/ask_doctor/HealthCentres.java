package huzykamz.jdhau.huzy.ask_doctor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import adapters.jdhau.me.DoctorsAdapter;
import adapters.jdhau.me.HealthCentreAdapter;
import httpmanager.huzykamz.jdhau.HttpManager;
import model.DoctorsModel;
import model.HealthCentreModel;
import parser.jdhau.me.DoctorsParser;
import parser.jdhau.me.HealthCentreParser;

/**
 * Created by HUZY_KAMZ on 2/5/2016.
 */
public class HealthCentres extends AppCompatActivity {

    private static final String PHOTOS_BASE_URL =
            "http://192.168.43.104/UgandaMedicalAccess/HealthCentres/HealthCentrePics/";

    public static String KEY_HEALTH_CENTRE_NAME="healthcentre_name";
    public static String KEY_AREA="doctors_information";
    public static String KEY_OPENING_HOURS="doctors_hospital";
    public static String KEY_TYPE="doctors_workinghours";
    public  static String KEY_INFORMATION="doctors_phone_number";


    ProgressBar pb;
    List<MyTask> tasks;

    List<HealthCentreModel> flowerList;
    GridView lv;
    HealthCentreAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospitals);
        pb =(ProgressBar) findViewById(R.id.progressBar_healthCentre);
        lv = (GridView) findViewById(R.id.listview_healthcentre);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("List of Health Centers");

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                HealthCentreModel item =(HealthCentreModel) adapter.getItem(position);
                Intent in = new Intent(getApplicationContext(), HealthCentreInformation.class);
                in.putExtra(KEY_HEALTH_CENTRE_NAME, item.getHealthCentreName());
                in.putExtra(KEY_AREA, item.getArea());
                in.putExtra(KEY_OPENING_HOURS, item.getOpeningHours());
                in.putExtra(KEY_TYPE, item.getTypeHealthCare());
                in.putExtra(KEY_INFORMATION, item.getHealthCentreInfo());
                startActivity(in);
            }
        });


        tasks = new ArrayList<>();
        Fetch();
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





    public void Fetch(){

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    if (isOnline()) {
                        requestData("http://192.168.43.104/UgandaMedicalAccess/HealthCentres/fecth.php");
                    } else {

                    }

                }catch(NullPointerException e){
                    e.printStackTrace();
                    Toast.makeText(HealthCentres.this, "Check your Connectivity....", Toast.LENGTH_LONG).show();
                }
            }
        };
        timerThread.start();
    }





    private void requestData(String uri) {
        MyTask task = new MyTask();
        task.execute(uri);
    }

    protected void updateDisplay() {
        //Use FlowerAdapter to display data
        adapter = new HealthCentreAdapter(this, R.layout.item_doctor, flowerList);
        lv = (GridView) findViewById(R.id.listview_healthcentre);
        lv.setAdapter(adapter);
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }


    private class MyTask extends AsyncTask<String, String, List<HealthCentreModel>> {

        @Override
        protected void onPreExecute() {
            if (tasks.size() == 0) {
                pb.setVisibility(View.VISIBLE);
            }
            tasks.add(this);
        }

        @Override
        protected List<HealthCentreModel> doInBackground(String... params) {


            String content = HttpManager.getData(params[0], "feeduser", "feedpassword");
            flowerList = HealthCentreParser.parseFeed(content);

            for (HealthCentreModel flower : flowerList) {
                try {
                    String imageUrl = PHOTOS_BASE_URL + flower.getPhoto();
                    InputStream in = (InputStream) new URL(imageUrl).getContent();
                    Bitmap bitmap = BitmapFactory.decodeStream(in);
                    flower.setBitmap(bitmap);
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }




            return flowerList;
        }

        @Override
        protected void onPostExecute(List<HealthCentreModel> result) {

            tasks.remove(this);
            if (tasks.size() == 0) {
                pb.setVisibility(View.INVISIBLE);
            }

            if (result == null) {
                Toast.makeText(HealthCentres.this, "Data is  not available", Toast.LENGTH_LONG).show();
                return;
            }

            flowerList = result;
            updateDisplay();

        }

    }


}
