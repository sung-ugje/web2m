package kr.pe.ekxkaks;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import kr.pe.ekxkaks.web2m.web2m;
import kr.pe.ekxkaks.web2m.common.Constants;
import kr.pe.ekxkaks.web2m.common.ListData;
import kr.pe.ekxkaks.web2m.common.RequestQuery;
import kr.pe.ekxkaks.web2m.common.StringUtil;
import kr.pe.ekxkaks.web2m.common.ViewData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class Web2MController {
	
	private static final Logger logger = LoggerFactory.getLogger(Web2MController.class);
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        logger.info("Welcome home! the client locale is "+ locale.toString());
        
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        
        String formattedDate = dateFormat.format(date);
        
        model.addAttribute("serverTime", formattedDate );
        
        return "home";
    }
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/menu.dkb")
    public String menu(RequestQuery req, Model model) {
        
        return "menu";
    }
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/list.dkb")
    public String list(RequestQuery req, Model model) {
    	Constants.load();
        ListData list = web2m.readList(req.getSort(),StringUtil.nvl(req.getPage(),"1"));

        model.addAttribute("list", list );
        return "list";
    }
    @RequestMapping(value = "/view.dkb")
    public String view(RequestQuery req, Model model) {
    	Constants.load();
    	System.out.println("\n\n\ndiv : "+StringUtil.nvl(req.getDiv(),"cafe")+" , sort : "+req.getSort()+", number : "+StringUtil.nvl(req.getNumber(),"1") + "\n\n\n\n\n\n\n\n");
    	ViewData view = web2m.readView(StringUtil.nvl(req.getDiv(),"cafe"),req.getSort(),StringUtil.nvl(req.getNumber(),"1"));
    	
    	model.addAttribute("sort", req.getSort() );
        model.addAttribute("view", view );
        return "view";
    }
    
}
