package br.com.fullface.ffdroid.images;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Created by Wender on 18/12/2016.
 */

public class CropImage {

    /**
     *
     * @param bitmap
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     */
    public static Bitmap cropImage(Bitmap bitmap, final float x, final float y, final int width, final int height){
        try {
            bitmap = cropImageFace(bitmap, x, y, width, height);
            return bitmap;
        }catch (Exception e){
            e.getMessage();
        }
        return null;
    }

    /**
     *
     * @param bitmap
     * @param x
     * @param y
     * @param width
     * @param heigth
     * @return
     */
    public static Bitmap cropImageFace(Bitmap bitmap, float x, float y, int width, int heigth){
        try {
            int START_X = (int) Math.round(x);
            int START_Y = (int) Math.round(y);
            Matrix matrix = new Matrix();
            bitmap = Bitmap.createBitmap(bitmap, START_X, START_Y, width, heigth);
            return bitmap;
        }catch (Exception e){
            e.getMessage();
        }
        return bitmap;
    }
}
