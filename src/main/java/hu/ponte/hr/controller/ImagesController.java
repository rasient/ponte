package hu.ponte.hr.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.ponte.hr.model.Image;
import hu.ponte.hr.repository.ImageRepository;

@RestController()
@RequestMapping("api/images")
public class ImagesController {

    @Autowired
    private ImageRepository imageRepository;

    @GetMapping("meta")
    public List<Image> listImages() {
		return imageRepository.findAllImageMeta();
    }

    @GetMapping("preview/{id}")
    public byte[] getImage(@PathVariable("id") String id) {
    	return imageRepository.getOne(id).getFile();
	}

}
