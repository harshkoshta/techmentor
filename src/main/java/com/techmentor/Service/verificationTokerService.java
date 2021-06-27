package com.techmentor.Service;

import com.techmentor.Model.verificationToken;
import com.techmentor.Repo.verificationTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class verificationTokerService {
	@Autowired
	private verificationTokenRepo verifyRepo;

	public verificationToken saveToken(verificationToken token) {
		verificationToken save = verifyRepo.save(token);
		return save;
	}

	public boolean deleteToken(int id) {

		verifyRepo.deleteById(id);
		return true;
	}

	public verificationToken findbyid(int id) {

		Optional<verificationToken> token = verifyRepo.findById(id);
		return token.get();
	}
}
