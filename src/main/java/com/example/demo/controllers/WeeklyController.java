package com.example.demo.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.WeeklyMenuDTO;
import com.example.demo.services.WeeklyMenuService;

@RestController
@RequestMapping("weeklyMenu")
public class WeeklyController {

	@Autowired
	WeeklyMenuService weeklyMenuService;

	@PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@RequestPart("weeklyMenu") WeeklyMenuDTO weeklyMenuDTO, @RequestPart(name = "files") MultipartFile[] files) {

		return new ResponseEntity<>(weeklyMenuService.create(weeklyMenuDTO, files), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<WeeklyMenuDTO>> findAll() {
		return new ResponseEntity<List<WeeklyMenuDTO>>(weeklyMenuService.findAll(), HttpStatus.OK);
	}

	@PutMapping("deleteDailyMenuFromWeakly/{weaklyMenuId}/{dailyMenuId}")
	public ResponseEntity<WeeklyMenuDTO> deleteDailyMenuFromWeakly(@PathVariable Long weaklyMenuId,
			@PathVariable Long dailyMenuId) {
		return new ResponseEntity<WeeklyMenuDTO>(weeklyMenuService.deleteDailyMenuFromWeakly(weaklyMenuId, dailyMenuId),
				HttpStatus.OK);
	}

//    @PostMapping("/Request")
//    @ResponseBody
//    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("Info") String Info) {
//        System.out.println("Json is" + Info);
//        if (file.isEmpty()) {
//            return "No file attached";
//        }
//        try {
//            // Get the file and save it somewhere
//            byte[] bytes = file.getBytes();
//            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
//            Files.write(path, bytes);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "Succuss";
//    }

}
