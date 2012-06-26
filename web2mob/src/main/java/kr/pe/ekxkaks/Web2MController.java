package kr.pe.ekxkaks;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import kr.pe.ekxkaks.web2m.web2m;
import kr.pe.ekxkaks.web2m.common.ListData;
import kr.pe.ekxkaks.web2m.common.RequestQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String menu(@RequestParam RequestQuery req, Model model) {
        
        return "menu";
    }
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/list.dkb")
    public String list(@RequestParam RequestQuery req, Model model) {
        
        ListData list = web2m.readList(req.getSort(),req.getPage());

        model.addAttribute("list", list );
        return "list";
    }
    
}
