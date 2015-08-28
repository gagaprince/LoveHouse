package wang.gagalulu.lovehouse.qr.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import wang.gagalulu.lovehouse.qr.services.QRService;
@Controller
@RequestMapping("/lovelulu/qr")
public class CreateQRController {
	@Autowired
	private QRService qrService;
	
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public void createQr(HttpServletRequest request,HttpServletResponse response, String qrStr){
		qrService.writeQrInResponse(qrStr, response);
	}
}
