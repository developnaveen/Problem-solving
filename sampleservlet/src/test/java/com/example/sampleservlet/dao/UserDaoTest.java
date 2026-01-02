package com.example.sampleservlet.dao;

import com.example.sampleservlet.model.User;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    private UserDao userDao;
    private User testUser;

    @BeforeEach
    void setUp() {
        userDao = new UserDao();

        testUser = new User();
        testUser.setEmail("testuser@test.com");
        testUser.setEmpId("TEST1001");
        testUser.setPassword("hashed-password");
    }

    @Test
    void shouldSaveUserSuccessfully() {
        assertDoesNotThrow(() -> userDao.saveUser(testUser));
    }

    @Test
    void shouldFetchPasswordByEmpId() {
        // Ensure user exists
        userDao.saveUser(testUser);

        String password = userDao.getUser("TEST1001");

        assertNotNull(password);
        assertEquals("hashed-password", password);
    }

    @Test
    void shouldReturnNullIfUserNotFound() {
        String password = userDao.getUser("UNKNOWN_ID");

        assertNull(password);
    }

    @Test
    void shouldThrowExceptionForDuplicateUser() {
        userDao.saveUser(testUser);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> userDao.saveUser(testUser)
        );

        assertEquals("User already exists", exception.getMessage());
    }

    @AfterEach
    void cleanUp() {
        // Optional cleanup to keep DB clean
        try {
            userDao.deleteUserByEmpId("TEST1001");
        } catch (Exception ignored) {
        }
    }
}
