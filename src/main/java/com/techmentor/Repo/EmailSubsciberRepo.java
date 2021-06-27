package com.techmentor.Repo;

import com.techmentor.Model.EmailSubscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailSubsciberRepo extends JpaRepository<EmailSubscriber, Integer> {

}
