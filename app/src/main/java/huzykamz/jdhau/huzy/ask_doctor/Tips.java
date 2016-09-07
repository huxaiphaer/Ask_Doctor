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

import adapters.jdhau.me.NewsAdapter;
import httpmanager.huzykamz.jdhau.HttpManager;
import model.NewsModel;
import parser.jdhau.me.NewsParser;

/**
 * Created by HUZY_KAMZ on 2/5/2016.
 */
public class Tips extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tips);
    }
}
