package com.memorynotfound.spring.security.test;

import com.memorynotfound.spring.security.web.dto.UserRegistrationDto;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class PasswordConstraintValidatorTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testInvalidPassword() {
        UserRegistrationDto userRegistration = new UserRegistrationDto();
        userRegistration.setFirstName("memory");
        userRegistration.setLastName("not found");
        userRegistration.setEmail("info@memorynotfound.com");
        userRegistration.setConfirmEmail("info@memorynotfound.com");
        userRegistration.setPassword("password");
        userRegistration.setConfirmPassword("password");
        userRegistration.setTerms(true);

        Set<ConstraintViolation<UserRegistrationDto>> constraintViolations = validator.validate(userRegistration);

        Assert.assertEquals(constraintViolations.size(), 2);
    }

    @Test
    public void testValidPasswords() {
        UserRegistrationDto userRegistration = new UserRegistrationDto();
        userRegistration.setFirstName("memory");
        userRegistration.setLastName("not found");
        userRegistration.setEmail("info@memorynotfound.com");
        userRegistration.setConfirmEmail("info@memorynotfound.com");
        userRegistration.setPassword("xJ3!dij50");
        userRegistration.setConfirmPassword("xJ3!dij50");
        userRegistration.setTerms(true);

        Set<ConstraintViolation<UserRegistrationDto>> constraintViolations = validator.validate(userRegistration);

        Assert.assertEquals(constraintViolations.size(), 0);
    }
}
