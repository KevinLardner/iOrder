package com.kevin.ifood;

import android.widget.EditText;

import static com.kevin.ifood.MainActivity.editEmail;

public class UserEmail {

    public static void saveEmail(EditText email){
        email = editEmail;
    }

    public static EditText getEditEmail(){
        return editEmail;
    }
}
