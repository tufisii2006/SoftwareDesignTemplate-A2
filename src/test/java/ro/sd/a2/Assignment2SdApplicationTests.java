package ro.sd.a2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.logging.Logger;


@RunWith(MockitoJUnitRunner.class)
class Assignment2SdApplicationTests {

  Logger logger = Logger.getLogger(Assignment2SdApplicationTests.class.getName());

  @Test
  void simpleTest() {
    logger.info("Running Simple test");
    int a = 2;
    Assertions.assertTrue(a + 2 > 3);
  }

  @Test
  void anotherSimpleTest() {
    logger.info("Running another Simple test");
    int b = 3;
    Assertions.assertEquals(9, b + 6);
  }

}
