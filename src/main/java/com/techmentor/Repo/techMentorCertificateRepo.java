package com.techmentor.Repo;

import com.techmentor.Model.TechMentorCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface techMentorCertificateRepo extends JpaRepository<TechMentorCertificate, Integer> {
	List<TechMentorCertificate> findByEmail(String emailAddress);
}
