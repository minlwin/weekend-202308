package com.jdc.weekend;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.jdc.weekend.model.output.Star;

public class StarTest {
	
	@ParameterizedTest
	@ValueSource(ints = {
		0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
	})
	void test(int rating) {
		var result = Star.getStars(rating);
		
		System.out.print("[");
		
		for(var i = 0; i < result.length; i ++) {
			
			if(i > 0) {
				System.out.print(", ");
			}
			
			System.out.print(result[i]);
		}
		
		System.out.println("]");
	}
}
