package kaishen.example.demo.web;

import kaishen.example.demo.config.DemoProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo/home")
public class DemoController {

  private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

  @Autowired
  DemoProperties demoProperties;

  @ResponseBody
  @RequestMapping(value = "/testProperties", method = {RequestMethod.GET})
  public String testProperties(){
    logger.info("inside request");
    return demoProperties.getField1() == null ? "no properties": demoProperties.getField1();
  }
}
