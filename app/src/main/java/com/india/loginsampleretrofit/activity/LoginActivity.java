package com.india.loginsampleretrofit.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.Spanned;

import android.util.Log;
import android.util.Patterns;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.google.gson.Gson;
import com.india.loginsampleretrofit.R;
import com.india.loginsampleretrofit.network.AppNetworkStatus;
import com.india.loginsampleretrofit.retrofit.Constant;
import com.india.loginsampleretrofit.retrofit.RestInterface;
import com.india.loginsampleretrofit.retrofit.RetrofitUtil;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

import static com.basgeekball.awesomevalidation.ValidationStyle.UNDERLABEL;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Snackbar snackbar;
    private Button btnLogin;
    LinearLayout loginLayout;
    private EditText etPassword, etEmail;
    private AwesomeValidation mAwesomeValidation;
    private String mEmail, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**    Transparent Status Bar */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_login);
        initializationView();

        btnLogin.setOnClickListener(this);
        findViewById(R.id.tv_sign_up).setOnClickListener(this);

    }

    private void initializationView() {
        etEmail = findViewById(R.id.input_email);
        etPassword = findViewById(R.id.input_password);
        btnLogin = findViewById(R.id.btn_login);
        loginLayout = findViewById(R.id.loginLayout);
        btnLogin.setEnabled(false); //Disable the button onClick

        /* To restrict Space Bar in Keyboard */
        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (Character.isWhitespace(source.charAt(i))) {
                        return "";
                    }
                }
                return null;
            }
        };
        etEmail.setFilters(new InputFilter[]{filter});
        etPassword.setFilters(new InputFilter[]{filter});

        /*Validation*/
        addValidationToViews();
    }

    private void addValidationToViews() {
        mAwesomeValidation = new AwesomeValidation(UNDERLABEL);
        mAwesomeValidation.setContext(this);
        mAwesomeValidation.addValidation(this, R.id.input_email, Patterns.EMAIL_ADDRESS, R.string.reg_error_email);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (AppNetworkStatus.getInstance(this).isOnline()) {
                    loginSubmit();
                } else {
                    snackbar = Snackbar.make(loginLayout, "No internet connection!", Snackbar.LENGTH_LONG);
                    View sbView = snackbar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(getResources().getColor(R.color.colorPrimary));
                    snackbar.show();
                }
                break;
            case R.id.tv_sign_up:
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                break;

        }

    }

    private void loginSubmit() {
        mEmail = etEmail.getText().toString().trim();
        mPassword = etPassword.getText().toString().trim();
        Log.e("mail", mEmail);
        Log.e("password", mPassword);

        RestInterface service = RetrofitUtil.retrofit(Constant.BASE_URL);

        final ProgressDialog pd = new ProgressDialog(LoginActivity.this);
        pd.setMessage("Please Wait...");
        pd.show();
        Call<ResponseBody> call = service.FetchingBookLottoService("","","");

       // Log.e("QuestionResponse", ">>" + new Gson().toJson(response.body()));


        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

                pd.dismiss();
               // BookLottoResponse bookLottoResponse = response.body();
                // Log.e("BookLottoResponse",">>");
//                try {
//                    if (bookLottoResponse != null) {
//                        if (!bookLottoResponse.getError())
//                            Toast.makeText(LottoActivity.this, "" + bookLottoResponse.getMessage(), Toast.LENGTH_LONG).show();
//                        tvOne.setText("");
//                        tvTwo.setText("");
//                        tvThree.setText("");
//                        tvFour.setText("");
//                        tvFive.setText("");
//                    }
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
//                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                pd.dismiss();

            }
        });

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
    }
}
