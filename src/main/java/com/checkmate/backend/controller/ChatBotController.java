package com.checkmate.backend.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.checkmate.backend.service.ScheduleService;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.dialogflow.v2beta1.model.GoogleCloudDialogflowV2WebhookRequest;
import com.google.api.services.dialogflow.v2beta1.model.GoogleCloudDialogflowV2WebhookResponse;

import lombok.RequiredArgsConstructor;

@RequestMapping("/chatbot")
@RestController
@RequiredArgsConstructor
public class ChatBotController {
	private static JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();
	private final ScheduleService scheduleService;

	@RequestMapping(method = RequestMethod.POST, value = "/dialogFlowWebHook")
	public ResponseEntity<?> dialogFlowWebHook(@RequestBody String requestStr, HttpServletRequest servletRequest) throws
		IOException {

		try {
			GoogleCloudDialogflowV2WebhookResponse response = new GoogleCloudDialogflowV2WebhookResponse(); // response 객체
			GoogleCloudDialogflowV2WebhookRequest request = jacksonFactory.createJsonParser(requestStr)
				.parse(GoogleCloudDialogflowV2WebhookRequest.class); // request 객체에서 파싱

			Map<String, Object> params = request.getQueryResult().getParameters(); // 파라미터 받아서 map에다 저장

			if (params.size() > 0) {
				System.out.println(params);
				response.setFulfillmentText("다음과 같은 파라미터가 나왔습니다 " + "스프링에서 보내는 테스트입니다.");
			} else {
				response.setFulfillmentText("Sorry you didn't send enough to process");
			}

			return new ResponseEntity<GoogleCloudDialogflowV2WebhookResponse>(response, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST); // 에러 발생 시 bad request 보내줌
		}
	}
}

