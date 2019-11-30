package pub.avalonframework.sqlhelper.readme.entity;

import java.lang.String;
import java.lang.Long;

public class UserRole {

            /**
     * 
     */
        private String id;
            /**
     * 
     */
        private String userId;
            /**
     * 
     */
        private String roleId;
            /**
     * 
     */
        private String roleName;
            /**
     * 
     */
        private Long sortIndex;
    
        public String getId(){
        return this.id;
    }
        public UserRole setId(String id){
        this.id = id;
        return this;
    }
            public String getUserId(){
        return this.userId;
    }
        public UserRole setUserId(String userId){
        this.userId = userId;
        return this;
    }
            public String getRoleId(){
        return this.roleId;
    }
        public UserRole setRoleId(String roleId){
        this.roleId = roleId;
        return this;
    }
            public String getRoleName(){
        return this.roleName;
    }
        public UserRole setRoleName(String roleName){
        this.roleName = roleName;
        return this;
    }
            public Long getSortIndex(){
        return this.sortIndex;
    }
        public UserRole setSortIndex(Long sortIndex){
        this.sortIndex = sortIndex;
        return this;
    }
        }