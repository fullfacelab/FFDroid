package br.com.fullface.ffdroid.utils;


import android.app.Activity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class MyGestureDetector {


    static Activity activity;

    public MyGestureDetector(Activity activity) {
        this.activity = activity;
    }

    /**
     * Listener para reconhecer o gesto feito pelo usuario na tela, nas direções direita e esquerda
     * gesto da esquerda para direita retorna para a activity anterior.
     */
    public static class MyFlingGestureDetector extends GestureDetector.SimpleOnGestureListener {
        // Distância do movimento em pixels. Por exemplo, o usuário tocou na tela e moveu o
        // dedo 100 pixels para a direita
        private float swipeMinDistance = 50;
        // Velocidade do moviment em pixels por segundo
        private float swipeThreasholdVelocity = 200;
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)  {
            // Usuário fez um gesto de swipe lateral
            Boolean r = false;
            try {
                if (e1.getX() - e2.getX() > swipeMinDistance && Math.abs(velocityX) > swipeThreasholdVelocity) {
                    // Fez um gesto da direita para a esquerda
                    Log.d("fullface","<<< Left");
                    r = true;
                } else if (e2.getX() - e1.getX() > swipeMinDistance && Math.abs(velocityX) > swipeThreasholdVelocity) {
                    // Fez um gesto da esquerda para a direita
                    activity.onBackPressed();
                    Log.d("fullface","Right >>>");
                    r = false;
                }
            } catch (Exception e) { }
            return r;
        }
    }
}
