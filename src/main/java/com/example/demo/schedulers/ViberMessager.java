package com.example.demo.schedulers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.ViberMessage;
import com.example.demo.dto.ViberSender;
import com.example.demo.services.DailyMenuService;
import com.google.common.util.concurrent.Futures;
import com.viber.bot.ViberSignatureValidator;
import com.viber.bot.api.ViberBot;
import com.viber.bot.message.TextMessage;

@Component
public class ViberMessager {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ViberBot bot;

	@Autowired
	private ViberSignatureValidator signatureValidator;

	@Value("${application.viber-bot.webhook-url}")
	private String webhookUrl;
	
	@Value("${application.viber-bot.auth-token}")
	private String token;

	@Autowired
	private DailyMenuService dailyMenuService;

	@Scheduled(cron = "*/60 * * * * *")
	public String getBot() throws InterruptedException, ExecutionException {
		System.out.println(bot.getAccountInfo().get());
		List<Object> members = (List<Object>) bot.getAccountInfo().get().get("members");
		for (Object member : members) {
			Map<Object, Object> memberObject = (Map<Object, Object>) member;
			String id = (String) memberObject.get("id");
			HttpHeaders headers = new HttpHeaders();
			headers.set("X-Viber-Auth-Token", token);//
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			ViberMessage message = new ViberMessage(id, 1, new ViberSender("Order Meal", "http://example.com"), "text",
					"Hi, i'm sending today's menu http://localhost:4200/user/daily-menu");
			System.out.println(id);
			HttpEntity<ViberMessage> entity = new HttpEntity<ViberMessage>(message, headers);
			System.out.println(entity.toString());

			restTemplate.exchange("https://chatapi.viber.com/pa/send_message", HttpMethod.POST, entity, String.class)
					.getBody();

		}
		return "result";

	}

}