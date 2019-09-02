package com.example.trackingtransport.Controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.trackingtransport.R;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsFactory {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Bitmap drawMarker(Context context, String text) {
        Drawable drawable = context.getResources().getDrawable(R.drawable.ic_car_marker, context.getTheme());
        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        Paint paint = new Paint();
        paint.setTextSize(50 * context.getResources().getDisplayMetrics().density / 2);
        paint.setStyle(Paint.Style.FILL);
        Canvas textCanvas = new Canvas(bitmap);
        //textCanvas.drawText(text, ((bitmap.getWidth() * 7) / 20), (bitmap.getHeight() / 2), paint);

        return bitmap;
    }

    public static PolylineOptions drawRoute(Context context) {
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.geodesic(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            polylineOptions.color(context.getResources().getColor(R.color.black, context.getTheme()));
        } else {
            polylineOptions.color(context.getResources().getColor(R.color.black));
        }
        return polylineOptions;
    }
}
