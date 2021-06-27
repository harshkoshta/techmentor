package com.techmentor.Controller.Server;

import com.techmentor.ConstTemplate.EmailConstant;
import com.techmentor.Model.*;
import com.techmentor.Service.EmailSubsciberService;
import com.techmentor.Service.MailService;
import com.techmentor.Service.TechMentorCertificateService;
import com.techmentor.Service.UserService;
import com.techmentor.more.GenerateCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.UnknownHostException;

@Controller
public class ServerController {

	@Autowired
	private MailService mailservice;
	@Autowired
	private EmailSubsciberService emailservice;
	@Autowired
	private BCryptPasswordEncoder passwordencoder;
	@Autowired
	private UserService userService;
	@Autowired
	private TechMentorCertificateService certificateService;

	

	@PostMapping("/register")
	public ModelAndView Register(@ModelAttribute User user) throws UnknownHostException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("pages/more/msg.html");
		User finduser = userService.finduser(user.getEmail());
		if (finduser == null) {
			user.setPassword(passwordencoder.encode(user.getPassword()));
			user.setRole(Role.USER);
			User save = userService.save(user);

			if (save != null) {
				modelAndView.addObject("msg", "Your are Successfull Registered!!");

			} else {
				modelAndView.addObject("msg", "Unable to Register!!");
			}
			return modelAndView;
		} else {
			modelAndView.addObject("msg", "User Already Registered!!");
			return modelAndView;
		}

	}

	@PostMapping("/subscribeemail")
	public String SubscribeEmail(@RequestParam("email") String email, Model model) {
		EmailSubscriber subscribe = emailservice.subscribe(email);
		Mail mail = new Mail(subscribe.getEmail(), EmailConstant.SUBJECT_THANKS_FOR_SUBSCRIBING,
				"You will Now receive techmentor mails.");
		mailservice.sendMail(mail);
		model.addAttribute("msg", "Thanks For subscribing!!");
		return "pages/more/msg.html";
	}

	@GetMapping("/certificate/generate/{id}/{email}")
	public ModelAndView generateCertificate(@PathVariable("id") Integer id, @PathVariable("email") String email)
			throws IOException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("pages/more/msg.html");
		TechMentorCertificate certificate = certificateService.byid(id);
		if (certificate.isStatus()) {
			GenerateCertificate certi = new GenerateCertificate();
			String file = GenerateCertificate.generate("Harsh", "Html", "24-07-2021");
			System.out.println("FIle address " + file);
			Mail mail = new Mail("koshta15@gmail.com", "Your certificate Generated", "Your Certificate here --");
			 mailservice.sendMailWithImage(mail, file);
			modelAndView.addObject("msg", "Certificate sent on mail Successfull!!");
			return modelAndView;
		}

		else {
			modelAndView.addObject("msg", "Unable to print, Please Complete Course!!");
			return modelAndView;
		}
	}

}
