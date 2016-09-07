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


import android.widget.ListView;
import android.widget.ProgressBar;

import android.widget.Toast;



import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import adapters.jdhau.me.DoctorsAdapter;
import httpmanager.huzykamz.jdhau.HttpManager;
import model.DoctorsModel;
import parser.jdhau.me.DoctorsParser;

/**
 * Created by HUZY_KAMZ on 2/5/2016.
 */
public class Doctors extends AppCompatActivity {

    private static final String PHOTOS_BASE_URL =
            "http://192.168.43.104/UgandaMedicalAccess/Doctors/DoctorsPics/";
    ImageLoader imageLoader;

    public static String KEY_DOCTOR_NAME="doctors_name";
    public static String KEY_DOCTOR_INFORMATION="doctors_information";
    public static String KEY_DOCTOR_HOSPITAL="doctors_hospital";
    public static String KEY_WORKING_HOURS ="doctors_workinghours";
    public  static String KEY_PHONE_NUMBER="doctors_phone_number";
    public static String KEY_EMAIL="doctors_email";
    public static String KEY_SPECIALISATION="doctors_speciaisation";
    public static String KEY_PHOTO="photo";

    ProgressBar pb;
    List<MyTask> tasks;

    List<DoctorsModel> flowerList;
    ListView lv;
    DoctorsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctors);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Health Center Doctors");
        pb = (ProgressBar) findViewById(R.id.progressBar_Doctor);
        lv = (ListView) findViewById(R.id.doctors_listview);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                DoctorsModel item =(DoctorsModel) adapter.getItem(position);
                Intent in = new Intent(getApplicationContext(), DoctorsInformation.class);
                in.putExtra(KEY_DOCTOR_NAME, item.getDrName());
                in.putExtra(KEY_DOCTOR_HOSPITAL, item.getHospitalName());
                in.putExtra(KEY_DOCTOR_INFORMATION, item.getInformation());
                in.putExtra(KEY_WORKING_HOURS, item.getWorkingHours());
                in.putExtra(KEY_PHONE_NUMBER, item.getPhoneNumber());
                in.putExtra(KEY_EMAIL, item.getEmail());
                in.putExtra(KEY_SPECIALISATION, item.getSpecialisation());
                in.putExtra(KEY_PHOTO,item.getPhoto());

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
                        requestData("http://192.168.43.104/UgandaMedicalAccess/Doctors/fecth.php");
                    } else {

                    }

                }catch(NullPointerException e){
                    e.printStackTrace();
                    Toast.makeText(Doctors.this, "Check your Connectivity....", Toast.LENGTH_LONG).show();
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
        adapter = new DoctorsAdapter(this, R.layout.item_doctor, flowerList);
         lv = (ListView) findViewById(R.id.doctors_listview);
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

    private class MyTask extends AsyncTask<String, String, List<DoctorsModel>> {

        @Override
        protected void onPreExecute() {
            if (tasks.size() == 0) {
                pb.setVisibility(View.VISIBLE);
            }
            tasks.add(this);
        }

        @Override
        protected List<DoctorsModel> doInBackground(String... params) {


                String content = HttpManager.getData(params[0], "feeduser", "feedpassword");
                flowerList = DoctorsParser.parseFeed(content);

                for (DoctorsModel flower : flowerList) {
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
        protected void onPostExecute(List<DoctorsModel> result) {

                tasks.remove(this);
                if (tasks.size() == 0) {
                    pb.setVisibility(View.INVISIBLE);
                }

                if (result == null) {
                    Toast.makeText(Doctors.this, "Data is  not available", Toast.LENGTH_LONG).show();
                    return;
                }

                flowerList = result;
                updateDisplay();

            }

        }


    }

