package br.com.fullface.ffdroid.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

//import br.com.fullface.ffapi.model.Device;

/**
 * Created by Wender on 05/08/2016.
 * Classe responsavel por exibir mensgens de alertas para o usuario.
 */
public class AlertAndToast {

    //Toast mensagens apenas para notificar, sem necessidade de interação do usuario
    public static void toast(Activity activity, String string){
        Toast.makeText(activity,string, Toast.LENGTH_LONG).show();
    }

    //AlertDialogs
    public static void alert(Activity activity, String title, String message, boolean back) {
        alert(activity, title, message, 0, 0, back);
    }

    public static void alert(Activity activity, String message, boolean back) {
        alert(activity, null, message, 0, 0, back);
    }

//    public static void alert(Activity activity, String message, Class c) {
//        alert(activity, null, message, c);
//    }

    public static void alert(Activity activity, String title, String message) {
        alert(activity, title, message, 0, 0, false);
    }

    public static void alert(Activity activity, String message) {
        alert(activity, null, message, 0, 0, false);
    }

    private static void alert(final Activity activity, String title, String message, int okButton, int icon, final boolean back) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (icon > 0) {
            builder.setIcon(icon);
        }
        if (title != null) {
            builder.setTitle(title);
        }
        builder.setMessage(message);
        builder.setCancelable(false);

        String okString = okButton > 0 ? activity.getString(okButton) : "OK";

        AlertDialog dialog = builder.create();
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, okString, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if(back)
                    activity.onBackPressed();
                else
                    return;
            }
        });
        dialog.show();
    }
/*
    public static void alert(final Activity activity, String title, String message, final Class c, final String param, final String string){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setCancelable(false)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GoToActivity.goToActitity(activity, c, param, string);
                    }
                })
                .setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        activity.onBackPressed();
                    }
                });

        AlertDialog dialog = builder.create();
        // Display the alert dialog on interface
        dialog.show();
    }

    //Retornar para MainActivity
    public static void alert(final Activity activity, String title, String message, final Class c) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        if (title != null) {
            builder.setTitle(title);
        }
        builder.setMessage(message);
        int okButton = 0;
        String okString = okButton > 0 ? activity.getString(okButton) : "OK";

        AlertDialog dialog = builder.create();
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, okString, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent main = new Intent(activity, c);
                main.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.finish();
                activity.startActivity(main);
            }
        });
        dialog.show();
    }

    public static void alert(final Activity activity, String title, String[] array){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final List<String> devices = Arrays.asList(array);
        List<String> remover = new ArrayList<String>();
        final Device device = new Device();

        final boolean[] checked = new boolean[array.length];

        builder.setMultiChoiceItems(array, null,new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                if(isChecked){
                    checked[which] = isChecked;
                }else if (devices.contains(which)) {
                    devices.remove(Integer.valueOf(which));
                }

            }
        }).setCancelable(false)
                .setTitle(title)
                .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when click positive button
                        for (int i = 0; i<checked.length; i++){
                            boolean check = checked[i];
                            if (check) {
                                String string = devices.get(i);
                                device.setRemover(string);
                                //Log.e("fullface", devices.get(i) + "\n");
                                Log.e("fullface","REMOVER = " + device.getRemover());
                            }
                        }
                    }
                })
                .setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when click the negative button
                    }
                });

        AlertDialog dialog = builder.create();
        // Display the alert dialog on interface
        dialog.show();
    }
*/
}
