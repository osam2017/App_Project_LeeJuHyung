package com.example.administrator.project;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {                 //로그인 메인화면

    public int pwdresetFlag=0;
    private EditText mAccount;                        //사용자 편집
    private EditText mPwd;                            //비밀번호 편집
    private Button mRegisterButton;                   //회원가입 버튼
    private Button mLoginButton;                      //로그인 버튼
    private Button mCancleButton;                     //취소 버튼
    private CheckBox mRememberCheck;                  //비밀번호기억체크

    private SharedPreferences login_sp;
    private String userNameValue,passwordValue;

    private View loginView;                           //로그인
    private View loginSuccessView;
    private TextView loginSuccessShow;
    private TextView mChangepwdText;
    private UserDataManager mUserDataManager;         //사용자 데이터베이스


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //id를 통한 관련 기능구현
        mAccount = (EditText) findViewById(R.id.login_edit_account);
        mPwd = (EditText) findViewById(R.id.login_edit_pwd);

        mRegisterButton = (Button) findViewById(R.id.login_btn_register);
        mRegisterButton.setOnClickListener(mListener);

        mLoginButton = (Button) findViewById(R.id.login_btn_login);
        mLoginButton.setOnClickListener(mListener);

        mCancleButton = (Button) findViewById(R.id.login_btn_cancle);
        mCancleButton.setOnClickListener(mListener);

        loginView=findViewById(R.id.login_view);
        loginSuccessView=findViewById(R.id.login_success_view);
        loginSuccessShow=(TextView) findViewById(R.id.login_success_show);

        mChangepwdText = (TextView) findViewById(R.id.login_text_change_pwd);
        mChangepwdText.setOnClickListener(mListener);

        mRememberCheck = (CheckBox) findViewById(R.id.Login_Remember);

        login_sp = getSharedPreferences("userInfo", 0);
        String name=login_sp.getString("USER_NAME", "");
        String pwd =login_sp.getString("PASSWORD", "");
        boolean choseRemember =login_sp.getBoolean("mRememberCheck", false);
        boolean choseAutoLogin =login_sp.getBoolean("mAutologinCheck", false);
        //만약 저번사용때 비밀번호기억을 눌렀으면，로그인화면 진입시 비밀번호기억 선택되어있고，사용자 및 비밀번호 자동기입
        if(choseRemember){
            mAccount.setText(name);
            mPwd.setText(pwd);
            mRememberCheck.setChecked(true);
        }






        ImageView image = (ImageView) findViewById(R.id.logo);             //ImageView통한logo표시
        image.setImageResource(R.drawable.logo);

        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();                              //데이터베이스 만들기
        }
        //OnClickListener을 통한 버튼 기능 구현
    }
    OnClickListener mListener = new OnClickListener() {                  //switch 를 통한 버튼기능 구현
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.login_btn_register:                            //로그인 화면의 회원가입 버튼
                    Intent intent_Login_to_Register = new Intent(Login.this,Register.class) ;    //Login Activity에서 User Activity 전환
                    startActivity(intent_Login_to_Register);
                    finish();
                    break;
                case R.id.login_btn_login:                              //로그인 화면의 로그인 버튼
                    login();
                    break;
                case R.id.login_btn_cancle:                             //로그인 화면의 취소 버튼
                    cancel();
                    break;
                case R.id.login_text_change_pwd:                             //로그인 화면의 비밀번호 변경 버튼
                    Intent intent_Login_to_reset = new Intent(Login.this,Resetpwd.class) ;    //Login Activity에서 User Activity
                    startActivity(intent_Login_to_reset);
                    finish();
                    break;
            }
        }
    };

    public void login() {                                              //로그인 버튼 기능
        if (isUserNameAndPwdValid()) {
            String userName = mAccount.getText().toString().trim();    //사용자 및 비밀번호 정보 얻기
            String userPwd = mPwd.getText().toString().trim();
            SharedPreferences.Editor editor =login_sp.edit();
            int result=mUserDataManager.findUserByNameAndPwd(userName, userPwd);
            if(result==1){                                             //1반환 시 사용자 및 비밀번호 Correct
                //사용자 및 비밀번호 저장
                editor.putString("USER_NAME", userName);
                editor.putString("PASSWORD", userPwd);

                //비밀번호기억 확인
                if(mRememberCheck.isChecked()){
                    editor.putBoolean("mRememberCheck", true);
                }else{
                    editor.putBoolean("mRememberCheck", false);
                }
                editor.commit();

                Intent intent = new Intent(Login.this,User.class) ;    //Login Activity에서 User Activity
                startActivity(intent);
                finish();
                Toast.makeText(this, getString(R.string.login_success), Toast.LENGTH_SHORT).show();//로그인 성공 표시
            }else if(result==0){
                Intent intent = new Intent(Login.this,User.class) ;
                startActivity(intent);
                Toast.makeText(this, getString(R.string.login_fail), Toast.LENGTH_SHORT).show();  //로그인 실패 표시
            }
        }
    }
    public void cancel() {           //로그아웃
        if (isUserNameAndPwdValid()) {
            String userName = mAccount.getText().toString().trim();    //이전 입력했던 아이디 및 비밀번호 정보 얻기
            String userPwd = mPwd.getText().toString().trim();
            int result=mUserDataManager.findUserByNameAndPwd(userName, userPwd);
            if(result==1){                                             //1반환시 아이디 및 비밀번호 Correct
//                Intent intent = new Intent(Login.this,User.class) ;    //Login Activity에서 User Activity
//                startActivity(intent);
                Toast.makeText(this, getString(R.string.cancel_success), Toast.LENGTH_SHORT).show();//로그인성공
                mPwd.setText("");
                mAccount.setText("");
                mUserDataManager.deleteUserDatabyname(userName);
            }else if(result==0){
                Toast.makeText(this, getString(R.string.cancel_fail), Toast.LENGTH_SHORT).show();  //로그인 실패
            }
        }

    }

    public boolean isUserNameAndPwdValid() {
        if (mAccount.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.account_empty),
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (mPwd.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.pwd_empty),
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    protected void onResume() {
        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        if (mUserDataManager != null) {
            mUserDataManager.closeDataBase();
            mUserDataManager = null;
        }
        super.onPause();
    }

}
