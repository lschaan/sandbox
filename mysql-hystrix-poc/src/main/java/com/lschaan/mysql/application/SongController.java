package com.lschaan.mysql.application;

import com.lschaan.mysql.service.SongService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SongController {

	@RequestMapping("/songs")
	public ResponseEntity returnRepositoryAmount() {
		return new ResponseEntity(SongService.getSong(), HttpStatus.OK);
	}

}
