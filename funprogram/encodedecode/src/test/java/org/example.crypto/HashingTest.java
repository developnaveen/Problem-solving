package org.example.crypto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class HashingTest {

    @InjectMocks
    Hashing hs;

    @Test
    void shouldHashData() throws Exception{
        String data = "naveen";
        String result = hs.hashData(data);

        assertTrue(BCrypt.checkpw(data, result));
    }
}
