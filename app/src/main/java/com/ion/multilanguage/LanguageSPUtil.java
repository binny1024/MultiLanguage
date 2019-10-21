package com.ion.multilanguage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.util.DisplayMetrics;
import android.util.Log;


import java.util.Locale;

public class LanguageSPUtil {

    private final String SP_NAME = "language_setting";
    private final String TAG_LANGUAGE = "language_select";
    private static volatile LanguageSPUtil instance;

    private final SharedPreferences mSharedPreferences;


    public static final int LANGUAGE_TYPE_AUTO = 0;
    public static final int LANGUAGE_TYPE_ZH = 1;
    public static final int LANGUAGE_TYPE_EN = 2;
    public static final int LANGUAGE_TYPE_IN = 3;

    private static Context sContext;

    private LanguageSPUtil(Context context) {
        sContext = context.getApplicationContext();
        mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public static LanguageSPUtil getInstance(Context context) {
        if (instance == null) {
            synchronized (LanguageSPUtil.class) {
                if (instance == null) {
                    instance = new LanguageSPUtil(context);
                }
            }
        }
        return instance;
    }

    /**
     * @param select 保存语言标记
     */
    public void saveLanguage(int select) {
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.putInt(TAG_LANGUAGE, select);
        edit.commit();
    }

    /**
     * @return 获取当期保存的语言标记
     */
    public int getLanguage() {
        return mSharedPreferences.getInt(TAG_LANGUAGE, 0);
    }

    /**
     * @return 获取当期虚拟机设置的语言环境
     */
    public Locale getCurrentLocal() {
        switch (getLanguage()) {
            case LANGUAGE_TYPE_ZH:
                return Locale.CHINA;
            case LANGUAGE_TYPE_EN:
                return Locale.ENGLISH;
            case LANGUAGE_TYPE_IN:
                return new Locale("id");
            case LANGUAGE_TYPE_AUTO:
            default:
                return Locale.getDefault();

        }
    }

    /**
     * @return 获取当期虚拟机设置的语言环境
     */
    public String getCurrentLanguage() {
        int lang = getLanguage();
        switch (lang) {
            case LANGUAGE_TYPE_ZH:
                return sContext.getString(R.string.language_cn);
            case LANGUAGE_TYPE_IN:
                return sContext.getString(R.string.language_in);
            case LANGUAGE_TYPE_EN:
                return sContext.getString(R.string.language_en);
            default:
                return sContext.getString(R.string.language_auto);
        }
    }

    public Context setLocal() {
        Locale locale = getCurrentLocal();
        Locale.setDefault(locale);
        Resources res = sContext.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        config.setLocale(locale);
        sContext = sContext.createConfigurationContext(config);
        return sContext;
    }

    /**
     * 设置语言类型
     */
    public void changeAppLanguage() {
        Resources resources = sContext.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = LocaleList.getDefault().get(0);
        } else {
            locale = Locale.getDefault();
        }
        config.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LocaleList localeList = new LocaleList(locale);
            LocaleList.setDefault(localeList);
            config.setLocales(localeList);
            sContext.createConfigurationContext(config);
            Locale.setDefault(locale);
        }

        resources.updateConfiguration(config, dm);
        Log.e("lang", "lang = " + Locale.getDefault().getLanguage());
    }

    /**
     * @return 当前语言环境
     */
    public static boolean isCurrentLanguageZN() {
        String language = Locale.getDefault().toString();
        Log.e("lang", "default-lang= " + language);
        return language.contains("zh_CN");
    }

    /**
     * @return 当前语言环境
     */
    public static boolean isCurrentLanguageEN() {
        String language = Locale.getDefault().toString();
        Log.e("lang", "default-lang= " + language);
        return language.contains("en");
    }


    public static boolean isCurrentLanguageIN() {
        String language = Locale.getDefault().toString();
        Log.e("lang", "default-lang= " + language);
        return language.contains("in");
    }

}
