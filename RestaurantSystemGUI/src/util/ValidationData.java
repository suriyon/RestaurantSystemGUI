package util;

import java.util.regex.Pattern;

public class ValidationData {
	public static boolean checkPatternId(String id){
		boolean result = false;
		String pattern = "[S][0-9]{4}";
		if(Pattern.matches(pattern, id)){
			result = true;
		}
		return result;
	}
	public static boolean checkNumber(String salary){
		boolean result = false;
		String pattern = "[0-9]{1,}";
		if(Pattern.matches(pattern, salary)){
			result = true;
		}
		return result;
	}
}
