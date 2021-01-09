package hu.ponte.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.ponte.hr.model.Image;

public interface ImageRepository extends JpaRepository<Image, String>{

	@Query("SELECT new Image(i.id, i.name, i.mimeType, i.size, i.digitalSign) FROM Image i")
	List<Image> findAllImageMeta();
}
