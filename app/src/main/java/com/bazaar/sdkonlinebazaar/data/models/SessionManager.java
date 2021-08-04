package com.bazaar.sdkonlinebazaar.data.models;

/*
public class SessionManager {

    private SharedPreferences pref;
    private Editor editor;
    private Context _context;
    private int PRIVATE_MODE = 0;



    private static final String PREF_NAME = "AndroidHivePref";
    private static final String IS_SAVED = "IsSaved";
    public static final String KEY_NAME = "name";
    public static final String KEY_PASSWORD = "password";
    public static final int KEY_PerIDatpl = 0;
    public static final int KEY_PerIDcplppl = 0;
    public static final int KEY_PerIDppl = 0 ;

    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(LoginResponse user)
    {

        editor.putBoolean(IS_SAVED, true);
        editor.putString(KEY_NAME, user.getLoginName());
        editor.putString(KEY_PASSWORD, user.getLoginPassword());
        editor.putInt(KEY_PerIDatpl, Constants.PerIDatpl);
        editor.putInt(KEY_PerIDcplppl,  Constants.PerIDcplppl);
        editor.putInt(KEY_PerIDppl,  Constants.PerIDppl);
        editor.putBoolean("isValid", true);
        editor.commit();

    }

    public LoginResponse getUserDetails()
    {
        User user=new User();
        user.setUserName( pref.getString(KEY_NAME, null));
        user.setUserPassword( pref.getString(KEY_NAME, null));
        user.setCustomerId(pref.getInt(KEY_CUSTOMER_ID, Constants.notDefined));
        user.setOrganizationId(pref.getInt(KEY_ORG_ID, Constants.notDefined));
        user.setBranchId(pref.getInt(KEY_BRANCH_ID, Constants.notDefined));
        editor.commit();
        return user;
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }

    public boolean isSaved(){
        return pref.getBoolean(IS_SAVED, false);
    }
}*/
