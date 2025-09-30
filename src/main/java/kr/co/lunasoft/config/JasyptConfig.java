package kr.co.lunasoft.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@EnableEncryptableProperties
@Configuration
public class JasyptConfig {
	
	@Bean("jasyptStringEncrptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("springbootdemo");
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }

//	public static void main(String[] args) {
//		StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
//		pbeEnc.setAlgorithm("PBEWithMD5AndDES");
//		pbeEnc.setPassword("springbootdemo");
//
//		String jdbcUrl = pbeEnc.encrypt("");
//		String username = pbeEnc.encrypt("");
//		String password = pbeEnc.encrypt("");
//
//		System.out.println(jdbcUrl);
//		System.out.println(username);
//		System.out.println(password);
//
//		String jdbcUrlDe = pbeEnc.decrypt("");
//		String usernameDe = pbeEnc.decrypt("");
//		String passwordDe = pbeEnc.decrypt("");
//
//		System.out.println(jdbcUrlDe);
//		System.out.println(usernameDe);
//		System.out.println(passwordDe);
//	}
	
}
