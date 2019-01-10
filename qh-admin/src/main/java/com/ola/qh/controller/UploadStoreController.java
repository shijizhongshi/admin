package com.ola.qh.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ola.qh.polyv.Tester;
import com.ola.qh.service.IStoreService;
import com.ola.qh.util.Results;

@RestController
public class UploadStoreController {

	@Autowired
	private IStoreService storeService;

	@RequestMapping(value = "/api/upload/single", method = RequestMethod.POST, consumes = "multipart/form-data")
	public Results<String> uploadSingle(@RequestParam(value = "file", required = true) MultipartFile file)
			throws Exception {
		Results<String> result = new Results<String>();
		String url = process(file);
		result.setData(url);
		result.setStatus("0");
		return result;
	}

	private String process(MultipartFile file) throws Exception {
		byte[] contents = file.getBytes();
		String fname = file.getOriginalFilename();
		return storeService.storeUrl(fname, contents);
	}

	@RequestMapping(value = "/api/upload/multi", method = RequestMethod.POST, consumes = "multipart/form-data")
	public Results<List<String>> upload(@RequestParam(value = "file", required = true) MultipartFile[] file)
			throws Exception 
	{
		Results<List<String>> result = new Results<List<String>>();
		List<String> items = new ArrayList<String>();
		for (MultipartFile f : file) {
			String url = process(f);
			items.add(url);
		}
		result.setStatus("0");
		result.setData(items);
		return result;
	}
	
	
	@RequestMapping(value = "/api/upload/video", method = RequestMethod.GET)
	public Results<String> uploadVideo(@RequestParam(value = "file", required = true) String file)
			throws Exception {
		Results<String> result = new Results<String>();
		Tester t=new Tester();
		System.out.println(file);
//		
		return result;
	}
}
