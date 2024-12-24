package javaIO;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class BytePracticing {
	
	public static void main(String[] args) {
		byte[] bytes = {66,73,83,83,69};
		
		ByteArrayInputStream inputStream = null;
		try {
			inputStream = new ByteArrayInputStream(bytes);
			int byteData;
			while((byteData = inputStream.read()) != -1) {
				System.out.print((char)byteData);
			}
		}catch(Exception e) {
			System.out.println("Error occured in inputstream");
		}finally {
			if(inputStream !=null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
