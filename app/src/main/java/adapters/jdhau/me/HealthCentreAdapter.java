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

import model.HealthCentreModel;

/**
 * Created by HUZY_KAMZ on 2/7/2016.
 */
public class HealthCentreAdapter  extends ArrayAdapter<HealthCentreModel> {
    private Context context;
    ImageLoader imageLoader;
    private List<HealthCentreModel> flowerList;

    public HealthCentreAdapter(Context context, int resource, List<HealthCentreModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.flowerList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_health_centre, parent, false);

        //Display flower name in the TextView widget
        HealthCentreModel flower = flowerList.get(position);

        TextView name = (TextView) view.findViewById(R.id.name);
        TextView area = (TextView) view.findViewById(R.id.area);
        TextView type = (TextView) view.findViewById(R.id.type);

        TextView workinghours = (TextView) view.findViewById(R.id.openinghours);


        name.setText(flower.getHealthCentreName());
        area.setText(flower.getArea());
        type.setText(flower.getTypeHealthCare());
  workinghours.setText(flower.getOpeningHours());



        //Display flower photo in ImageView widget
        ImageView image = (ImageView) view.findViewById(R.id.healthCentre_image);
        image.setImageBitmap(flower.getBitmap());
/*        imageLoader.DisplayImage(flowerList.get(position).getPhoto(),
                image);
*/
        return view;

    }




}
