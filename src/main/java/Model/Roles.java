package Model;

public class Roles {
    public String roleID;
    public String roleName;

    public Roles(String roleID, String roleName) {
        this.roleID = roleID;
        this.roleName = roleName;
    }

    public Roles() {
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }
}
