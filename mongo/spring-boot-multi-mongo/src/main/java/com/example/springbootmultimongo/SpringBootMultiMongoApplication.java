package com.example.springbootmultimongo;

import com.example.springbootmultimongo.repository.primary.PrimaryModel;
import com.example.springbootmultimongo.repository.primary.PrimaryRepository;
import com.example.springbootmultimongo.repository.secondary.SecondaryModel;
import com.example.springbootmultimongo.repository.secondary.SecondaryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@Slf4j
@SpringBootApplication
public class SpringBootMultiMongoApplication implements CommandLineRunner {

	@Autowired
	private PrimaryRepository primaryRepository;

	@Autowired
	private SecondaryRepository secondaryRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMultiMongoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("************************************************************");
		log.info("Start printing mongo objects");
		log.info("************************************************************");
		this.primaryRepository.save(new PrimaryModel(null, "Primary database plain object"));

		this.secondaryRepository.save(new SecondaryModel(null, "Secondary database plain object","tmp description"));

		List<PrimaryModel> primaries = this.primaryRepository.findAll();
		for (PrimaryModel primary : primaries) {
			log.info(primary.toString());
		}

		List<SecondaryModel> secondaries = this.secondaryRepository.findAll();

		for (SecondaryModel secondary : secondaries) {
			log.info(secondary.toString());
		}

		log.info("************************************************************");
		log.info("Ended printing mongo objects");
		log.info("************************************************************");

	}
}
