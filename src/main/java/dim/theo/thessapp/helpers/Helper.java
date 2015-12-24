package dim.theo.thessapp.helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import dim.theo.thessapp.R;

public class Helper {

    private static Context context;

    private static final double EARTH_RADIOUS = 3958.75; // Earth radius;
    private static final int METER_CONVERSION = 1609;

    public Helper(Context context) {
        this.context = context;
    }

    public Bitmap scaleBitmap(int scaleFactor) {
        Drawable drawable = context.getResources().getDrawable(R.drawable.ic_icon_1);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable.getCurrent();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        return Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() * scaleFactor / 10, bitmap.getHeight() * scaleFactor / 10, false);
    }

    /**
     * From http://stackoverflow.com/a/19498994/423980
     * @return  distance between 2 points, stored as 2 pair location;
     */
    public static double distanceFrom(double lat1, double lng1, double lat2, double lng2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = EARTH_RADIOUS * c;
        return Double.valueOf(dist * METER_CONVERSION).floatValue();
    }
}