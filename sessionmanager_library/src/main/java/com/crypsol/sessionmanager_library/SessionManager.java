package com.crypsol.sessionmanager_library;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;
import static android.Manifest.permission.SEND_SMS;

public class SessionManager  {
    // TODO 221 We need to establish the usual persistence hierarchy; DB <==> Flash <==> RAM
    // TODO  Such that we can reduce the settings system to be efficient and compact. Same goes for the
    // TODO  getMyAppSettings.php.
    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();


    // Shared Preferences
    static SharedPreferences pref;

    static SharedPreferences.Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String KEY_DASHBOARDID = "DashBoardID";
    private static final String KEY_SHOWID = "ShowID";
    private static final String PREF_NAME = "Choop.Session";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_IMEI = "ImeiNumber";
    private static final String KEY_PHONE = "PhoneNumber";
    private static final String KEY_PHONE_REG = "phone";
    private static final String KEY_APP_NAME = "appname";
    private static final String KEY_INVITEEPHONE = "inviteephone";
    private static final String KEY_INVITEENAME = "names";
    private static final String KEY_MyISOLANGUAGE = "language";
    private static final String KEY_MyISOCOUNTRY = "country";
    private static final String KEY_MyE_164_PREFIX = "prefix";
    private static final String KEY_PADDING = "padding";
    private static final String KEY_MyNSN = "NSN";
    private static final String KEY_DASHBOARDSCREENSHOT = "screenshot";
    private static final String KEY_LANGUAGECOUNTRY = "languagecountry";
    private static final String KEY_CURRENCYANDACCUMULATEDAMOUT = "currencyAmount";
    private static final String KEY_WITHDRAWABLEAMOUNT = "withdrawableamount";
    private static final String KEY_CURRENCY = "currency";
    private static final String KEY_CURRENCYlIST = "currencyList";
    private static final String KEY_ERRORREPORTING  = "errorReportingMessage";


    private static final String KEY_CONTACTSELECTED = "contacts";
    private static final String KEY_MyCONTACTLIST = "contactlist";
    private static final String KEY_CHECK_IF_LANG_COUNTRY_IS_SET = "county_lang";

    private static final String KEY_CHARGEDRATE = "chargedrate";
    private static final String KEY_PAYOUTFEE = "payoutfee";
    private static final String KEY_PAYABLEAFTERDEDUCTIONS = "payableamout";

    private static final String KEY_TILEAVAILABILITY = "tileAvailability";
    private static final String KEY_APPSETTINGS = "appSettings";
    private static final String KEY_APPSOP = "asppSop";
    private static final String KEY_APPTOAST = "asppToast";

    private static final String KEY_GENERALAPPSETTINGS = "generalAppsettings";
    private static final String KEY_APPSTRINGS = "appstrings";
    private static final String KEY_URL_PATH = "url_path";
    private static final String KEY_ALERTDIALOGSTYLING = "alertedittext_stylings";

    public static final int  UI_CANCEL = 0;
    public static final int  UI_OK = 1;

    public SessionManager(Context context) {
        this._context = context;
        System.out.println("***** SessionManager(PREF_NAME:"+PREF_NAME+"), context="+((context==null)?"Null":"Not Null"));
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    //// LOG IN STATUS BEING SET...

    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);        // commit changes
        editor.commit();
        Log.d(TAG, "User login session modified!");
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    //// IMEI NUMBER SESSIONED
    // TODO need to check for 2 IMEI numbers, and decide on how to store these in the key-value system

    public static void setIMEI (String IMEI) {
        editor.putString(KEY_IMEI, IMEI);
        editor.commit();
        Log.d(TAG, "IMEI "+IMEI+" recorded");
    }

    public static String getIMEI () {
        return pref.getString(KEY_IMEI, "");
    }

    public static void setPadding (String padding) {
        editor.putString(KEY_PADDING, padding);
        editor.commit();
        Log.d(TAG, "PADDING "+padding+" recorded");
    }

    public static String getPADDING () {
        return pref.getString(KEY_PADDING, "");
    }

    //////// SETTING AND GETTING APP NAME
    public static void setAPPNAME (String APP_NAME) {
        editor.putString(KEY_APP_NAME, APP_NAME);
        editor.commit();
        Log.d(TAG, "IMEI "+APP_NAME+" recorded");
    }

    public static String getAPPNAME () {
        return pref.getString(KEY_APP_NAME, "");
    }

    ////////// PHONE NUMBER FOR THE PERSON BEEN INVITED
    public static void setINVITEEPHONE (String INVITEEPHONE) {
        editor.putString(KEY_INVITEEPHONE, INVITEEPHONE);
        editor.commit();
        Log.d(TAG, "IMEI "+INVITEEPHONE+" recorded");
    }

    public static String getINVITEEPHONE () {
        return pref.getString(KEY_INVITEEPHONE, "");
    }

    public static void setINVITEENAME (String INVITEENAME) {
        editor.putString(KEY_INVITEENAME, INVITEENAME);
        editor.commit();
        Log.d(TAG, "INVITEENAME "+INVITEENAME+" recorded");
    }

    public static String getINVITEEINVITEENAME () {
        return pref.getString(KEY_INVITEENAME, "");
    }

    ////////// SETTING MY ISOLANGUAGE
    public static void setMyISOLANGUAGE (String MyISOLANGUAGE) {
        editor.putString(KEY_MyISOLANGUAGE, MyISOLANGUAGE);
        editor.commit();
        Log.d(TAG, "KEY_MyISOLANGUAGE "+MyISOLANGUAGE+" recorded");
    }

    public static String getMyISOLANGUAGE () {
        return pref.getString(KEY_MyISOLANGUAGE, "");
    }

    ////////// SETTING MY ISOCOUNTRY
    public static void setMyISOCOUNTRY (String MyISOCOUNTRY) {
        editor.putString(KEY_MyISOCOUNTRY, MyISOCOUNTRY);
        editor.commit();
        Log.d(TAG, "KEY_MyISOCOUNTRY "+MyISOCOUNTRY+" recorded");
    }

    public static String getMyISOCOUNTRY () {
        return pref.getString(KEY_MyISOCOUNTRY, "");
    }

    ////////// SETTING MY E_164_PREFIX
    public static void setMyE_164_PREFIX (String MyE_164_PREFIX) {
        editor.putString(KEY_MyE_164_PREFIX, MyE_164_PREFIX);
        editor.commit();
        Log.d(TAG, "KEY_MyE_164_PREFIX "+MyE_164_PREFIX+" recorded");
    }

    public static String getMyE_164_PREFIX () {
        return pref.getString(KEY_MyE_164_PREFIX, "");
    }

    ////////// SETTING MY NATIONAL SIGNIFICANT NUMBER
    public static void setMyNSN (String MyNSN) {
        editor.putString(KEY_MyNSN, MyNSN);
        editor.commit();
        Log.d(TAG, "KEY_MyNSN "+MyNSN+" recorded");
    }

    public static String getMyNSN () {
        return pref.getString(KEY_MyNSN, "");
    }


    ////////// STORING DASHBORD IMAGE
    public static void setDashboardImage (String screenshot) {
        editor.putString(KEY_DASHBOARDSCREENSHOT , screenshot);
        editor.commit();
        Log.d(TAG, "KEY_DASHBOARDSCREENSHOT "+screenshot+" recorded");
    }

    public static String getDashboardImage () {
        return pref.getString(KEY_DASHBOARDSCREENSHOT, "");
    }


    // Setting map language in settings
    public static void setLanguageCountry (List<String> langCountry) {
        editor.putString(KEY_LANGUAGECOUNTRY , String.valueOf(langCountry));
        editor.commit();
        Log.d(TAG, "KEY_LANGUAGECOUNTRY "+langCountry+" recorded");
    }

    public static String getLanguageCountry () {
        return pref.getString(KEY_LANGUAGECOUNTRY, "");
    }

    //seting userdata such as currency and accumulated amount
    public static void setCurrencyAmount (String currency) {
        editor.putString(KEY_CURRENCYANDACCUMULATEDAMOUT, currency);
        editor.commit();
        Log.d(TAG, "KEY_CURRENCYANDACCUMULATEDAMOUT "+currency+" recorded");
    }

    public static String getCurrencyAmount () {
        return pref.getString(KEY_CURRENCYANDACCUMULATEDAMOUT, "");
    }

    //seting  accumulated amount
    public static void setWithdrawableAmount (String amount) {
        editor.putString(KEY_WITHDRAWABLEAMOUNT, amount);
        editor.commit();
        System.out.println( " Setting KEY_WITHDRAWABLEAMOUNT "+amount+" recorded");
    }

    public static String getWithdrawableAmount () {
        System.out.println( " Getting KEY_WITHDRAWABLEAMOUNT "+pref.getString(KEY_WITHDRAWABLEAMOUNT, ""));
        return pref.getString(KEY_WITHDRAWABLEAMOUNT, "");
    }

    //seting  accumulated amount
    public static void setCurrency (String currency) {
        editor.putString(KEY_CURRENCY, currency);
        editor.commit();
        Log.d(TAG, "KEY_CURRENCY "+currency+" recorded");
    }

    public static String getCurrency () {
        return pref.getString(KEY_CURRENCY, "");
    }

    // Setting currency to use
    public static void setCurrenyList (ArrayList currencyList) {
        editor.putString(KEY_CURRENCYlIST , String.valueOf(currencyList));
        editor.commit();
        System.out.println(TAG+ " KEY_CURRENCYlIST "+currencyList+" recorded");
    }

    public static String getCurrenyList  () {
        return pref.getString(KEY_CURRENCYlIST, null);
    }

/*
    ////////// Seeting Contacts in List
    public void setCONTACTLIST ( String  MyCONTACTLIST) {
        editor.putString (KEY_MyCONTACTLIST, MyCONTACTLIST);
        editor.commit();
        Log.d(TAG, "KEY_MYLANGUAGE "+MyCONTACTLIST+" recorded");

    }
    public  String  getCONTACTLIST () {
        //return pref.getString(KEY_MyCONTACTLIST, "");
        return pref.getString(KEY_MyCONTACTLIST,"" );

    }
*/



    //// PHONE NUMBER SESSIONED
    // TODO need to check for 2 phone numbers, and decide on how to store these in the key-value system

    public static void setPhone (String phone) {
        editor.putString(KEY_PHONE, phone);
        editor.commit();
        Log.d(TAG, "Phone "+phone+" recorded");
    }

    //remove phone
    public static void removePhone () {
        editor.remove(KEY_PHONE);
        editor.commit();
        Log.d(TAG, "Phone removed...");
    }

    public static String getPhone () {
        return pref.getString(KEY_PHONE, "");
    }

    //TOTAL CONTACT SELECTED BY USER DURING INVIATION
    public static void setContactselected (String contact) {
        editor.putString(KEY_CONTACTSELECTED, contact);
        editor.commit();
        Log.d(TAG, "Phone "+contact+" recorded");
    }

    public static String getContactselected () {
        return pref.getString(KEY_CONTACTSELECTED, "");
    }

    //// Current SHOWID being set, prior to displaying it.

    public static void setCurrentShowID(String showID) {
        editor.putString(KEY_SHOWID, showID);
        editor.commit();
        Log.d(TAG, "Current ShowID "+showID+" recorded");
    }

    public static String getCurrentShowID () {
        return pref.getString(KEY_SHOWID, "");
    }

    //// Current SHOWID being set, prior to displaying it.

    public static void setCurrentDashBoardID(String dashBoardID) {
        editor.putString(KEY_DASHBOARDID, dashBoardID);
        editor.commit();
        Log.d(TAG, "Current DashBoardD "+dashBoardID+" recorded");
    }

    public static String getCurrentDashBoardID () {
        // TODO Find out how to set a default value when SHOWID has not been set yet.
        // TODO Find out how to set this from the startup of the app.
        // TODO Set this as a precondition for the database - to contain that particular ShowID.
        // TODO THis value could be the users guide for the APP, as a showreel.
        return pref.getString(KEY_DASHBOARDID, "");
    }

    //// These are the String-settings, as in R.string.xxxxxxxxxxxxxxxx values.
    // We have added the language and name joined by $, such that for instance it becomes:
    // "ENG$greeting" as the name, while we then use the settingValue to hold the value to be set
    // inside of the Shared Preferences...

    public static void setSetting (String domain, String settingName, String settingValue) {
        // TODO 080 We need to make a method to LOAD these values from the database (on server)
        editor.putString(domain+"$"+settingName, settingValue);
        editor.commit();
        Log.d(TAG, "Current DashBoardD "+settingValue+" recorded");
    }

    public static String getSetting (String domain, String settingName) {
        // TODO 081 If the settings are not there, we need a fall back method to fetch them from server.
        // TODO 082 If the settings (for instance for a language) are not present, we need to have fallback to certain default language (ie. english)
        String returnVal = pref.getString(domain+"$"+settingName, "");
        return returnVal == null ? "0" : returnVal;
    }

    /**
     *
     * Phone settings by generating a MyIMEI in replacement of the system IMEI number.
     * @return
     */
    public static String checkAndGenerateImei () {
        String myIMEI = getIMEI();
        if (myIMEI.length() == 0) {
            myIMEI = SharedMethods_libs.generateMyImeiNo();
            setIMEI(myIMEI);
        }
        return myIMEI;
    }

    /**
     * restoreSettings() will pick the stored JSONObject based settings, which again originates from the database.
     * This method here can be implemented like a straight inline recovery method, or as a method where the
     * user can choose which parameters to restore to default (advanced)
     */
    public static void restoreSettings () {
        // TODO 290 Here we will restore all settings from the database to the Shared preferences, and from there into the memory based settings
        //  - again - based on the AppName, and the Language.
    }

    // TODO 218 Define this function well, .... Requesting permission from the user
    // TODO 220 We need to define these permits in hashMap with {Permit,Permission} pairs, just like IMEI, such that we can
    //  refer each individual setting at each point where we need to know, just by SessionManager.checkPermission("ReadContacts") etc. and that
    //  one will then return a boolean if set or not.
    public static boolean getUserPermissionAndImei(Activity pActivity) {
        checkAndGenerateImei();
        if (
                ActivityCompat.checkSelfPermission(pActivity, READ_CONTACTS)       != PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(pActivity, READ_PHONE_NUMBERS)  != PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(pActivity, READ_SMS)            != PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(pActivity, SEND_SMS)            != PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(pActivity, READ_PHONE_STATE)    != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(pActivity,
                    new String[]{READ_CONTACTS, SEND_SMS, READ_SMS, READ_PHONE_NUMBERS, READ_PHONE_STATE},
                    UI_OK);
            return false; // invariance: We did not have all permits, so we have asked, and continuance must be dealt with in onRequestPermission in the local activity
        } else
            return true;  // invariance: we can continue execution by simply calling what comes after, as we have the permit
    }

    public static void setTileAvailability (String tileAvailability) {
        editor.putString(KEY_TILEAVAILABILITY, tileAvailability);
        editor.commit();
        System.out.println( " Setting KEY_TILEAVAILABILITY "+tileAvailability+" recorded");
    }

    public static String getTileAvailability  () {
        System.out.println( " Getting KEY_TILEAVAILABILITY "+pref.getString(KEY_TILEAVAILABILITY, ""));
        return pref.getString(KEY_TILEAVAILABILITY, "");
    }


    /// setting on off system out
    public static void setSOP (String sop) {
        editor.putString(KEY_APPSOP, sop);
        editor.commit();
        System.out.println( " Setting KEY_APPSOP "+sop+" recorded");
    }

    public static String getSOP   () {
        System.out.println( " Getting KEY_APPSOP "+pref.getString(KEY_APPSOP, ""));
        return pref.getString(KEY_APPSOP, "");
    }

    /// setting on off  toast
    public static void setTOAST (String toast) {
        editor.putString(KEY_APPTOAST, toast);
        editor.commit();
        System.out.println( " Setting KEY_APPTOAST "+toast+" recorded");
    }

    public static String getTOAST   () {
        System.out.println( " Getting KEY_APPTOAST "+pref.getString(KEY_APPTOAST, ""));
        return pref.getString(KEY_APPTOAST, "");
    }

    /// SETINGS
    public static void setGENERALAPPSETTINGS (JSONObject object) {
        Gson gson = new Gson();
        String jsonText = gson.toJson(object);
        editor.putString(KEY_GENERALAPPSETTINGS, jsonText);
        editor.apply();
    }

    public static JSONObject getGENERALAPPSETTINGS   () {
        String objectStored = pref.getString(KEY_GENERALAPPSETTINGS, "");
        Gson convert = new Gson();
        JSONObject jsonObject = convert.fromJson(objectStored, JSONObject.class);
        return jsonObject;

    }

    //App STRING settings
    public static void setAppString (JSONObject  appString) {
        Gson gson = new Gson();
        String jsonText = gson.toJson(appString);
        editor.putString(KEY_APPSTRINGS, jsonText);
        editor.apply();
    }

    public static JSONObject getAppString   () {
        String objectStored = pref.getString(KEY_APPSTRINGS, "");
        Gson convert = new Gson();
        JSONObject jsonObject = convert.fromJson(objectStored, JSONObject.class);
        return jsonObject;

    }

    // Setting Url Path
    public static void setURL (String url) {
        editor.putString(KEY_URL_PATH, url);
        editor.commit();
        System.out.println( " Setting KEY_URL_PATH "+url+" recorded");
    }

    public static String getURL () {
        System.out.println( " Getting KEY_URL_PATH "+pref.getString(KEY_URL_PATH, ""));
        return pref.getString(KEY_URL_PATH, "");
    }

    // Setting Alert dialog editTextStylings
    public static void setAlertDialogStyling (int style) {
        editor.putInt(KEY_ALERTDIALOGSTYLING, style);
        editor.commit();
        System.out.println( " Setting KEY_URL_PATH "+style+" recorded");
    }

    public static int getAlertDialogStyling () {
        System.out.println( " Getting KEY_URL_PATH "+pref.getInt(KEY_URL_PATH, 0));
        return pref.getInt(KEY_ALERTDIALOGSTYLING, 0);
    }

}
