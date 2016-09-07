package text.customization;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by HUZY_KAMZ on 3/25/2016.
 */
public class Grenale extends TextView {


    public Grenale(Context context) {
        super(context);
        setFont();
    }
    public Grenale(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont();
    }
    public Grenale(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setFont();
    }

    private void setFont() {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/Grenale.ttf");
        setTypeface(font, Typeface.NORMAL);
    }
}
