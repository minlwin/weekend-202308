package com.jdc.weekend.model.input;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jdc.weekend.model.entity.Post;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PostForm {

	private int id;
	
	@NotBlank(message = "Please enter title.")
	private String title;
	
	@Min(value = 1, message = "Please select category.")
	@NotNull(message = "Please select category.")
	private Integer categoryId;
	
	@NotBlank(message = "Please enter description.")
	private String description;
	
	private List<MultipartFile> files;
	
	private List<String> photoLinks;
	
	public static PostForm from(Post entity) {
		var form = new PostForm();
		form.setId(entity.getId());
		form.setTitle(entity.getTitle());
		form.setDescription(entity.getDescription());
		form.setPhotoLinks(entity.getImages());
		return form;
	}
}
