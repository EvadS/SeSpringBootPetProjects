package com.example.soap.additional.filter;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * A filter to create transaction before and commit it once request completes
 * The current implemenatation is just for demo
 * @author hemant
 *
 */
@Component
@Order(1)
public class TransactionFilter implements Filter {
    private final static Logger LOG = LoggerFactory.getLogger(TransactionFilter.class);

    @SneakyThrows
    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

     //--------
        MessageFactory messageFactory = MessageFactory.newInstance();
        InputStream inStream = request.getInputStream();
        SOAPMessage soapMessage = messageFactory.createMessage(new MimeHeaders(), inStream);
        PrintWriter writer = response.getWriter();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        soapMessage.writeTo(out);
        String strMsg = new String(out.toByteArray());
        writer.println(strMsg);
        //--------

        LOG.info(
                "Starting a transaction for req : {}",
                req.getRequestURI());

        chain.doFilter(request, response);
        LOG.info(
                "Committing a transaction for req : {}",
                req.getRequestURI());
    }

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        LOG.info("Initializing filter :{}", this);
    }
    // other methods
}
