package ro.sd.a2.entity;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
class UserTest {

  Logger logger = Logger.getLogger(UserTest.class.getName());

  @Test
  void getId() {
    logger.info("Testing getId method");
    int a = 2;
    assertEquals(a, 2);
  }

}