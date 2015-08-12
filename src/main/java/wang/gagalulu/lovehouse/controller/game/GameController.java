package wang.gagalulu.lovehouse.controller.game;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import wang.gagalulu.lovehouse.annotation.WXShareController;

@Controller
@RequestMapping("/lovelulu/game")
public class GameController {
	@WXShareController
	@RequestMapping(value = "/oneSecond", method = RequestMethod.GET)
	public String goOneSecondGame(HttpServletRequest request, Model model){
		return "/game/oneSecond";
	}
}
