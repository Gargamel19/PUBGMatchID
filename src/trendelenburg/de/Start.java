package trendelenburg.de;

import java.io.IOException;

public class Start {

	public static void main(String[] args) throws IOException {
		MatchIDConverter mid = new MatchIDConverter( "b6de33a5-928f-4d22-84e5-be782f5c7978");
		mid.getRequest();

	}

}
