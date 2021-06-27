package com.techmentor.Repo;

import com.techmentor.Model.payment.TechmentorPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RazorpayRepo extends JpaRepository<TechmentorPayment, String>{ 

}
