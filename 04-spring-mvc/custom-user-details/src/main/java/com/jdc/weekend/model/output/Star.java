package com.jdc.weekend.model.output;

public enum Star {

	None("bi-star"), Full("bi-star-fill"), Half("bi-star-half");

	private String style;

	private Star(String style) {
		this.style = style;
	}

	public String getStyle() {
		return style;
	}
	
	public static String[] getStars(int rating) {
		var stars = new String[5];
		
		var end = false;
		
		for(var i = 0; i < stars.length; i ++) {
			
			if(end) {
				stars[i] = Star.None.style;
				continue;
			}
			
			var devisor = (i + 1) * 2;
			var result = rating / devisor;
			var remainance = rating % devisor;
			
			if(result > 0) {
				stars[i] = Star.Full.style;
				end = result == 1 && remainance == 0;
			} else {
				stars[i] = remainance == 0 ? Star.None.style : Star.Half.style;
				end = true;
			}
			
		}
		
		return stars;		
	}
}
