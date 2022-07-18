package com.example.soap;


import com.example.soap.additional.filter.CustomEndpointInterceptor;
import com.example.soap.additional.filter.GlobalEndpointInterceptor;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadRootSmartSoapEndpointInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.List;


@EnableWs
@Configuration
public class Config extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);

        return new ServletRegistrationBean(servlet, "/service/*");
    }

    @Bean(name = "studentDetailsWsdl")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema countriesSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("StudentDetailsPort");
        wsdl11Definition.setLocationUri("/service/student-details");
        wsdl11Definition.setTargetNamespace("http://www.howtodoinjava.com/xml/school");
        wsdl11Definition.setSchema(countriesSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema countriesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("school.xsd"));
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this is the package name specified in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("com.howtodoinjava.xml.school");
        return marshaller;
    }

    // additional. step2

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {

        // register global interceptor
        interceptors.add(new CustomEndpointInterceptor());

        // register endpoint specific interceptor
        interceptors.add(new PayloadRootSmartSoapEndpointInterceptor(
                new CustomEndpointInterceptor(),
                StudentEndpoint.NAMESPACE_URI,
                StudentEndpoint.BEER_REQUEST_LOCAL_PART));

        GlobalEndpointInterceptor interceptor = new GlobalEndpointInterceptor();
        interceptors.add(interceptor);
    }



}