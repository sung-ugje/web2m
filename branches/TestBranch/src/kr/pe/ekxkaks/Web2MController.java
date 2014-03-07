package kr.pe.ekxkaks;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import kr.pe.ekxkaks.web2m.web2m;
import kr.pe.ekxkaks.web2m.common.Constants;
import kr.pe.ekxkaks.web2m.common.ListData;
import kr.pe.ekxkaks.web2m.common.RequestQuery;
import kr.pe.ekxkaks.web2m.common.ViewData;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 웹 화면을 모바일 페이지로 변경해주는 컨트롤러
 * 
 * @author ekxkaks
 *
 */
@Controller
public class Web2MController {
	
	private static final Logger logger = LoggerFactory.getLogger(Web2MController.class);
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        logger.info("Welcome home! the client locale is {}",locale.toString());
        
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        
        String formattedDate = dateFormat.format(date);
        
        model.addAttribute("serverTime", formattedDate );
        
        return "home";
    }
    /**
     * 기본 메뉴 페이지를 호출한다.
     */
    @RequestMapping(value = "/menu.dkb")
    public String menu(RequestQuery req, Model model) {
        
        return "menu";
    }
    
    /**
     * 지정한 게시판의 글목록을 읽어와서 반환한다.
     */
    @RequestMapping(value = "/list.dkb")
    public String list(RequestQuery req, Model model) {
    	Constants.load();
        ListData list = web2m.readList(req.getSort(),StringUtils.defaultString(req.getPage(),"1"));

        model.addAttribute("list", list );
        return "list";
    }
    /**
     * 지정한 글의 내용을 읽어와서 반환한다.
     */
    @RequestMapping(value = "/view.dkb")
    public String view(RequestQuery req, Model model) {
    	Constants.load();
    	ViewData view = web2m.readView(StringUtils.defaultString(req.getDiv(),"cafe"),req.getSort(),StringUtils.defaultString(req.getNumber(),"1"));
    	
    	model.addAttribute("sort", req.getSort() );
        model.addAttribute("view", view );
        return "view";
    }
    
}
