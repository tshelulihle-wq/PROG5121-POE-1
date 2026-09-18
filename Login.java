public class Login {
    private String username;
    private String password;
    private String cellNumber;
    private String firstName;
    private String lastName;
    private boolean lastLoginSuccess = false;

    public Login(String username, String password, String cellNumber, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.cellNumber = cellNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public Login() {}
    public Login(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean checkUserName() {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity() {
        if (password == null) return false;
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[0-9].*") &&
                password.matches(".*[^a-zA-Z0-9].*");
    }


    public boolean checkCellPhoneNumber() {
        if (cellNumber == null) return false;
        return cellNumber.matches("^\\+27\\d{9}$");
    }

    public boolean loginUser(String inputUser, String inputPass) {
        lastLoginSuccess = inputUser.equals(this.username) && inputPass.equals(this.password);
        return lastLoginSuccess;
    }

    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber()) {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }
        return "User registered successfully.";
    }

    public String returnLoginStatus() {
        if (lastLoginSuccess) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    // Aliases to make Main.java also work
    public boolean checkUserName(String u) { return u.contains("_") && u.length() <= 5; }
    public boolean checkPasswordComplexity(String p) { return p.length() >= 8 && p.matches(".*[A-Z].*") && p.matches(".*[0-9].*") && p.matches(".*[^a-zA-Z0-9].*"); }
    public boolean checkCellPhoneNumber(String c) { return c.matches("^\\+27\\d{9}$"); }
    public String registerUser(String u, String p, String c) { this.username=u; this.password=p; this.cellNumber=c; return registerUser(); }
    public String returnLoginStatus(String u, String p) { loginUser(u,p); return returnLoginStatus(); }
}