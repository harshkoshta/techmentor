package com.techmentor.Controller.payment;

import com.razorpay.RazorpayClient;
import com.techmentor.Model.Course;
import com.techmentor.Model.Mail;
import com.techmentor.Model.TechMentorCertificate;
import com.techmentor.Model.User;
import com.techmentor.Model.payment.TechmentorPayment;
import com.techmentor.Repo.CourseRepo;
import com.techmentor.Repo.UserRepo;
import com.techmentor.Service.MailService;
import com.techmentor.Service.RazorpayService;
import com.techmentor.Service.TechMentorCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
public class RozarPayController {
	@Autowired
	private RazorpayService razorService;
	@Autowired
	private MailService mailservice;
	@Autowired
	private TechMentorCertificateService certificateservice;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CourseRepo courseRepo;
	private RazorpayClient razorpayClient;

	@PostMapping("/pay/{amount}")
	public String razorpay(@PathVariable(name = "amount") int amount, Principal principal,
			@RequestParam(name = "courseid", required = false) Integer courseid) {
		System.out.print("Coures Id : " + courseid);
		if (courseid != null) {
			System.out.print("Course id : " + courseid);
			Optional<User> user = userRepo.findById(principal.getName());
			Optional<Course> course = courseRepo.findById(courseid);
			System.out.print("Coures Id : " + courseid);
			if (user.isPresent() && course.isPresent()) {
				TechmentorPayment payment = razorService.createOrder(amount, "INR", "techmentor_receipt", user.get(),
						course.get());

				return "redirect:/payment/" + payment.getOrderid();
			} else
				return "redirect:/";
		} else
			return "redirect:/";
	}

	@GetMapping("/payment/{orderid}")
	public String razororderpayment(@PathVariable(name = "orderid") String orderid, Model model) {
		TechmentorPayment order = razorService.fetchOrder(orderid);
		if (order == null)
			System.out.println("Error in order");
		model.addAttribute("order", order);

		return "pages/payment/payment.html";
	}

	@PostMapping("/payment/success")
	public String paymentSuccess(@RequestBody String res_string, Model model) {
		TechmentorPayment techmentorPayment = new TechmentorPayment();
		String[] res_string_arr = res_string.split("&");
		for (String ss : res_string_arr) {
			String[] ar = ss.split("=");
			System.out.println("--" + ar[0].trim());
			System.out.println("--" + ar[1].trim() + "--");
			if ("razorpay_payment_id".equalsIgnoreCase(ar[0]))
				techmentorPayment.setPaymentid(ar[1]);
			if ("razorpay_order_id".equalsIgnoreCase(ar[0]))
				techmentorPayment.setOrderid(ar[1]);
			if ("razorpay_signature".equalsIgnoreCase(ar[0]))
				techmentorPayment.setSignature(ar[1]);
		}
		System.out.println("Tech Mentor Payment : " + techmentorPayment);
		TechmentorPayment fetchOrder = razorService.fetchOrder(techmentorPayment.getOrderid());
		fetchOrder.setPaymentid(techmentorPayment.getPaymentid());
		fetchOrder.setSignature(techmentorPayment.getSignature());
		TechmentorPayment saveorder = razorService.saveorder(fetchOrder);
		// saving to tech Certificate
		TechMentorCertificate techMentorCertificate = new TechMentorCertificate();
techMentorCertificate.setName(saveorder.getUser().getName());
techMentorCertificate.setEmail(saveorder.getUser().getEmail());
techMentorCertificate.setUser(saveorder.getUser());
techMentorCertificate.setCourse(saveorder.getCourse());
techMentorCertificate.setCoursename(saveorder.getCourse().getName());
techMentorCertificate.setCourseStartDate(saveorder.getCourse().getStart_course());
techMentorCertificate.setCourseEndDate(saveorder.getCourse().getEnd_course());
		certificateservice.saveCertificate(techMentorCertificate);
		// delete to tech Certificate
		// email

		Mail mail = new Mail(saveorder.getUser().getEmail(), "TechMentor - Payment receive Successfully!! ",
				"Thanks for being a part of techmentor \n" + "Your Order whose Order ID : " + saveorder.getOrderid()
						+ " Has been receiverd successfully of Amount : " + saveorder.getAmount() / 100 + " Rs."
						+ "\n Whose payment ID of PAYMENT ID :" + saveorder.getPaymentid()
						+ " Glad and Thanks to be Part of Tech Mentor!!");
		mailservice.sendMail(mail);
		// end
		model.addAttribute("order", saveorder);
		System.out.println(saveorder);
		return "pages/payment/paymentsuccess.html";
	}

}
