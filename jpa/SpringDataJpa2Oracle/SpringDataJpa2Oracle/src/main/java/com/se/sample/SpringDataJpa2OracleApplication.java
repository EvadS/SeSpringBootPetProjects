package com.se.sample;

import com.se.sample.enums.TaskStatus;
import com.se.sample.enums.TaskType;
import com.se.sample.persist.Artist;
import com.se.sample.persist.CommonConfig;
import com.se.sample.persist.RemoteServerTaskEntity;
import com.se.sample.persist.Song;
import com.se.sample.repository.RemoteServerRepository;
import com.se.sample.repository.SongRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.YearMonth;
import java.util.UUID;

@Slf4j
@EnableJpaAuditing
@SpringBootApplication
public class SpringDataJpa2OracleApplication {

//	@Autowired
//	private StudentRepository studentRepository;

	//@Autowired
	//private CertificateUrlRepository certificateUrlRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpa2OracleApplication.class, args);
	}


	@Autowired
	RemoteServerRepository remoteServerRepository;

	@Autowired
	SongRepository songRepository;

	@Bean
	ApplicationRunner init() {
		return args ->{
			//testSong();

			testCertificateUrlEntity();

		//	testCustomer();
		};
	}

//	private void tesCommon() {
//		CommonConfig commonConfig = new CommonConfig();
//
//		commonConfig.setId(UUID.randomUUID());
//		commonConfig.setConfigData("date");
//
//		try {
//			commonConfigRepo.save(commonConfig);
//			int a = 0;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

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



	private void testCertificateUrlEntity() {
		RemoteServerTaskEntity certificateUrl = new RemoteServerTaskEntity();

		TaskConfiguration configuration = TaskConfiguration.builder()
				.test("test")
				.build();

		//certificateUrl.setConfiguration(null);

		certificateUrl.setServerId(UUID.randomUUID());
		certificateUrl.setStatus(TaskStatus.CREATED);
		certificateUrl.setType(TaskType.AUTO_UPDATE);
		remoteServerRepository.save(certificateUrl);

		remoteServerRepository.findAll().forEach(i-> log.info(i.toString()));
	}


}
