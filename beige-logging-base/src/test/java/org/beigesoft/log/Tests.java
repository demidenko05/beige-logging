/*
BSD 2-Clause License

Copyright (c) 2019, Beigesoft™
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

* Redistributions of source code must retain the above copyright notice, this
  list of conditions and the following disclaimer.

* Redistributions in binary form must reproduce the above copyright notice,
  this list of conditions and the following disclaimer in the documentation
  and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.beigesoft.log;

import java.util.ArrayList;
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
    LogFile log = new LogFile();
    log.setPath(currDir + fileBaseName);
    log.setClsImm(true);
    String logFilePath = log.getPath() + "0.log";
    File logFile = new File(logFilePath);
    if (logFile.exists()) {
      logFile.delete();
    }
    assertTrue(!logFile.exists());
    log.info(null, getClass(), "Tests file log 1-st str");
    try {
      Model m1 = new Model();
      m1.throwsException();
    } catch (Exception e) {
      log.error(null, getClass(), "tst ", e);
    }
    assertTrue(logFile.exists());
    Long size1 = Files.size(logFile.toPath());
    assertTrue(size1 > 0);
    log.info(null, getClass(), "Tests file log 2-nd str");
    Long size2 = Files.size(logFile.toPath());
    assertTrue(size2 > size1);
    log.setDbgSh(true);
    log.setDbgFl(8000);
    log.setDbgCl(8000);
    assertTrue(log.getDbgSh(this.getClass(), 8000));
    assertTrue(!log.getDbgSh(this.getClass(), 8001));
    assertTrue(!log.getDbgSh(this.getClass(), 7999));
    log.setRngMth(ERngMth.MULTI);
    log.setRanges(new ArrayList<Range>());
    Range rng1 = new Range();
    rng1.setDbgFl(8000);
    rng1.setDbgCl(8000);
    log.getRanges().add(rng1);
    Range rng2 = new Range();
    rng2.setDbgFl(7000);
    rng2.setDbgCl(7001);
    log.getRanges().add(rng2);
    assertTrue(log.getDbgSh(this.getClass(), 8000));
    assertTrue(!log.getDbgSh(this.getClass(), 8001));
    assertTrue(!log.getDbgSh(this.getClass(), 7999));
    assertTrue(log.getDbgSh(this.getClass(), 7000));
    assertTrue(log.getDbgSh(this.getClass(), 7001));
    assertTrue(!log.getDbgSh(this.getClass(), 6999));
  }

  @Test
  public void testFileLog2() throws Exception {
    String currDir = System.getProperty("user.dir") + File.separator;
    String fileBaseName = "test-file-thread-closed";
    LogFile log = new LogFile();
    log.setPath(currDir + fileBaseName);
    log.setClsImm(false);
    log.setMaxIdleTm(5000); //5sec
    String logFilePath = log.getPath() + "0.log";
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
    log.setNeedRun(false);
  }

  @Test
  public void testLogStandard() {
    LogStd log = new LogStd();
    log.debug(null, getClass(), "Tests standard log 1-st str");
    log.info(null, getClass(), "Tests standard log 2-nd str");
    log.warn(null, getClass(), "Tests standard log 3-nd str");
    log.error(null, getClass(), "Tests standard log 4-nd str");
  }
}
