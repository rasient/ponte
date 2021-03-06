package hu.ponte.hr.services;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

@Service
public class SignService {

	public String digitalSign(byte[] file) throws IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, SignatureException {
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(IOUtils.toByteArray(this.getClass().getResourceAsStream("/config/keys/key.private")));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(keyFactory.generatePrivate(spec));
        signature.update(file);
        return Base64.getEncoder().encodeToString(signature.sign());
	}

}
