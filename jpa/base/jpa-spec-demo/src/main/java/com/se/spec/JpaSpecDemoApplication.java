package com.se.spec;

import com.se.spec.domain.Note;
import com.se.spec.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@EnableJpaAuditing
@SpringBootApplication
public class JpaSpecDemoApplication implements CommandLineRunner {

    @Autowired
    private NoteRepository noteRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaSpecDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        noteRepository.deleteAllInBatch();

        long timestamp1 = new GregorianCalendar(2000, Calendar.JANUARY, 1,
                10, 15, 45).getTimeInMillis();
        Date date1 = new Date(timestamp1);
        Note note1 = new Note("1 january title", date1);

        System.out.println("1 jan timestamp: "+ timestamp1);

        long timestamp2 = new GregorianCalendar(1999, Calendar.DECEMBER, 31,
                10, 15, 45).getTimeInMillis();
        Note note2 = new Note("31 december title", new Date(timestamp2));

        long timestamp3 = new GregorianCalendar(2000, Calendar.JANUARY, 2,
                10, 15, 45).getTimeInMillis();
        Note note3 = new Note("2 january title", new Date(timestamp3));

        long timestamp4 = new GregorianCalendar(2000, Calendar.JANUARY, 3,
                10, 15, 45).getTimeInMillis();
        Note note4 = new Note("3 january title", new Date(timestamp4));

        System.out.println("3 jan timestamp: "+ timestamp4);


        long timestamp5 = new GregorianCalendar(2000, Calendar.JANUARY, 4,
                10, 15, 45).getTimeInMillis();
        Note note5 = new Note("4 january title", new Date(timestamp5));
        noteRepository.saveAll(Arrays.asList(
                note1, note2, note3, note4, note5
        ));

        System.out.println("Current elements count : " + noteRepository.count());



    }
}
