package com.example.soap;

import com.howtodoinjava.xml.school.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBElement;


@Endpoint
public class StudentEndpoint {
    public static final String NAMESPACE_URI = "http://www.howtodoinjava.com/xml/school";
    public static final String BEER_REQUEST_LOCAL_PART = "StudentDetailsRequest";
    public static final String BEER_REQUEST_LOCAL_PART1 = "StudentDetailsRequest";
    private StudentRepository StudentRepository;

    @Autowired
    public StudentEndpoint(StudentRepository StudentRepository) {
        this.StudentRepository = StudentRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = BEER_REQUEST_LOCAL_PART)
    @ResponsePayload
    public StudentDetailsResponse getStudent(@RequestPayload StudentDetailsRequest request) {
        StudentDetailsResponse response = new StudentDetailsResponse();
        response.setStudent(StudentRepository.findStudent(request.getName()));

        return response;
    }

//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "LeaveRequest")
//    public void handleLeaveRequest(@RequestPayload StudentDetailsRequest leaveRequest) throws Exception {
//        System.out.println("---------------------");
//    }
//    @PayloadRoot(localPart = BEER_REQUEST_LOCAL_PART1)
//    @ResponsePayload
//    public StudentDetailsResponse test(String request) {
//        StudentDetailsResponse response = new StudentDetailsResponse();
//        response.setStudent(StudentRepository.findStudent("name"));
//
//        return response;
//    }
}
