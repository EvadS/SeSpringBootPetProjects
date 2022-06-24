package com.se.sample;

import com.se.sample.persist.Artist;
import com.se.sample.persist.Song;
import com.se.sample.repository.CertificateUrlRepository;
import com.se.sample.repository.SongRepository;
import com.se.sample.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.YearMonth;

@Slf4j
@SpringBootApplication
public class SpringDataJpa2OracleApplication {

//	@Autowired
//	private StudentRepository studentRepository;

//	@Autowired
//	private CertificateUrlRepository certificateUrlRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpa2OracleApplication.class, args);
	}

	@Autowired
	SongRepository songRepository;

	@Bean
	ApplicationRunner init() {
		return args ->{
			testSong();
			//testCertificateUrlEntity();

			//testCustomer();
		};
	}

	private void testSong() {
		Song song = new Song();
		Artist artist = new Artist();
		artist.setName("artist name");

		song.setArtist(artist);
		song.setLength(111l);
		song.setName("song name");
		YearMonth recordedOn = YearMonth.now();

		song.setRecordedOn(recordedOn);

		songRepository.save(song);

		songRepository.findAll().forEach(i-> log.info(i.toString()));
		log.info("**************************************************");

	}

//	private void testCustomer() {
//		Customer customer =  Customer.builder()
//				.email("mail@mail.ru")
//				.name("some name")
//				.build();
//
//		studentRepository.save(customer);
//		studentRepository.findAll().forEach(
//				i-> log.info(i.toString())
//		);
//	}



//	private void testCertificateUrlEntity() {
//		CertificateUrlEntity certificateUrl = new CertificateUrlEntity();
//
//		TaskConfiguration configuration = TaskConfiguration.builder()
//				.test("test")
//				.build();
//
//		certificateUrl.setConfiguration(configuration);
//
//		certificateUrlRepository.save(certificateUrl);
//
//		certificateUrlRepository.findAll().forEach(i-> log.info(i.toString()));
//	}


}
