package com.medoc.doctorLab.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class newclinics {
	private String name;
    private String address;
    private double rating;
    private double distance;
    
}
