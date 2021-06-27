package com.techmentor.Repo;

import com.techmentor.Model.verificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface verificationTokenRepo extends JpaRepository<verificationToken, Integer> {

}
