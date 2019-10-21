package com.ion.multilanguage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityHelper.getHelper().addActivity(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LanguageSPUtil.getInstance(newBase).setLocal());
    }

    public void moren(View view) {
        if (LanguageSPUtil.getInstance(this).getLanguage() == LanguageSPUtil.LANGUAGE_TYPE_AUTO) {

            return;
        }
        LanguageSPUtil.getInstance(this).saveLanguage(LanguageSPUtil.LANGUAGE_TYPE_AUTO);
        LanguageSPUtil.getInstance(this).changeAppLanguage();
        skipToMain();
    }

    public void zhongwen(View view) {
        if (LanguageSPUtil.getInstance(this).getLanguage() == LanguageSPUtil.LANGUAGE_TYPE_ZH) {
            return;
        }
        LanguageSPUtil.getInstance(this).saveLanguage(LanguageSPUtil.LANGUAGE_TYPE_ZH);
        LanguageSPUtil.getInstance(this).changeAppLanguage();
        skipToMain();

    }

    public void yinni(View view) {
        if (LanguageSPUtil.getInstance(this).getLanguage() == LanguageSPUtil.LANGUAGE_TYPE_IN) {
            return;
        }
        LanguageSPUtil.getInstance(this).saveLanguage(LanguageSPUtil.LANGUAGE_TYPE_IN);
        LanguageSPUtil.getInstance(this).changeAppLanguage();
        skipToMain();

    }

    public void yingwen(View view) {
        if (LanguageSPUtil.getInstance(this).getLanguage() == LanguageSPUtil.LANGUAGE_TYPE_EN) {
            return;
        }
        LanguageSPUtil.getInstance(this).saveLanguage(LanguageSPUtil.LANGUAGE_TYPE_EN);
        LanguageSPUtil.getInstance(this).changeAppLanguage();
        skipToMain();

    }

    public static void skipToMain() {
        Intent intent = new Intent(ActivityHelper.getHelper().currentActivity(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        ActivityHelper.getHelper().currentActivity().startActivity(intent);
    }
}
