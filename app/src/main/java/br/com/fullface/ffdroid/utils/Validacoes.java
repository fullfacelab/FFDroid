package br.com.fullface.ffdroid.utils;

import android.view.View;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Wender Dantas on 04/08/2016.
 */
public class Validacoes {

    /**
     * Método que valida o cpf
     * @param cpf
     * @return
     */
    public static boolean validateCPF(String cpf) {
        cpf = Mask.unmask(cpf);
        if (cpf.equals("00000000000") || cpf.equals("11111111111")
                || cpf.equals("22222222222") || cpf.equals("33333333333")
                || cpf.equals("44444444444") || cpf.equals("55555555555")
                || cpf.equals("66666666666") || cpf.equals("77777777777")
                || cpf.equals("88888888888") || cpf.equals("99999999999")) {
            return false;
        }
        char dig10, dig11;
        int sm, i, r, num, peso;
        try {
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else
                dig10 = (char) (r + 48);
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else
                dig11 = (char) (r + 48);
            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
                return (true);
            else
                return (false);
        } catch (Exception erro) {
            return (false);
        }
    }

    /**
     * validar email
     * @param email
     * @return
     */
    public static boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        //return email.contains("@");

        String emailPattern = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
        Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if(email.equals("email@email.email") || email.equals("email@email.com") || email.equals("mail@mail.com") || email.equals("mail@mail.mail") || email.equals("teste@teste.teste") || email.equals("test@test.test"))
            return false;
        else
            return matcher.matches();
    }

    /**
     * set invalid text
     * @param editText
     * @param string
     */
    public static void setInvalidText(EditText editText, String string){
        editText.setError(null);

        boolean cancel = false;

        View focusView = null;

        editText.setError(string);
        focusView = editText;
        cancel = true;

        //exibir a mensagem de erro
        if(cancel){
            focusView.requestFocus();
        }
    }
}
