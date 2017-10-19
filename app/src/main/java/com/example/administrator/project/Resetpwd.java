package com.example.administrator.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Resetpwd extends AppCompatActivity {
    private EditText mAccount;                        //아이디
    private EditText mPwd_old;                        //이전비밀번호
    private EditText mPwd_new;                        //새비밀번호
    private EditText mPwdCheck;                       //비밀번호 확인
    private Button mSureButton;                       //확인 버튼
    private Button mCancelButton;                     //취소 버튼
    private UserDataManager mUserDataManager;         //데이터베이스
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resetpwd);
//        layout.setOrientation(RelativeLayout.VERTICAL).
        mAccount = (EditText) findViewById(R.id.resetpwd_edit_name);
        mPwd_old = (EditText) findViewById(R.id.resetpwd_edit_pwd_old);
        mPwd_new = (EditText) findViewById(R.id.resetpwd_edit_pwd_new);
        mPwdCheck = (EditText) findViewById(R.id.resetpwd_edit_pwd_check);

        mSureButton = (Button) findViewById(R.id.resetpwd_btn_sure);
        mCancelButton = (Button) findViewById(R.id.resetpwd_btn_cancel);

        mSureButton.setOnClickListener(m_resetpwd_Listener);      //로그인 화면 두개 버튼
        mCancelButton.setOnClickListener(m_resetpwd_Listener);
        //mCancelButton.setOnClickListener(m_resetpwd_Listener);

        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();                              //데이터베이스 생성
        }

    }
    View.OnClickListener m_resetpwd_Listener = new View.OnClickListener() {    //버튼 기능
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.resetpwd_btn_sure:                       //확인 버튼 누를시
                    resetpwd_check();
                    break;
                case R.id.resetpwd_btn_cancel:                     //취소버튼 누를시,로그인 화면으로 전환
                    Intent intent_Resetpwd_to_Login = new Intent(Resetpwd.this,Login.class) ;    //Resetpwd Activity에서 Login Activity
                    startActivity(intent_Resetpwd_to_Login);
                    finish();
                    break;
            }
        }
    };
    public void resetpwd_check() {                                //확인 버튼 기능
        if (isUserNameAndPwdValid()) {
            String userName = mAccount.getText().toString().trim();
            String userPwd_old = mPwd_old.getText().toString().trim();
            String userPwd_new = mPwd_new.getText().toString().trim();
            String userPwdCheck = mPwdCheck.getText().toString().trim();
            int result=mUserDataManager.findUserByNameAndPwd(userName, userPwd_old);
            if(result==1){                                             //1반환 시아이디 및 비밀번호 Correct,계속 진행
                if(userPwd_new.equals(userPwdCheck)==false){           //两次密码输入不一样
                    Toast.makeText(this, getString(R.string.pwd_not_the_same), Toast.LENGTH_SHORT).show();
                    return ;
                } else {
                    UserData mUser = new UserData(userName, userPwd_new);
                    mUserDataManager.openDataBase();
                    boolean flag = mUserDataManager.updateUserData(mUser);
                    if (flag == false) {
                        Toast.makeText(this, getString(R.string.resetpwd_fail), Toast.LENGTH_SHORT).show();
                    }else{

                        Toast.makeText(this, getString(R.string.resetpwd_success), Toast.LENGTH_SHORT).show();

                        mUser.pwdresetFlag=1;
                        Intent intent_Register_to_Login = new Intent(Resetpwd.this,Login.class) ;    //User Activity에서 Login Activity
                        startActivity(intent_Register_to_Login);
                        finish();
                    }
                }
            }else if(result==0){                                       //0반환시 아이디 및 비밀번호 틀림，다시 입력
                Toast.makeText(this, getString(R.string.pwd_not_fit_user), Toast.LENGTH_SHORT).show();
                return;
            }




        }
    }
    public boolean isUserNameAndPwdValid() {
        String userName = mAccount.getText().toString().trim();
        //아이디 중복 확인
        int count=mUserDataManager.findUserByName(userName);
        //아이디가 없을시，String 출력
        if(count<=0){
            Toast.makeText(this, getString(R.string.name_not_exist, userName), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mAccount.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.account_empty), Toast.LENGTH_SHORT).show();
            return false;
        } else if (mPwd_old.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.pwd_empty), Toast.LENGTH_SHORT).show();
            return false;
        } else if (mPwd_new.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.pwd_new_empty), Toast.LENGTH_SHORT).show();
            return false;
        }else if(mPwdCheck.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.pwd_check_empty), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}

