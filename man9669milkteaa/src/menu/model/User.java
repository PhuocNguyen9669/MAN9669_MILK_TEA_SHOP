package menu.model;

import java.time.Instant;

public class User {
    private long id;
    private String username;
    private String password;
    private String fullName;
    private String phone;
    private String address;
    private Role role;
    private Instant createdAT;
    private Instant updatedAT;

    public Instant getCreateAT() {
        return createdAT;
    }

    public void setCreateAT(Instant createAT) {
        this.createdAT = createAT;
    }

    public Instant getUpdateAT() {
        return updatedAT;
    }

    public void setUpdateAT(Instant updateAT) {
        this.updatedAT = updateAT;
    }



    public User() {

    }

    public User(long id, String username, String password, String fullName, String phone, String address, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }

    public static User parseUser(String raw) {
        User user = new User();
        String[] fields = raw.split(",");
        user.id = Long.parseLong(fields[0]);
        user.username = fields[1];
        user.password = fields[2];
        user.fullName = fields[3];
        user.phone = fields[4];
        user.address = fields[5];
        user.role = Role.parseRole(fields[6]);
        user.createdAT = Instant.parse(fields[7]);
        String temp = fields[8];
        if (temp != null && !temp.equals("null") )
            user.updatedAT = Instant.parse(temp);
        return user;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Instant getCreatedAT() {
        return createdAT;
    }

    public void setCreatedAT(Instant createdAT) {
        this.createdAT = createdAT;
    }

    public Instant getUpdatedAT() {
        return updatedAT;
    }

    public void setUpdatedAT(Instant updatedAT) {
        this.updatedAT = updatedAT;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("%d,%s,%s,%s,%s,%s,%s,%s,%s",
                id,
                username,
                password,
                fullName,
                phone,
                address,
                role,
                createdAT,
                updatedAT
        );
    }
}
