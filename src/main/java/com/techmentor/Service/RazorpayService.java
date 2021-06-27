package com.techmentor.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.techmentor.GlobalConstants;
import com.techmentor.Model.Course;
import com.techmentor.Model.Mail;
import com.techmentor.Model.User;
import com.techmentor.Model.payment.TechmentorPayment;
import com.techmentor.Repo.RazorpayRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RazorpayService {
	@Autowired
	private RazorpayRepo razorRepo;
	@Autowired
	private MailService mailservice;
	
	private final RazorpayClient razorpayClient;

	public RazorpayService() throws RazorpayException {
		super();
//	UMzlDIxF3oPbF3uMRHcEv7d1
//	rzp_test_pjpFb4mQElwRrb
		razorpayClient = new RazorpayClient("rzp_test_pjpFb4mQElwRrb", "UMzlDIxF3oPbF3uMRHcEv7d1", true);
		// TODO Auto-generated constructor stub
	}

	public TechmentorPayment createOrder(int amount, String currency, String receipt, User user, Course course) {
		TechmentorPayment techpayment = null;
		try {
			System.out.println("Creating Order!!");
			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount", amount * 100); // amount in the smallest currency unit
			orderRequest.put("currency", currency.trim().toUpperCase());
			orderRequest.put("receipt", receipt.trim().toUpperCase());

			Order order = razorpayClient.Orders.create(orderRequest);

			techpayment = new TechmentorPayment();
			techpayment.setOrderid(order.get("id"));
			techpayment.setUser(user);
			techpayment.setCourse(course);
			techpayment.setAmount(order.get("amount"));
			if (order != null) {
				techpayment = razorRepo.save(techpayment);
				System.out.println("Order saved !!");
				// email

				Mail mail = new Mail(techpayment.getUser().getEmail(), "TechMentor - Order Created - click to pay!! ",
						"Click on Link to pay : " + GlobalConstants.WEBSITE_NAME+"/payment/" +  techpayment.getOrderid());
				mailservice.sendMail(mail);
				// end
			}

			System.out.println("Amount :" + amount + " Currency :" + currency + " Receipt :" + receipt + " User :"
					+ user + "Course: " + course);
			return techpayment;
		} catch (RazorpayException e) {
			// Handle Exception
			System.out.println(e.getMessage());
		}
		return techpayment;

	}

	public TechmentorPayment fetchOrder(String orderid) {
		TechmentorPayment order = null;
		Optional<TechmentorPayment> Oporder = razorRepo.findById(orderid);
		if (Oporder.isEmpty())
			return null;
		System.out.println(Oporder.get());
		return Oporder.get();
	}

	
	public TechmentorPayment saveorder(TechmentorPayment techpayment) {
		return razorRepo.save(techpayment);
	}

}
