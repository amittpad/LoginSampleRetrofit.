package com.india.loginsampleretrofit.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.india.loginsampleretrofit.R;
import com.india.loginsampleretrofit.network.AppNetworkStatus;


import static com.basgeekball.awesomevalidation.ValidationStyle.UNDERLABEL;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private Snackbar snackbar;
    private LinearLayout signUpLineraLayout;
    private TextView alreadyLoginBtn;
    private Button btnSignUp;
    private EditText etPassword;
    private AwesomeValidation mAwesomeValidation;
    private String mPassword, mCompanyName, mCompanyEmail, mCompanyPhoneNo, mCompanyAddress, mCountryId, mStateId, mPackageId;
    private EditText etEmail,etMobile;
    private String mEmail;
    private String mMobileNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        Transparent Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_signup);
        initializationView();


        btnSignUp.setOnClickListener(this);
        alreadyLoginBtn.setOnClickListener(this);
    }

    private void initializationView() {
        signUpLineraLayout = findViewById(R.id.signUpLayout);
        alreadyLoginBtn = findViewById(R.id.tv_already_login);
        etPassword = findViewById(R.id.input_password);
        etEmail = findViewById(R.id.input_email);
        etMobile = findViewById(R.id.input_contact);
        btnSignUp = findViewById(R.id.btn_sign_up);


        /**Validated to edit text*/
        addValidationToViews();


    }



    private void addValidationToViews() {
        mAwesomeValidation = new AwesomeValidation(UNDERLABEL);
        mAwesomeValidation.setContext(this);
        mAwesomeValidation.addValidation(this, R.id.input_email, RegexTemplate.NOT_EMPTY, R.string.hint_email);

        // to validate the confirmation of another field
        //String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
        String regexPassword = ".{5,}";
        mAwesomeValidation.addValidation(this, R.id.input_password, regexPassword, R.string.hint_password);
        mAwesomeValidation.addValidation(this, R.id.input_contact, "^[+]?[0-9]{10,13}$", R.string.hint_contact);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sign_up:
                if (AppNetworkStatus.getInstance(this).isOnline()) {
                    signUpSubmit();
                } else {
                    snackbar = Snackbar.make(signUpLineraLayout, "No internet connection!", Snackbar.LENGTH_LONG);
                    View sbView = snackbar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(getResources().getColor(R.color.colorPrimary));
                    snackbar.show();
                }
                break;
            case R.id.tv_already_login:
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                break;
        }
    }

    private void signUpSubmit() {
        if (mAwesomeValidation.validate()) {
            mEmail = etEmail.getText().toString().trim();
            mMobileNo = etMobile.getText().toString().trim();
            mPassword = etPassword.getText().toString().trim();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }



}
