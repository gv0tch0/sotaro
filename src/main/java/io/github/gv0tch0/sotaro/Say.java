package io.github.gv0tch0.sotaro;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Say {
  private final static ObjectFactory JAXB_FACTORY = new ObjectFactory();
  private static final TimeZone UTC = TimeZone.getTimeZone("UTC");
  private final static DatatypeFactory CALENDAR_FACTORY;
  static {
    try {
      CALENDAR_FACTORY = DatatypeFactory.newInstance();
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  @RequestMapping(value = "/say/{what}", 
                  produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, 
                  method = RequestMethod.GET)
  public @ResponseBody JAXBElement<SayWhat> say(@PathVariable("what") String what) {
    return echo(what);
  }
  
  private JAXBElement<SayWhat> echo(String what) {
    SayWhat echo = new SayWhat();
    echo.setWhat(what);
    echo.setWhen(utcNow());
    return JAXB_FACTORY.createSay(echo);
  }

  private XMLGregorianCalendar utcNow() {
    GregorianCalendar c = new GregorianCalendar(UTC);
    c.setTime(new Date());
    XMLGregorianCalendar now = CALENDAR_FACTORY.newXMLGregorianCalendar(c);
    now.setFractionalSecond(null);
    return now;
  }
}
