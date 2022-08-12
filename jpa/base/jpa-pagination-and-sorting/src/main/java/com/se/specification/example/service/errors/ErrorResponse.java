package com.se.specification.example.service.errors;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Evgeniy Skiba on 20.06.2020
 * @project spring-data-jpa
 */

@NoArgsConstructor
public class ErrorResponse {
     private  int status;

     public ErrorResponse() {
     }

     public int getStatus() {
          return status;
     }

     public void setStatus(int status) {
          this.status = status;
     }
}
