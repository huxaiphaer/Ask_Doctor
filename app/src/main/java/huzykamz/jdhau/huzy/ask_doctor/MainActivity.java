package huzykamz.jdhau.huzy.ask_doctor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import adapters.jdhau.me.DoctorsAdapter;
import adapters.jdhau.me.NewsAdapter;
import httpmanager.huzykamz.jdhau.HttpManager;
import model.DoctorsModel;
import model.NewsModel;
import model.TipModel;
import parser.jdhau.me.DoctorsParser;
import parser.jdhau.me.NewsParser;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String PHOTOS_BASE_URL =
            "http://192.168.43.104/UgandaMedicalAccess/HealthCentreNews/HealthCentreNewsPics/";

    public static String KEY_HEADLINES="headlines";
    public static String KEY_DETAILS="details";




    ProgressBar pb;
    List<MyTask> tasks;

    List<NewsModel> flowerList;
    ListView lv;
    NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // call

        Fetch();
        Next();
        pb = (ProgressBar) findViewById(R.id.progressBarNews);


        tasks = new ArrayList<>();
// declaring the recycler view.

        // load data

        //declare the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(isOnline()){

                   requestData("http://192.168.43.104/UgandaMedicalAccess/HealthCentreNews/fecth.php");
                   Snackbar.make(view, "Refreshing news ....", Snackbar.LENGTH_LONG)
                           .setAction("Thanks.", null).show();
               }

                else {
                   Snackbar.make(view, "There's a Network problem", Snackbar.LENGTH_LONG)
                           .setAction("", null).show();

               }

            Snackbar.make(view, "Refreshing news ....", Snackbar.LENGTH_LONG)
                        .setAction("Thanks.", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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





    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent i;
        if (id == R.id.hospitals) {

            i = new Intent(MainActivity.this, HealthCentres.class);
            startActivity(i);


        } else if (id == R.id.doctors) {

            i = new Intent(MainActivity.this, Doctors.class);
            startActivity(i);


        } /*else if (id == R.id.location) {
            i = new Intent(MainActivity.this, Location.class);
            startActivity(i);

        } */else if (id == R.id.tips) {
            i = new Intent(MainActivity.this, Tips.class);
            startActivity(i);

        } /*else if (id == R.id.faq) {


        }
        */
        /*
        else if (id == R.id.suggestions) {

        }
        */
        else if (id == R.id.contacts) {
            i= new Intent(MainActivity.this, ContactUs.class);
            startActivity(i);

        }
        /*
        else if (id == R.id.settings) {

        }
        */
        else if (id == R.id.about) {
            i = new Intent(MainActivity.this, AboutUs.class);
            startActivity(i);

        }





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void Next(){
        lv = (ListView) findViewById(R.id.list_view_news);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                NewsModel item = (NewsModel) adapter.getItem(position);
                Intent in = new Intent(getApplicationContext(), NewsInformation.class);
                in.putExtra(KEY_DETAILS, item.getDetails());
                in.putExtra(KEY_HEADLINES, item.getHeadlines());

                startActivity(in);
            }
        });

    }


    public void Fetch(){

        Thread timerThread = new Thread(){
            public void run(){

                if (isOnline()) {
                    requestData("http://192.168.43.104/UgandaMedicalAccess/HealthCentreNews/fecth.php");
                } else {

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
        adapter = new NewsAdapter(this, R.layout.item_news, flowerList);
        lv = (ListView) findViewById(R.id.list_view_news);
        lv.setAdapter(adapter);
    }





    private class MyTask extends AsyncTask<String, String, List<NewsModel>> {

        @Override
        protected void onPreExecute() {
            if (tasks.size() == 0) {
                pb.setVisibility(View.VISIBLE);
            }
            tasks.add(this);
        }

        @Override
        protected List<NewsModel> doInBackground(String... params) {


            String content = HttpManager.getData(params[0], "feeduser", "feedpassword");
            flowerList = NewsParser.parseFeed(content);

            for (NewsModel flower : flowerList) {
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
        protected void onPostExecute(List<NewsModel> result) {

            tasks.remove(this);
            if (tasks.size() == 0) {
                pb.setVisibility(View.INVISIBLE);
            }

            if (result == null) {
                Toast.makeText(MainActivity.this, "Data is  not available", Toast.LENGTH_LONG).show();
                return;
            }

            flowerList = result;
            updateDisplay();

        }

    }





}
