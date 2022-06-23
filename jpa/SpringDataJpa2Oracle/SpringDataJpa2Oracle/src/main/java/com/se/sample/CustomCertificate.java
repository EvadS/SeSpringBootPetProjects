package com.se.sample;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

    @Data
    @Builder
    public class CustomCertificate {

        private int version;

        private String subject;

        private String signatureAlgorithm;

        private String signatureOID;

        private Date expirationDate;

        private String alias;

        private BigInteger serialNumber;

        public String parseField(String field) {
            int startIndex = subject.indexOf(field + "=");
            if (startIndex == -1) return null;
            int endIndex = subject.indexOf(",", startIndex) == -1 ? subject.length() : subject.indexOf(",", startIndex);
            return subject.substring(startIndex, endIndex);
        }

        public String parseCN() {
            return parseField("CN");
        }

        public String parseO() {
            return parseField("O");
        }

        @Override
        public String toString() {
            return String.format("%s %s(%s)", alias, serialNumber, parseCN());
        }
    }
