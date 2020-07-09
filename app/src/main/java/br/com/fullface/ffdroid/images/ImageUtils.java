package br.com.fullface.ffdroid.images;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtils {
    private static final String TAG = ImageUtils.class.getName();

    /**
     *
     * @param uriFile
     * @param width
     * @param height
     * @return
     */
    public static Bitmap getResizedImage(Uri uriFile, int width, int height) {
        try {
            // Carrega a imagem original em memória
            Bitmap bitmap = BitmapFactory.decodeFile(uriFile.getPath());

            int w = bitmap.getWidth();
            int h = bitmap.getHeight();

            float scaleX = (float) width / bitmap.getWidth();
            float scaleY = (float) height / bitmap.getHeight();

            Matrix matrix = new Matrix();
            matrix.setScale(scaleX, scaleY);

            Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);

            resizedBitmap = fixMatrix(uriFile, resizedBitmap);

            // Limpa a memória do arquivo original (o grande que fizemos resize)
            bitmap.recycle();
            bitmap = null;

            // Retorna a imagem com o resize
            return resizedBitmap;
        } catch (RuntimeException e) {
            Log.e(TAG, e.getMessage(), e);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return null;
    }

    /**
     *
     * @param uriFile
     * @param width
     * @param height
     * @return
     */
    public static Bitmap getResizedImage2(Uri uriFile, int width, int height) {
        try {
            // Configura o BitmapFactory para apenas ler o tamanho da imagem (sem carregá-la em memória)
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            // Faz o decode da imagem
            BitmapFactory.decodeFile(uriFile.getPath(), opts);
            // Lê a largura e altura do arquivo
            int w = opts.outWidth;
            int h = opts.outHeight;

            // Fator de escala
            opts.inSampleSize =  Math.min(w / width, h / height);
            // Agora deixa carregar o bitmap completo
            opts.inJustDecodeBounds = false;

            Bitmap bitmap = BitmapFactory.decodeFile(uriFile.getPath(), opts);

            Bitmap newBitmap = fixMatrix(uriFile, bitmap);

            bitmap.recycle();

            return newBitmap;
        } catch (RuntimeException e) {
            Log.e(TAG, e.getMessage(), e);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return null;
    }

    private static Bitmap fixMatrix(Uri uriFile, Bitmap bitmap) throws IOException {
        Matrix matrix = new Matrix();

        // Classe para ler tags escritas no JPEG
        /**
         * Para utilizar esta classe precisa de Android 2.2 ou superior
         */
        ExifInterface exif = new ExifInterface(uriFile.getPath());

        // Lê a orientação que foi salva a foto
        int orientation = exif.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL);

        boolean fix = false;

        // Rotate bitmap
        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.postRotate(180);
                fix = true;
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.postRotate(90);
                fix = true;
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.postRotate(270);
                fix = true;
                break;
            default:
                // ORIENTATION_ROTATE_0
                fix = false;
                break;
        }

        if(!fix) {
            return bitmap;
        }

        // Corrige a orientação (passa a matrix)
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix,true);

        bitmap.recycle();
        bitmap = null;

        return newBitmap;
    }

    /**
     *
     * @param name
     * @param extension
     */
    public static void deleteFile(String name, String extension){
        try {
            File filePath = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES);

            File f;
//            for(int i = 0; i < 3; i++){
//            }
            f = new File(filePath, name + "." + extension);
            f.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param b
     * @param degrees
     * @return
     */
    public final static Bitmap rotate(Bitmap b, float degrees) {
        if (degrees != 0 && b != null) {
            Matrix m = new Matrix();
            m.setRotate(degrees, (float) b.getWidth() / 2,
                    (float) b.getHeight() / 2);

            Bitmap b2 = Bitmap.createBitmap(b, 0, 0, b.getWidth(),
                    b.getHeight(), m, true);
            if (b != b2) {
                b.recycle();
                b = b2;
            }

        }
        return b;
    }

    /**
     * Aplicar grayScale em um bitmap.
     * @param bmpOriginal
     * @return
     */
    public static Bitmap toGrayscale(Bitmap bmpOriginal){
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();

        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        return bmpGrayscale;
    }

    /**
     *
     * @param bitmap
     * @return
     */
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

    /**
     *
     * @param bytes
     * @return
     */
    public static String encodeImage(byte[] bytes){
        String s = Base64.encodeToString(bytes, Base64.NO_WRAP);
        return s; //+ "<EOF>";

    }

    /**
     *
     * @param input
     * @return
     */
    public static Bitmap decodeImageBase64(String input){
        byte[] decodedBytes = Base64.decode(input, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}
