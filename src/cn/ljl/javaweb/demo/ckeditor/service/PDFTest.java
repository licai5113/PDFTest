package cn.ljl.javaweb.demo.ckeditor.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;



public class PDFTest {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		IStamperService iserver=new StamperServiceImpl();
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(new File("C:\\Users\\HROCLOUD1507\\Desktop\\Visa improvement.pdf"));
			os=new FileOutputStream(new File("../pdftest/1.pdf"));
			iserver.testAddDefaultImage(is, os);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				is.close();
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}

}
