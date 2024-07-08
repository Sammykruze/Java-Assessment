package question1;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.validator.routines.EmailValidator;

public class UserInputValidation {

    public static void main(String[] args) {
        String username = "user";
        String email = "user@example.com";
        String password = "Passw0rd!";
        LocalDate dateOfBirth = LocalDate.of(2000, 1, 1);

        List<String> validationErrors = validateInput(username, email, password, dateOfBirth);
        if (validationErrors.isEmpty()) {
            System.out.println("All validations passed");
        } else {
            validationErrors.forEach(System.out::println);
        }
    }

    public static List<String> validateInput(String username, String email, String password, LocalDate dateOfBirth) {
        return Stream.of(
                validateUsername(username),
                validateEmail(email),
                validatePassword(password),
                validateDateOfBirth(dateOfBirth)
        ).flatMap(List::stream).collect(Collectors.toList());
    }

    private static List<String> validateUsername(String username) {
        List<String> errors = new ArrayList<>();
        if (username == null || username.isEmpty() || username.length() < 4) {
            errors.add("Username: must be at least 4 characters long and not empty.");
        }
        return errors;
    }

    private static List<String> validateEmail(String email) {
        List<String> errors = new ArrayList<>();
        if (email == null || email.isEmpty() || !EmailValidator.getInstance().isValid(email)) {
            errors.add("Email: must be a valid email address and not empty.");
        }
        return errors;
    }

    private static List<String> validatePassword(String password) {
        List<String> errors = new ArrayList<>();
        String passwordPattern = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$";
        if (password == null || password.isEmpty() || !Pattern.matches(passwordPattern, password)) {
            errors.add("Password: must be at least 8 characters long, contain at least one uppercase letter, one number, and one special character.");
        }
        return errors;
    }

    private static List<String> validateDateOfBirth(LocalDate dateOfBirth) {
        List<String> errors = new ArrayList<>();
        if (dateOfBirth == null || Period.between(dateOfBirth, LocalDate.now()).getYears() < 16) {
            errors.add("Date of Birth: must be at least 16 years old.");
        }
        return errors;
    }
}
