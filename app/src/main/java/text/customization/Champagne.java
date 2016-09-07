package text.customization;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by HUZY_KAMZ on 3/25/2016.
 */
public class Champagne  extends TextView{



    public Champagne(Context context) {
        super(context);
        setFont();
    }
    public Champagne(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont();
    }
    public Champagne(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setFont();
    }

    private void setFont() {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/Champagne.ttf");
        setTypeface(font, Typeface.NORMAL);
    }
}
