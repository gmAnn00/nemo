package nemo.test;

public class Getpath {
	private static String path = "D:\\AGM\\board\\image_upload";
	private static String path2 = "C:/Users/dkstp/OneDrive/Desktop/GB/project/nemo/backend";
	
	public static void main(String[] args) {
		
		path2 = path2.replace("/", "\\");
		System.out.println(path2);
		
		path.replace("\\", "/");
		//System.out.println(path);
	}

}
