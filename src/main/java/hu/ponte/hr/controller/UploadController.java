package hu.ponte.hr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import hu.ponte.hr.exception.ImageException;
import hu.ponte.hr.services.ImageStore;
@Component
@RequestMapping("api/file")
public class UploadController
{

    @Autowired
    private ImageStore imageStore;
	
	@PostMapping("post")
    @ResponseBody
    public String handleFormUpload(@RequestParam("file") MultipartFile file) {
		try {
			imageStore.saveImage(file);
		} catch (Exception e) {
			throw new ImageException(e.getMessage());
		}
        return "ok";
    }
}
