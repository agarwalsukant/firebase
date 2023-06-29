package com.ktworks.firebase.serviceimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.ktworks.firebase.controller.request.User;
import com.ktworks.firebase.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private Firestore firestore;
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public String saveUser(User user) {
		DocumentReference docRef = firestore.collection("users").document("alovelace");
		Map<String, Object> map = 
			    mapper.convertValue(user, new TypeReference<Map<String, Object>>() {});
		ApiFuture<WriteResult> result = docRef.set(user);
		// ...
		// result.get() blocks on response
		try{System.out.println("Update time : " + result.get().getUpdateTime());
		
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}
