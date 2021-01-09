package hu.ponte.hr.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Image
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String name;
	private String mimeType;
	private long size;
	
	@Lob
	private String digitalSign;
	
	@Lob
	private byte[] file;
	
	public Image(String id, String name, String mimeType, long size, String digitalSign) {
		this.id = id;
		this.name = name;
		this.mimeType = mimeType;
		this.size = size;
		this.digitalSign = digitalSign;
	}
}
