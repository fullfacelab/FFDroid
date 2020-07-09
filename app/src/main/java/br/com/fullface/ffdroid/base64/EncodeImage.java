package br.com.fullface.ffdroid.base64;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by Wender on 19/12/2016.
 */

public class EncodeImage {

    public static String encodeImage(Bitmap bitmap){

        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

            byte[] bytes = stream.toByteArray();
            return encodeImage(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }



    public static String encodeImage(byte[] bytes){

        String s = Base64.encodeToString(bytes, Base64.NO_WRAP);
        return s; //+ "<EOF>";

    }

    public static Bitmap decodeImageBase64(String input)
    {
        byte[] decodedBytes = Base64.decode(input, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}
