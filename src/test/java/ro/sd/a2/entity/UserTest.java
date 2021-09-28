package ro.sd.a2.entity;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

  Logger logger = Logger.getLogger(UserTest.class.getName());

  @Test
  void getId() {
    logger.info("Testing getId method");
    int a = 2;
    assertEquals(a, 2);
  }

}