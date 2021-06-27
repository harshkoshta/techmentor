package com.techmentor.Service;

import com.techmentor.Model.TechMentorCertificate;
import com.techmentor.Repo.techMentorCertificateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechMentorCertificateService {
@Autowired
private techMentorCertificateRepo repo;
public TechMentorCertificate saveCertificate(TechMentorCertificate tech) {
	TechMentorCertificate save = repo.save(tech);
	return save;
}
private List<TechMentorCertificate> findByEmail(String email){
	List<TechMentorCertificate> findByEmail = repo.findByEmail(email);
	return findByEmail;
	
}
public TechMentorCertificate byid(int  id){
	Optional<TechMentorCertificate> findByEmail = repo.findById(id);
	return findByEmail.get();
	
}
}
