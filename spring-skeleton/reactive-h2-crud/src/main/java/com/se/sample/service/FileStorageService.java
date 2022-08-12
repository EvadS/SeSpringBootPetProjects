package com.se.sample.service;

import org.springframework.http.codec.multipart.FilePart;
import reactor.core.CorePublisher;
import reactor.core.publisher.Mono;

public interface FileStorageService {
     Mono<String> storeFile(FilePart filePart) ;
}
