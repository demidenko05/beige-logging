package org.beigesoft.log;

/*
 * Copyright (c) 2017 Beigesoft ™
 *
 * Licensed under the GNU General Public License (GPL), Version 2.0
 * (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 */

import java.io.*;
import java.nio.file.Files;
 
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * <p>
 * All tests.
 * </p>
 *
 * @author Yury Demidenko
 */
public class Tests {

  @Test
  public void testFileLog1() throws Exception {
    String currDir = System.getProperty("user.dir") + File.separator;
    String fileBaseName = "test-file-ac";
    LoggerFile log = new LoggerFile();
    log.setFilePath(currDir + fileBaseName);
    log.setIsCloseFileAfterRecord(true);
    String logFilePath = log.getFilePath() + "0.log";
    File logFile = new File(logFilePath);
    if (logFile.exists()) {
      logFile.delete();
    }
    assertTrue(!logFile.exists());
    log.info(null, getClass(), "Tests file log 1-st str");
    assertTrue(logFile.exists());
    Long size1 = Files.size(logFile.toPath());
    assertTrue(size1 > 0);
    log.info(null, getClass(), "Tests file log 2-nd str");
    Long size2 = Files.size(logFile.toPath());
    assertTrue(size2 > size1);
  }

  @Test
  public void testFileLog2() throws Exception {
    String currDir = System.getProperty("user.dir") + File.separator;
    String fileBaseName = "test-file-thread-closed";
    LoggerFile log = new LoggerFile();
    log.setFilePath(currDir + fileBaseName);
    log.setIsCloseFileAfterRecord(false);
    log.setMaxIdleTime(5000); //5sec
    String logFilePath = log.getFilePath() + "0.log";
    File logFile = new File(logFilePath);
    if (logFile.exists()) {
      logFile.delete();
    }
    assertTrue(!logFile.exists());
    log.info(null, getClass(), "Tests file log 1-st str");
    assertTrue(logFile.exists());
    Long size1 = Files.size(logFile.toPath());
    assertTrue(size1 > 0);
    log.info(null, getClass(), "Tests file log 2-nd str");
    Long size2 = Files.size(logFile.toPath());
    assertTrue(size2 > size1);
    assertNotNull(log.getWriter());
    for (int i=0; i < 3; i++) {
      try {
        Thread.sleep(5000); //5 sec
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    assertNull(log.getWriter());
  }

  @Test
  public void testLoggerStandard() {
    LoggerStandard log = new LoggerStandard();
    log.debug(null, getClass(), "Tests standard log 1-st str");
    log.info(null, getClass(), "Tests standard log 2-nd str");
    log.warn(null, getClass(), "Tests standard log 3-nd str");
    log.error(null, getClass(), "Tests standard log 4-nd str");
  }
}
