package cn.ljl.javaweb.demo.ckeditor.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class StamperServiceImpl implements IStamperService {
    StamperServiceImpl() {
        super();
    }

    @Override
    public Image createImage(byte[] imageContent, float width, float height, float left,
            float bottom) {
        Image image = null;
        try {
            image = Image.getInstance(imageContent);
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        image.scaleAbsolute(width, height);
        image.setAbsolutePosition(left, bottom);
        
        return image;
    }
    
    @Override
    public Image createImage(byte[] imageContent) {
        Image image = createImage(imageContent, DEFAULT_WIDTH, DEFAULT_HEIGHT,
                DEFAULT_LEFT, DEFAULT_BOTTOM);
        return image;
    }
    
    @Override
    public void addImage(Image image, InputStream is, OutputStream os) {
        PdfReader reader = null;
        PdfStamper stamper = null;
        try {
            reader = new PdfReader(is);
            int nop = reader.getNumberOfPages();
            stamper = new PdfStamper(reader, os);
            PdfContentByte content = stamper.getOverContent(nop);
            content.addImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stamper != null) stamper.close();
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (reader != null) reader.close();
        }
    }
    
    /** 测试图片 */
    private static final String PATH_IMAGE = "C:\\Users\\HROCLOUD1507\\Desktop\\signature.gif";
    @Override
    public void testAddDefaultImage(InputStream is, OutputStream os) throws FileNotFoundException {
        InputStream imageIs = new FileInputStream(new File(PATH_IMAGE));
        byte[] imageContent = null;
        try {
        	System.out.println(imageIs);
            int length = imageIs.available();
            System.out.println(length);
            imageContent = new byte[length];
            imageIs.read(imageContent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                imageIs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        Image img = createImage(imageContent);
        addImage(img, is, os);
    }
}