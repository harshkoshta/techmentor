package com.techmentor.Service;


import com.techmentor.Model.EmailSubscriber;
import com.techmentor.Repo.EmailSubsciberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailSubsciberService {
@Autowired
private EmailSubsciberRepo emailrepo;
public EmailSubscriber subscribe(String subscibing) {
	EmailSubscriber subscriber = new  EmailSubscriber();
	subscriber.setEmail(subscibing);
	subscriber.setStatus(true);
	EmailSubscriber save = emailrepo.save(subscriber);
	return save;
	
}
public List<EmailSubscriber> findall() {
	List<EmailSubscriber> findAll = emailrepo.findAll();
	System.out.println(findAll);
	return findAll;
}

}
