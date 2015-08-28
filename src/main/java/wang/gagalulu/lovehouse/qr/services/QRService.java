package wang.gagalulu.lovehouse.qr.services;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.swetake.util.Qrcode;


@Service
public class QRService {
	private static final Logger logger =  Logger.getLogger(QRService.class);
	public void writeQrInResponse(String qrStr,HttpServletResponse response){
		logger.info("要生成二维码的字符串："+qrStr);
		int width = 140;
		try {  
			Qrcode handler = new Qrcode();  
	        handler.setQrcodeErrorCorrect('M');  
	        handler.setQrcodeEncodeMode('B');  
	        handler.setQrcodeVersion(7);  
	          
	        System.out.println(qrStr);  
	        byte[] contentBytes = qrStr.getBytes("UTF-8");  
	          
	        BufferedImage bufImg = new BufferedImage(width, width, BufferedImage.TYPE_INT_RGB);  
	          
	        Graphics2D gs = bufImg.createGraphics();  
	          
	        gs.setBackground(Color.WHITE);  
	        gs.clearRect(0, 0, width, width);  
	          
	        //设定图像颜色：BLACK  
	        gs.setColor(Color.BLACK);  
	          
	        //设置偏移量  不设置肯能导致解析出错  
	        int pixoff = 2;  
	        //输出内容：二维码  
	        if(contentBytes.length > 0 && contentBytes.length < 124) {  
	            boolean[][] codeOut = handler.calQrcode(contentBytes);  
	            for(int i = 0; i < codeOut.length; i++) {  
	                for(int j = 0; j < codeOut.length; j++) {  
	                    if(codeOut[j][i]) {  
	                        gs.fillRect(j * 3 + pixoff, i * 3 + pixoff,3, 3);  
	                    }  
	                }  
	            }  
	        } else {  
	        	logger.error("QRCode content bytes length = " + contentBytes.length + " not in [ 0,120 ]. ");  
	        }  
	          
	        gs.dispose();  
	        bufImg.flush();  
 
	        //生成二维码QRCode图片  
	        ImageIO.write(bufImg, "jpg", response.getOutputStream());  

	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  
		
	}
}