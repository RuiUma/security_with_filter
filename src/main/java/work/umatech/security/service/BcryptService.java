package work.umatech.security.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BcryptService {

    private final Integer STRENGTH = 10;
    public String bcryptEncode(String passwd) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(STRENGTH);
        return encoder.encode(passwd);
    }

    public Boolean verify(String passwd, String encodedPass) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(STRENGTH);
        return encoder.matches(passwd, encodedPass);
    }



}
