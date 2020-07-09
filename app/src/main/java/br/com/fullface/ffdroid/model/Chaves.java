package br.com.fullface.ffdroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Chaves {

    @SerializedName("chave")
    @Expose
    private String chave;
    @SerializedName("valor")
    @Expose
    private String valor;

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
