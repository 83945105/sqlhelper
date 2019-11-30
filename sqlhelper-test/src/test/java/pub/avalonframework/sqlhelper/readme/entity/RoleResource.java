package pub.avalonframework.sqlhelper.readme.entity;

import java.lang.String;
import java.lang.Long;

public class RoleResource {

            /**
     * 
     */
        private String id;
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
        private String resourceId;
            /**
     * 
     */
        private String resourceName;
            /**
     * 
     */
        private Long sortIndex;
    
        public String getId(){
        return this.id;
    }
        public RoleResource setId(String id){
        this.id = id;
        return this;
    }
            public String getRoleId(){
        return this.roleId;
    }
        public RoleResource setRoleId(String roleId){
        this.roleId = roleId;
        return this;
    }
            public String getRoleName(){
        return this.roleName;
    }
        public RoleResource setRoleName(String roleName){
        this.roleName = roleName;
        return this;
    }
            public String getResourceId(){
        return this.resourceId;
    }
        public RoleResource setResourceId(String resourceId){
        this.resourceId = resourceId;
        return this;
    }
            public String getResourceName(){
        return this.resourceName;
    }
        public RoleResource setResourceName(String resourceName){
        this.resourceName = resourceName;
        return this;
    }
            public Long getSortIndex(){
        return this.sortIndex;
    }
        public RoleResource setSortIndex(Long sortIndex){
        this.sortIndex = sortIndex;
        return this;
    }
        }