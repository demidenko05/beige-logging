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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.BeigeLoggerAdapter;
 
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

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
  public void testFileLog() throws Exception {
    BeigeLoggerAdapter logger = (BeigeLoggerAdapter) LoggerFactory.getLogger(Tests.class);
    LoggerFile lf = (LoggerFile) logger.getLogger();
    String logFilePath = lf.getFilePath() + "0.log";
    File logFile = new File(logFilePath);
    if (logFile.exists()) {
      logFile.delete();
    }
    assertTrue(!logFile.exists());
    logger.info("Tests file log");
    assertTrue(logFile.exists());
    assertTrue(Files.size(logFile.toPath()) > 0);
    assertEquals(new Integer(999999), lf.getFileMaxSize());
    assertEquals(Boolean.TRUE, lf.getIsShowDebugMessages());
  }
}
