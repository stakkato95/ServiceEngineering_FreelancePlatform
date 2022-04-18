package com.stakkato95.freelance.domain.transport;

import lombok.Data;

import java.util.List;

@Data
public class NewProject {
    String name;
    //    Client client;
    List<Long> freelancerIds;
}
