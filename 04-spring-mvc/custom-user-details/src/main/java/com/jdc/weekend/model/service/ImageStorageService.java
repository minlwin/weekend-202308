package com.jdc.weekend.model.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@PreAuthorize("hasAuthority('Member')")
public class ImageStorageService {
	
	private Path imageFolder = Path.of("images");
	private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
	
	public enum ImageType {
		Profile, Post
	}

	public String save(MultipartFile file, ImageType type, int imageNumber) {
		
		var fileName = getFileName(ImageType.Profile, file.getOriginalFilename(), imageNumber);
		var target = imageFolder.resolve(fileName);
		
		try {
			Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return fileName;
	}

	// type_trim(username)_yyyyMMddHHmmssSSS_imageNumber.extension
	private String getFileName(ImageType type, String fileName, int imageNumber) {
		var extension = getExtension(fileName);
		var userName = SecurityContextHolder.getContext().getAuthentication().getName().trim();
		var timeStamp = LocalDateTime.now().format(DF);
		return "%s_%s_%s_%s.%s".formatted(type, userName, timeStamp, extension, imageNumber);
	}

	private String getExtension(String fileName) {
		var array = fileName.split("\\.");
		
		if(array.length > 0) {
			return array[array.length - 1];
		}
		
		return "";
	}

	public void delete(List<String> images) {
		images.forEach(image -> {
			try {
				var path = imageFolder.resolve(image);
				Files.delete(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	public List<String> saveAll(List<MultipartFile> files) {
		
		var list = new ArrayList<String>();
		
		for(var i = 0; i < files.size(); i ++) {
			var fileName = save(files.get(i), ImageType.Post,i + 1);
			list.add(fileName);
		}
		
		return list;
	}
}
