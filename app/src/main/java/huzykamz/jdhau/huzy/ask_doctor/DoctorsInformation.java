package huzykamz.jdhau.huzy.ask_doctor;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import model.DoctorsModel;

/**
 * Created by HUZY_KAMZ on 2/6/2016.
 */
public class DoctorsInformation extends AppCompatActivity {

    String doctors_name="";
    String hospital_name="";
    String information_doctor ="";
    String working_hours="";
    String phone_number="";
    String email="";
    String Specialisation="";
    String photo="";
    private List<DoctorsModel> flowerList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctors_information);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Doctors Information");
// add phone litsener

        // add PhoneStateListener
        PhoneCallListener phoneListener = new PhoneCallListener();
        TelephonyManager telephonyManager = (TelephonyManager) this
                .getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(phoneListener,
                PhoneStateListener.LISTEN_CALL_STATE);

        Intent in = getIntent();
        if (null != in) {
            doctors_name = in.getStringExtra(Doctors.KEY_DOCTOR_NAME);
            hospital_name = in.getStringExtra(Doctors.KEY_DOCTOR_HOSPITAL);
            information_doctor = in.getStringExtra(Doctors.KEY_DOCTOR_INFORMATION);
            working_hours = in.getStringExtra(Doctors.KEY_WORKING_HOURS);


            Specialisation = in.getStringExtra(Doctors.KEY_SPECIALISATION);
            photo = in.getStringExtra(Doctors.KEY_PHOTO);


        }
        TextView dname = (TextView) findViewById(R.id.doctors_name);
        dname.setText(doctors_name);

        TextView hname = (TextView) findViewById(R.id.health_centres_name);
        hname.setText("Health Centre: "+hospital_name);

        TextView info = (TextView) findViewById(R.id.information_doctor);
        info.setText(information_doctor);

        TextView specialise = (TextView) findViewById(R.id.specialisation);
        specialise.setText("Profession: "+Specialisation);
        TextView workingHo = (TextView) findViewById(R.id.workinghours);
        workingHo.setText("Working Hours: "+working_hours);
        //
        DoctorsModel flower = new DoctorsModel();



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.call_doctor:
                Intent in = getIntent();
    if(null != in){
        phone_number = in.getStringExtra(Doctors.KEY_PHONE_NUMBER);
    }
                Uri num = Uri.parse("tel:"+phone_number+"");
                Intent dial = new Intent(Intent.ACTION_DIAL);
                dial.setData(num);
                startActivity(dial);
                break;

            case R.id.mail_doctor:
                Intent inn = getIntent();
                if(null != inn){
                    email = inn.getStringExtra(Doctors.KEY_EMAIL);
                }
                EmailCityTyreMainBranch();

                break;

        }
        return super.onOptionsItemSelected(item);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.doctors_info, menu);
        return true;
    }





    private class PhoneCallListener extends PhoneStateListener {

        private boolean isPhoneCalling = false;

        String LOG_TAG = "LOGGING 123";

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {

            if (TelephonyManager.CALL_STATE_RINGING == state) {
                // phone ringing
                Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
            }

            if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
                // active
                Log.i(LOG_TAG, "OFFHOOK");

                isPhoneCalling = true;
            }

            if (TelephonyManager.CALL_STATE_IDLE == state) {
                // run when class initial and phone call ended, need detect flag
                // from CALL_STATE_OFFHOOK
                Log.i(LOG_TAG, "IDLE");

                if (isPhoneCalling) {

                    Log.i(LOG_TAG, "restart app");

                    // restart app
                    Intent i = getBaseContext().getPackageManager()
                            .getLaunchIntentForPackage(
                                    getBaseContext().getPackageName());
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);

                    isPhoneCalling = false;
                }

            }
        }


    }




    protected void EmailCityTyreMainBranch() {
        Log.i("Send email", "");

        String[] TO = {email};
        String[] CC = {email};
        String[] BCC = {email};




        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_BCC, BCC);

        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Compose email");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(DoctorsInformation.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT)
                    .show();
        }
        finally {
            Toast.makeText(DoctorsInformation.this,
                    "Continue with the Task.", Toast.LENGTH_SHORT)
                    .show();
        }

    }

}
