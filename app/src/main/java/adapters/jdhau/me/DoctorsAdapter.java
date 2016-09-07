package adapters.jdhau.me;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


import huzykamz.jdhau.huzy.ask_doctor.ImageLoader;
import huzykamz.jdhau.huzy.ask_doctor.R;
import model.DoctorsModel;

/**
 * Created by HUZY_KAMZ on 2/5/2016.
 */
public class DoctorsAdapter  extends ArrayAdapter<DoctorsModel> {
    private Context context;
    private List<DoctorsModel> flowerList;
    ImageLoader imageLoader;

    public DoctorsAdapter(Context context, int resource, List<DoctorsModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.flowerList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_doctor, parent, false);

        //Display flower name in the TextView widget
        DoctorsModel flower = flowerList.get(position);
        TextView drName = (TextView) view.findViewById(R.id.dr_name);
        TextView HospitalName = (TextView) view.findViewById(R.id.hospital_name);
        TextView specialisation = (TextView) view.findViewById(R.id.specialisation);



        drName.setText(flower.getDrName());
        HospitalName.setText(flower.getHospitalName());
        specialisation.setText(flower.getSpecialisation());


        //Display flower photo in ImageView widget
        ImageView image = (ImageView) view.findViewById(R.id.doctor_image);
        image.setImageBitmap(flower.getBitmap());

        // image Loader

        return view;
    }




}




