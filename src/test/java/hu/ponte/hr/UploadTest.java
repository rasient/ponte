package hu.ponte.hr;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import hu.ponte.hr.controller.ImagesController;
import hu.ponte.hr.controller.UploadController;
import hu.ponte.hr.model.Image;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class UploadTest {

	@Autowired
	private UploadController uploadController;
	
	@Autowired
	private ImagesController imagesController;
	
	@Test
	public void testUpload() throws IOException {
		File file = new File("src\\test\\resources\\images\\cat.jpg");
		MultipartFile multipartFile = new MockMultipartFile("cat", file.getName(), MediaType.IMAGE_JPEG_VALUE, Files.readAllBytes(file.toPath()));
		
		uploadController.handleFormUpload(multipartFile);
		Image image = imagesController.listImages().get(0);
		
		assertEquals("1", image.getId());
		assertEquals("cat.jpg", image.getName());
		assertEquals(MediaType.IMAGE_JPEG_VALUE, image.getMimeType());
		assertEquals(32172, image.getSize());
	}
}
