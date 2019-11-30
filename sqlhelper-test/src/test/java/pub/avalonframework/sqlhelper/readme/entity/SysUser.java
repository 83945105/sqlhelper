package pub.avalonframework.sqlhelper.readme.entity;

import java.lang.String;

public class SysUser {

            /**
     * 主键ID
     */
        private String id;
            /**
     * 用户名
     */
        private String userName;
            /**
     * 登录名
     */
        private String loginName;
    
        public String getId(){
        return this.id;
    }
        public SysUser setId(String id){
        this.id = id;
        return this;
    }
            public String getUserName(){
        return this.userName;
    }
        public SysUser setUserName(String userName){
        this.userName = userName;
        return this;
    }
            public String getLoginName(){
        return this.loginName;
    }
        public SysUser setLoginName(String loginName){
        this.loginName = loginName;
        return this;
    }
        }