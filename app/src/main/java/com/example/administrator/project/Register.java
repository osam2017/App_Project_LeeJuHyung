package com.example.administrator.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    private EditText mAccount;                        //아이디편집
    private EditText mPwd;                            //비밀번호편집
    private EditText mPwdCheck;                       //비밀번호편집
    private Button mSureButton;                       //확인버튼
    private Button mCancelButton;                     //취소버튼
    private UserDataManager mUserDataManager;         //데이터베이스관리
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        mAccount = (EditText) findViewById(R.id.resetpwd_edit_name);
        mPwd = (EditText) findViewById(R.id.resetpwd_edit_pwd_old);
        mPwdCheck = (EditText) findViewById(R.id.resetpwd_edit_pwd_new);

        mSureButton = (Button) findViewById(R.id.register_btn_sure);
        mCancelButton = (Button) findViewById(R.id.register_btn_cancel);

        mSureButton.setOnClickListener(m_register_Listener);      //회원가입화면에 두개 버튼
        mCancelButton.setOnClickListener(m_register_Listener);

        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();                              //데이터베이스 생성
        }

    }
    View.OnClickListener m_register_Listener = new View.OnClickListener() {    //버튼 기능
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.register_btn_sure:                       //확인버튼
                    register_check();
                    break;
                case R.id.register_btn_cancel:                     //취소버튼,로그인화면으로 돌아감
                    Intent intent_Register_to_Login = new Intent(Register.this,Login.class) ;    //User Activity에서 Login Activity
                    startActivity(intent_Register_to_Login);
                    finish();
                    break;
            }
        }
    };
    public void register_check() {                                //확인 버튼 기능
        if (isUserNameAndPwdValid()) {
            String userName = mAccount.getText().toString().trim();
            String userPwd = mPwd.getText().toString().trim();
            String userPwdCheck = mPwdCheck.getText().toString().trim();
            //아이디 중복 확인
            int count=mUserDataManager.findUserByName(userName);
            //아이디 존재 확인
            if(count>0){
                Toast.makeText(this, getString(R.string.name_already_exist, userName), Toast.LENGTH_SHORT).show();
                return ;
            }
            if(userPwd.equals(userPwdCheck)==false){     //비밀번호 서로 다름
                Toast.makeText(this, getString(R.string.pwd_not_the_same), Toast.LENGTH_SHORT).show();
                return ;
            } else {
                UserData mUser = new UserData(userName, userPwd);
                mUserDataManager.openDataBase();
                long flag = mUserDataManager.insertUserData(mUser); //새아이디 넣기
                if (flag == -1) {
                    Toast.makeText(this, getString(R.string.register_fail), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, getString(R.string.register_success), Toast.LENGTH_SHORT).show();
                    Intent intent_Register_to_Login = new Intent(Register.this,Login.class) ;    //User Activity에서 Login Activity
                    startActivity(intent_Register_to_Login);
                    finish();
                }
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
        }else if(mPwdCheck.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.pwd_check_empty),
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
