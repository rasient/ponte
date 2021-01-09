package hu.ponte.hr.services;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import hu.ponte.hr.exception.ImageException;
import hu.ponte.hr.model.Image;
import hu.ponte.hr.repository.ImageRepository;

@Service
public class ImageStore {
	
	@Autowired
	private SignService signService;
	
	@Autowired
	private ImageRepository imageMetaRepository;

	public void saveImage(MultipartFile file) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException {
        if (file.getSize() > 2097152) {
            throw new ImageException("Maximum allowed size is 2MB.");
        }
        
        Image image = Image.builder()
        			.name(file.getOriginalFilename())
        			.mimeType(file.getContentType())
        			.size(file.getSize())
        			.digitalSign(signService.digitalSign(file.getBytes()))
        			.file(file.getBytes())
        			.build();
        
        imageMetaRepository.save(image);
        
	}

}
