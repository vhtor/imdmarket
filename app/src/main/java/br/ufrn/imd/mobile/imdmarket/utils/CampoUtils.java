package br.ufrn.imd.mobile.imdmarket.utils;

import android.widget.TextView;

public class CampoUtils {
    public static void limparCampos(TextView... campos) {
        for (TextView campo : campos) {
            campo.setText("");
        }
    }
}
