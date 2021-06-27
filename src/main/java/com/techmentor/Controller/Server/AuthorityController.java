package com.techmentor.Controller.Server;

import com.techmentor.GlobalConstants;
import com.techmentor.Model.*;
import com.techmentor.Repo.techMentorCertificateRepo;
import com.techmentor.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
public class AuthorityController {
	@Autowired
	private MailService mailservice;
	@Autowired
	private CourseService courseService;
	@Autowired
	private techMentorCertificateRepo certificateRepo;
	@Autowired
	private verificationTokerService verifyService;
	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder passwordencoder;
	@Autowired
	private EmailSubsciberService emailservice;

	@GetMapping("/createcourse")
	public String getCourse() {
		return "pages/authority/createCourse.html";
	}

	
	@PostMapping("/createcourse")
	public ModelAndView CreateCourse(@ModelAttribute Course course) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("pages/more/msg.html");
		System.out.println("Firing Query!!");
		System.out.println(course);
		course.setEnrollable(true);
		com.techmentor.Model.Course saveCourseService = courseService.saveCourseService(course);
		System.out.println(course);
		modelAndView.addObject("msg", "Course Successfully created" + saveCourseService);
		return modelAndView;
		
	}

	@GetMapping("/updatecourse")
	public String updateCourse(@RequestParam("id") Integer id, Model model) {
		Course course = courseService.loadcourse(id);
		model.addAttribute("course", course);
		return "pages/authority/deleteandUpdateCourse.html";
	}
	
	
	@PostMapping("/updatecourse")
	public ModelAndView updateCourse(@ModelAttribute Course course) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("pages/more/msg.html");
		System.out.print("Post method: " + course);
		Course loadcourse = courseService.loadcourse(course.getId());
		if (loadcourse == null) {
			
			modelAndView.addObject("msg", "Unable to undate Course!! Contact Admin");
			return modelAndView;
		}
		Course updatedcourse = courseService.saveCourseService(course);
		modelAndView.addObject("msg", "Successfully updated : " + updatedcourse);
		return modelAndView;
	
	}

	
	@PostMapping("/deletecourse/{id}")
	public ModelAndView deleteCourse(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("pages/more/msg.html");
		boolean deletecourse = courseService.deletecourse(id);
		if (deletecourse) {
			modelAndView.addObject("msg", "Deleted Successfull!!");
			return modelAndView;
			
		}
		else {
			modelAndView.addObject("msg",  "Couldn't delete !!");
			return modelAndView;
			
		}
	}

	@GetMapping("/search/certificate")
	public String getCertificate(@RequestParam("email") String email, Model model) {
		List<TechMentorCertificate> emails = certificateRepo.findByEmail(email);
		System.out.println(Arrays.toString(emails.toArray()));
		model.addAttribute("certificates", emails);
		return "pages/certificate/tablecertificates.html";

	}

	@GetMapping("/resetpassword")
	public String getresetPassword() {
		return "pages/more/resetpassword.html";
	}


	@PostMapping("/resetpassword")
	public ModelAndView resetPassword(@RequestParam("email") String email) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("pages/more/msg.html");
		verificationToken token = new verificationToken();
		token.setEmail(email);
		verificationToken saveToken = verifyService.saveToken(token);
		Mail mail = new Mail(email, "Click to verify email! - tech Mentor", "Dont share this link to anyone : "
				+ GlobalConstants.WEBSITE_NAME + "/resetpassword/" + saveToken.getId());
		mailservice.sendMail(mail);
		modelAndView.addObject("msg", "Mail Sent !! Check your mail.");
		return modelAndView;
	}

	@GetMapping("/resetpassword/{id}")
	public String privateresetpassword(@PathVariable("id") Integer id, Model model) {
		verificationToken token = verifyService.findbyid(id);
		if (token != null) {
			model.addAttribute("token", id);
			return "pages/more/privateresetpassword.html";
		}
		return "pages/more/login.html";
	}


	@PostMapping("/resetpassword/{id}")
	public ModelAndView privatepostresetpassword(@PathVariable("id") Integer id, @RequestParam("password") String password) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("pages/more/msg.html");
		verificationToken token = verifyService.findbyid(id);

		User user = userService.finduser(token.getEmail());
		if (user == null) {
			modelAndView.addObject("msg", "User not found with this email");
			return modelAndView;
			
		} else {
			user.setPassword(passwordencoder.encode(password));
			userService.save(user);
			verifyService.deleteToken(id);
			modelAndView.addObject("msg", "Password Succesfully Changed!!");
			return modelAndView;
	
		}

	}

	@GetMapping("/authority/sendmailtosubscriber")
	public String getsendmailtosubscriber() {

		return "pages/authority/sendMailToSubscriber.html";
	}

	
	@PostMapping("/authority/sendmailtosubscriber")
	public ModelAndView postsendmailtosubscriber(@RequestParam("body") String body, @RequestParam("subject") String subject) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("pages/more/msg.html");
		List<EmailSubscriber> subscribe = emailservice.findall();
		Mail[] mailaddresses = new Mail[subscribe.size()];
		for (int i = 0; i < subscribe.size(); i++) {
			mailaddresses[i] = new Mail(subscribe.get(i).getEmail(), subject, body);
		}
		for (int i = 0; i < subscribe.size(); i++) {
			String sendMail = mailservice.sendMail(mailaddresses[i]);
			System.out.println(sendMail);
		}
		modelAndView.addObject("msg", "Send mail to all Subsciber");
		return modelAndView;
	}
	@GetMapping("/authorize")
	public String Authorize() {
	
		return "pages/authority/authority.html";
	}
}
