package app;

public class LibaryApp {
	public static void main(String[] args) {

		String appName = "Biblioteka v1.0";
		System.out.println(appName);
		LibraryControl libControl = new LibraryControl();
		libControl.controlLoop();
	}
}
