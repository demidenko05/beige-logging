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

import java.util.Map;
import java.util.Date;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.nio.charset.Charset;

/**
 * <p>Half implementation of simple logger to file.
 * Useful resource friendly logger.
 * All logs writes into only file.
 * There are two files [file-name]0.log and [file-name]1.log.
 * One of them is current.
 * Then current file size exceed maximum, then other will be used,
 * i.e. created or overwritten.
 * </p>
 *
 * @author Yury Demidenko
 */
public abstract class ALogFile extends ALog {

  /**
   * <p>Full file path without extension, e.g. "/home/gt/server",
   * so there will be "server0.log" and "server1.log".</p>
   **/
  private String path;

  /**
   * <p>Current log file.</p>
   **/
  private File file;

  /**
   * <p>Full max size in bytes, default 1048576 - 1Mb.</p>
   **/
  private int maxSize = 1048576;

  /**
   * <p>Current writer.</p>
   **/
  private OutputStreamWriter writer;

  /**
   * <p>Last time when file was written.</p>
   **/
  private long lstWrTm;

  /**
   * <p>Max idle time to close file in ms, default 3000 - 3 sec.</p>
   **/
  private long maxIdleTm = 3000;

  /**
   * <p>Current logs count that must be emptied after closing file.</p>
   **/
  private int logsCnt;

  /**
   * <p>Max logs count to close file, 300 default.</p>
   **/
  private int maxLogsCnt = 300;

  /**
   * <p>If close file after make log record immediately.</p>
   **/
  private boolean clsImm = false;

  /**
   * <p>Closer File.</p>
   **/
  private Closer closer;

  /**
   * <p>Make info log.</p>
   * @param pRqVs request scoped vars
   * @param pCls of bean
   * @param pMsg message
   **/
  @Override
  public final synchronized void info(final Map<String, Object> pRqVs,
    final Class<?> pCls, final String pMsg) {
    prn("thread#" + Thread.currentThread().getId() + " INFO " + getFmtDt()
  .format(new Date()) + " " + pCls.getSimpleName() + " - " + pMsg + getLnSp());
  }

  /**
   * <p>Make error log.</p>
   * @param pRqVs request scoped vars
   * @param pCls of bean
   * @param pMsg message
   **/
  @Override
  public final synchronized void error(final Map<String, Object> pRqVs,
    final Class<?> pCls, final String pMsg) {
    prn("thread#" + Thread.currentThread().getId() + " ERROR " + getFmtDt()
  .format(new Date()) + " " + pCls.getSimpleName() + " - " + pMsg + getLnSp());
  }

  /**
   * <p>Make error log.</p>
   * @param pRqVs request scoped vars
   * @param pCls of bean
   * @param pMsg message
   * @param pThr thrown
   **/
  @Override
  public final synchronized void error(final Map<String, Object> pRqVs,
  final Class<?> pCls, final String pMsg, final Throwable pThr) {
    StringBuffer sb = new StringBuffer();
    sb.append("thread#" + Thread.currentThread().getId() + " ERROR "
  + getFmtDt().format(new Date()) + " " + pCls.getSimpleName() + " - " + pMsg);
    excStr(sb, pThr);
    prn(sb.toString());
  }

  /**
   * <p>Make warn log.</p>
   * @param pRqVs request scoped vars
   * @param pCls of bean
   * @param pMsg message
   **/
  @Override
  public final synchronized void warn(final Map<String, Object> pRqVs,
    final Class<?> pCls, final String pMsg) {
    prn("thread#" + Thread.currentThread().getId() + " WARN " + getFmtDt()
  .format(new Date()) + " " + pCls.getSimpleName() + " - " + pMsg + getLnSp());
  }

  /**
   * <p>Make warn log.</p>
   * @param pRqVs request scoped vars
   * @param pCls of bean
   * @param pMsg message
   * @param pThr thrown
   **/
  @Override
  public final synchronized void warn(final Map<String, Object> pRqVs,
    final Class<?> pCls, final String pMsg, final Throwable pThr) {
    StringBuffer sb = new StringBuffer();
    sb.append("thread#" + Thread.currentThread().getId() + " WARN "
  + getFmtDt().format(new Date()) + " " + pCls.getSimpleName() + " - " + pMsg);
    excStr(sb, pThr);
    prn(sb.toString());
  }

  /**
   * <p>Write record into file.</p>
   * @param pMsg message
   **/
  protected final synchronized void prn(final String pMsg) {
    if (this.clsImm) {
      try {
        lazyGetWriter().write(pMsg);
      } catch (Exception ex) {
        ex.printStackTrace();
      } finally {
        if (this.writer != null) {
          try {
            this.writer.close();
          } catch (Exception ex) {
            ex.printStackTrace();
          } finally {
            this.writer = null;
          }
        }
      }
    } else {
      if (this.writer != null && this.logsCnt > this.maxLogsCnt) {
        this.logsCnt = 0;
        try {
          this.writer.close();
        } catch (Exception ex) {
          ex.printStackTrace();
        } finally {
          this.writer = null;
        }
      }
      try {
        lazyGetWriter().write(pMsg);
        this.logsCnt++;
        this.writer.flush();
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    this.lstWrTm = new Date().getTime();
  }

  /**
   * <p>Get writer in lazy mode.</p>
   * @return OutputStreamWriter
   **/
  public final synchronized OutputStreamWriter lazyGetWriter() {
    try {
      if (this.writer == null) {
        boolean apnd = true;
        File f = new File(getPath() + "0.log");
        if (f.exists() && f.length() >= this.maxSize) {
          File f1 = new File(getPath() + "1.log");
          if (!f1.exists() || f1.length() < this.maxSize) {
            f = f1;
          } else {
            apnd = false;
            if (f.lastModified() > f1.lastModified()) {
              f = f1;
            }
          }
        }
        if (f.exists()) {
          this.writer = new OutputStreamWriter(new FileOutputStream(f, apnd),
            Charset.forName("UTF-8").newEncoder());
        } else {
          this.writer = new OutputStreamWriter(new FileOutputStream(f),
            Charset.forName("UTF-8").newEncoder());
        }
        this.file = f;
        if (!this.clsImm
          && this.closer == null) {
          this.closer = new Closer();
          this.closer.start();
        }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return this.writer;
  }

  /**
   * <p>Thread to close idle file.</p>
   */
  private class Closer extends Thread {

    /**
     * <p>If need to run flag.</p>
     **/
    private boolean needRun = true;

    @Override
    public final void run() {
      while (this.needRun) {
        try {
          long currTm = new Date().getTime();
          synchronized (ALogFile.this) {
            if (ALogFile.this.getWriter() != null
              && (currTm - ALogFile.this.lstWrTm > ALogFile.this.maxIdleTm
                || ALogFile.this.file.length() > ALogFile.this.maxSize)) {
              try {
                ALogFile.this.writer.close();
              } catch (Exception ex) {
                ex.printStackTrace();
              } finally {
                ALogFile.this.logsCnt = 0;
                ALogFile.this.writer = null;
                ALogFile.this.file = null;
              }
            }
          }
          Thread.sleep(5000); //5 sec
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    }

    /**
     * <p>Setter for needRun.</p>
     * @param pNeedRun value
     **/
    public final void setNeedRun(final boolean pNeedRun) {
      this.needRun = pNeedRun;
    }
  };

  //Customized SGS:
  /**
   * <p>Setter for needRun.</p>
   * @param pNeedRun value
   **/
  public final synchronized void setNeedRun(final boolean pNeedRun) {
    if (this.closer != null) {
      this.closer.setNeedRun(pNeedRun);
    }
  }

  //Simple getters and setters:
  /**
   * <p>Setter for clsImm.</p>
   * @param pClsImm reference
   **/
  public final synchronized void setClsImm(
    final boolean pClsImm) {
    this.clsImm = pClsImm;
  }

  /**
   * <p>Getter for writer.</p>
   * @return OutputStreamWriter
   **/
  public final synchronized OutputStreamWriter getWriter() {
    return this.writer;
  }

  /**
   * <p>Getter for path.</p>
   * @return String
   **/
  public final synchronized String getPath() {
    return this.path;
  }

  /**
   * <p>Setter for path.</p>
   * @param pPath reference
   **/
  public final synchronized void setPath(final String pPath) {
    this.path = pPath;
  }

  /**
   * <p>Getter for file.</p>
   * @return File
   **/
  public final synchronized File getFile() {
    return this.file;
  }

  /**
   * <p>Getter for maxSize.</p>
   * @return Integer
   **/
  public final synchronized int getMaxSize() {
    return this.maxSize;
  }

  /**
   * <p>Setter for maxSize.</p>
   * @param pMaxSize reference
   **/
  public final synchronized void setMaxSize(final int pMaxSize) {
    this.maxSize = pMaxSize;
  }

  /**
   * <p>Getter for lstWrTm.</p>
   * @return long
   **/
  public final synchronized long getLstWrTm() {
    return this.lstWrTm;
  }

  /**
   * <p>Getter for maxIdleTm.</p>
   * @return long
   **/
  public final synchronized long getMaxIdleTm() {
    return this.maxIdleTm;
  }

  /**
   * <p>Setter for maxIdleTm.</p>
   * @param pMaxIdleTm reference
   **/
  public final synchronized void setMaxIdleTm(final long pMaxIdleTm) {
    this.maxIdleTm = pMaxIdleTm;
  }

  /**
   * <p>Getter for clsImm.</p>
   * @return Boolean
   **/
  public final synchronized boolean getClsImm() {
    return this.clsImm;
  }

  /**
   * <p>Getter for Closer.</p>
   * @return class
   **/
  public final synchronized Closer getCloser() {
    return this.closer;
  }

  /**
   * <p>Setter for Closer.</p>
   * @param pCloser reference
   **/
  public final synchronized void setCloser(final Closer pCloser) {
    this.closer = pCloser;
  }

  /**
   * <p>Getter for logsCnt.</p>
   * @return int
   **/
  public final synchronized int getLogsCnt() {
    return this.logsCnt;
  }


  /**
   * <p>Getter for maxLogsCnt.</p>
   * @return int
   **/
  public final synchronized int getMaxLogsCnt() {
    return this.maxLogsCnt;
  }

  /**
   * <p>Setter for maxLogsCnt.</p>
   * @param pMaxLogsCnt reference
   **/
  public final synchronized void setMaxLogsCnt(final int pMaxLogsCnt) {
    this.maxLogsCnt = pMaxLogsCnt;
  }
}
