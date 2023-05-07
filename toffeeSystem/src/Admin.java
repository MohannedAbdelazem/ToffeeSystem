public class Admin extends unregisteredUser{
    private int id;
    private String name;
    private char gender;
    private String address;
    private String Email;
    Admin(int id, String name, char gender, String address, String email){
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.Email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
