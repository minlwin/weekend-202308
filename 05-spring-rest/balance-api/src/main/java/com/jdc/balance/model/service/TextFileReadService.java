package com.jdc.balance.model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.balance.model.exceptions.ApiBusinessException;

@Service
public class TextFileReadService {

	public List<String[]> read(MultipartFile file) {
		return read(file, ",");
	}
	
	public List<String[]> read(MultipartFile file, String delimiter) {
		
		try(var reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			var list = new ArrayList<String[]>();
			String line = null;
			
			while(null != (line = reader.readLine())) {
				list.add(line.split(delimiter));
			}
			
			return list;
			
		} catch (IOException e) {
			throw new ApiBusinessException(e.getMessage());
		} 
	}	
}
