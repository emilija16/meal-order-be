package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ViberMessage {
	
	private String receiver;
	private int min_api_version;
	private ViberSender sender;
	private String type;
	private String text;
	
}
