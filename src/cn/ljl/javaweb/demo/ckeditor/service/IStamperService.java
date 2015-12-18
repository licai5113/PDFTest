package cn.ljl.javaweb.demo.ckeditor.service;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itextpdf.text.Image;
/**
 * 设置图片、添加图片.
 * @author lijinlong
 *
 */
public interface IStamperService {
    public static final Log logger = LogFactory.getLog(IStamperService.class);
    
    /** 默认宽度 */
    float DEFAULT_WIDTH = 100f;
    /** 默认高度 */
    float DEFAULT_HEIGHT = 100f;
    /** 默认x坐标 */
    float DEFAULT_LEFT = 440f;
    /** 默认y坐标 */
    float DEFAULT_BOTTOM = 50f;
    
    /**
     * 根据指定的图片数据，封装成{@link Image}对象实例.
     * 
     * @param imageContent
     *            图片内容
     * @param width
     *            图片宽度
     * @param height
     *            图片高度
     * @param left
     *            图片X坐标
     * @param top
     *            图片Y坐标
     */
    public Image createImage(byte[] imageContent, float width, float height,
            float left, float bottom);
    
    /**
     * 使用默认的数据创建{@link Image}对象实例.
     * @param imageContent 图像内容.
     * @return
     */
    public Image createImage(byte[] imageContent);
    
    /**
     * 加入图片.<br/>
     * 要求先调用{@link #setImage(byte[], float, float, float, float)}设置图片;<br/>
     * 从指定的输入流读入pdf内容，加入图片之后写到指定的输出流.<br/>
     * @param image 指定作为印章的{@link Image}实例.
     * @param is
     * @param os
     */
    public void addImage(Image image, InputStream is, OutputStream os);
    
    /**
     * 将默认的图片加入pdf.<br/>
     * 这个方法仅用于功能测试.
     * @param is
     * @param os
     * @throws FileNotFoundException 
     */
    public void testAddDefaultImage(InputStream is, OutputStream os) throws FileNotFoundException;
}