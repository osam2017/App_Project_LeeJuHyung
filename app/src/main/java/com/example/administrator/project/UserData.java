package com.example.administrator.project;
/**
 * Created by Administrator on 2017/10/17.
 */

public class UserData {
    private String userName;                  //사용자명
    private String userPwd;                   //비밀번호
    private int userId;                       //사용자 id
    public int pwdresetFlag=0;
    //사용자명 얻기
    public String getUserName() {             //获取用户名
        return userName;
    }
    //사용자명 설정
    public void setUserName(String userName) {  //输入用户名
        this.userName = userName;
    }
    //비밀번호 얻기
    public String getUserPwd() {                //获取用户密码
        return userPwd;
    }
    //비밀번호 설정
    public void setUserPwd(String userPwd) {     //输入用户密码
        this.userPwd = userPwd;
    }
    //id 얻기
    public int getUserId() {                   //获取用户ID号
        return userId;
    }
    //id 설정
    public void setUserId(int userId) {       //设置用户ID号
        this.userId = userId;
    }

   /* public UserData(String userName, String userPwd, int userId) {    //사용자 정보
        super();
        this.userName = userName;
        this.userPwd = userPwd;
        this.userId = userId;
    }*/

    public UserData(String userName, String userPwd) {  //사용자명 및 비밀번호 설정
        super();
        this.userName = userName;
        this.userPwd = userPwd;
    }

}
