package com.se.sample.dto;


import com.se.sample.entity.Speaker;
import lombok.*;

import javax.validation.constraints.NotEmpty;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SpeakerDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private String company;

    public Speaker createSpeaker() {
        return Speaker.builder()
                .company(this.company)
                .name(this.name)
                .build();
    }
}