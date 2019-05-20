package org.beigesoft.lgacl;

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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import org.beigesoft.log.*;

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
    System.setProperty("org.apache.commons.logging.diagnostics.dest", "STDOUT");
    System.setProperty("org.apache.commons.logging.LogFactory", "org.apache.commons.logging.impl.BsfLogFlFct");
    Log logger = LogFactory.getLog(Tests.class);
    logger.info("Tests file log1");
    Log logger2 = LogFactory.getLog(LogFile.class);
    logger.info("Tests file log2");
    assertEquals(logger, logger2);
    System.out.println("Logger object - >" + logger2);
  }
}
