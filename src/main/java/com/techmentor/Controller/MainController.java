package com.techmentor.Controller;

import com.techmentor.Model.Course;
import com.techmentor.Model.User;
import com.techmentor.Service.CourseService;
import com.techmentor.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
public class MainController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private UserService userService;


	@ModelAttribute
	public void addattribute(Principal principal, HttpSession session) {
		if (principal != null) {
			User user = userService.finduser(principal.getName());
			session.setAttribute("user", user);
		}
	}

	@GetMapping("/")
	public String Home(Model model) throws IOException {
//		Resource resource = new ClassPathResource("static/resources/sliderimages");
//
//			File file = resource.getFile();
//			System.out.println("File Directory :  " + file.isDirectory());
//
//				model.addAttribute("obj",file.list());

		return "pages/index.html";
	}

	@GetMapping("/certificate")
	public String Certificate() {
		return "pages/certificate/certificate.html";
	}

	@GetMapping("/course")
	public String Course(Model model) {
		List<com.techmentor.Model.Course> list = courseService.loadallenrollable();
		model.addAttribute("courses", list);
		return "pages/course/course.html";
	}

	@GetMapping("/coursedetail/{id}")
	public String CourseDetails(@PathVariable("id") int id, Model model) {
		Course course = courseService.loadcourse(id);
		model.addAttribute(course);
		return "pages/course/coursedetail.html";
	}

	@GetMapping("/login")
	public String Login() {
		return "pages/more/login.html";
	}

	@GetMapping("/register")
	public String Register() {
		return "pages/more/register.html";
	}



}
