package views;

public class Formatter {
	public static String formatText(String text) {
		String result = "";
		int length = text.length();
		int lineLength = 0;
		for(int i = 0; i < length; i++) {
			if(lineLength > 50 && !"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".contains(text.substring(i, i + 1))) {
				result += "\r\n";
				lineLength = 0;
			}
			result += text.charAt(i);
			lineLength++;
		}
		return result;
	}
}
