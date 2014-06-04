package io.github.gv0tch0.sotaro;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/say")
public class Say {
  @RequestMapping(
      value = "/{what}", 
      produces = {MediaType.APPLICATION_XML_VALUE}, 
      method = RequestMethod.GET)
  @ResponseBody
  public SayWhat say(@PathVariable("what") String what) {
    return echo(what);
  }
  
  private SayWhat echo(String what) {
    SayWhat echo = new SayWhat();
    echo.setWhat(what);
    return echo;
  }
}
