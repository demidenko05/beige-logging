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
public abstract class ALoggerFile extends ALogger {

  /**
   * <p>Full file path without extension, e.g. "/home/gt/server",
   * so there will be "server0.log" and "server1.log".</p>
   **/
  private String filePath;

  /**
   * <p>Current log file.</p>
   **/
  private File currentFile;

  /**
   * <p>Full max size in bytes, default 1048576 - 1Mb.</p>
   **/
  private Integer fileMaxSize = 1048576;

  /**
   * <p>Current writer.</p>
   **/
  private OutputStreamWriter writer;

  /**
   * <p>Last time when file was written.</p>
   **/
  private long lastWriteTime;

  /**
   * <p>Max idle time to close file in ms, default 3000 - 3 sec.</p>
   **/
  private long maxIdleTime = 3000;

  /**
   * <p>Current logs count that emptied after closing file.</p>
   **/
  private int logsCount;

  /**
   * <p>Max logs count to close file, 300 default.</p>
   **/
  private int maxLogsCountToClose = 300;

  /**
   * <p>If close file after make log record.</p>
   **/
  private Boolean isCloseFileAfterRecord = false;

  /**
   * <p>Closer File.</p>
   **/
  private CloserFile closerFile;

  /**
   * <p>Make info log.</p>
   * @param pAddParam additional param
   * @param pClazz of bean
   * @param pMsg message
   **/
  @Override
  public final synchronized void info(final Map<String, Object> pAddParam,
    final Class<?> pClazz, final String pMsg) {
    makeRecord(getDateFormat().format(new Date()) + " INFO "
      + pClazz.getSimpleName() + " - " + pMsg + getLineSeparator());
  }

  /**
   * <p>Make error log.</p>
   * @param pAddParam additional param
   * @param pClazz of bean
   * @param pMsg message
   **/
  @Override
  public final synchronized void error(final Map<String, Object> pAddParam,
    final Class<?> pClazz, final String pMsg) {
    makeRecord(getDateFormat().format(new Date()) + " ERROR "
      + pClazz.getSimpleName() + " - " + pMsg + getLineSeparator());
  }

  /**
   * <p>Make error log.</p>
   * @param pAddParam additional param
   * @param pClazz of bean
   * @param pMsg message
   * @param pThrown thrown
   **/
  @Override
  public final synchronized void error(final Map<String, Object> pAddParam,
  final Class<?> pClazz,
    final String pMsg, final Throwable pThrown) {
    StringBuffer sb = new StringBuffer();
    sb.append(getDateFormat().format(new Date()) + " ERROR "
      + pClazz.getSimpleName() + " - " + pMsg);
    exceptionToString(sb, pThrown);
    makeRecord(sb.toString());
  }

  /**
   * <p>Make warn log.</p>
   * @param pAddParam additional param
   * @param pClazz of bean
   * @param pMsg message
   **/
  @Override
  public final synchronized void warn(final Map<String, Object> pAddParam,
    final Class<?> pClazz, final String pMsg) {
    makeRecord(getDateFormat().format(new Date()) + " WARN "
    + pClazz.getSimpleName() + " - " + pMsg + getLineSeparator());
  }

  /**
   * <p>Make warn log.</p>
   * @param pAddParam additional param
   * @param pClazz of bean
   * @param pMsg message
   * @param pThrown thrown
   **/
  @Override
  public final synchronized void warn(final Map<String, Object> pAddParam,
    final Class<?> pClazz, final String pMsg, final Throwable pThrown) {
    StringBuffer sb = new StringBuffer();
    sb.append(getDateFormat().format(new Date()) + " WARN "
      + pClazz.getSimpleName() + " - " + pMsg);
    exceptionToString(sb, pThrown);
    makeRecord(sb.toString());
  }

  @Override
  protected final synchronized void finalize() throws Throwable {
    if (this.closerFile != null) {
      this.closerFile.setIsNeedToRun(false);
    }
    if (this.writer != null) {
      try {
        this.writer.close();
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }

  /**
   * <p>Make record in file.</p>
   * @param pMsg message
   **/
  protected final synchronized void makeRecord(final String pMsg) {
    if (this.isCloseFileAfterRecord) {
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
      if (this.writer != null
        && this.logsCount > this.maxLogsCountToClose) {
        this.logsCount = 0;
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
        this.logsCount++;
        this.writer.flush();
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    this.lastWriteTime = new Date().getTime();
  }

  /**
   * <p>Get writer in lazy mode.</p>
   * @return OutputStreamWriter
   **/
  public final synchronized OutputStreamWriter lazyGetWriter() {
    try {
      if (this.writer == null) {
        File f = new File(getFilePath() + "0.log");
        if (f.exists() && f.length() >= this.fileMaxSize) {
          File f1 = new File(getFilePath() + "1.log");
          if (!f1.exists() || f1.length() < this.fileMaxSize) {
            f = f1;
          }
        }
        boolean isAppend = f.exists();
        this.writer = new OutputStreamWriter(new FileOutputStream(f, isAppend),
            Charset.forName("UTF-8").newEncoder());
        this.currentFile = f;
        if (!this.isCloseFileAfterRecord
          && this.closerFile == null) {
          this.closerFile = new CloserFile();
          this.closerFile.start();
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
  private class CloserFile extends Thread {

    /**
     * <p>If need to run flag.</p>
     **/
    private boolean isNeedToRun = true;

    @Override
    public final void run() {
      System.out.println("Started closer of logger file: "
        + ALoggerFile.this.getFilePath());
      while (this.isNeedToRun) {
        try {
          long currTime = new Date().getTime();
          synchronized (ALoggerFile.this) {
            if (ALoggerFile.this.getWriter() != null
              && (currTime - ALoggerFile.this.lastWriteTime > maxIdleTime
                || ALoggerFile.this.currentFile.length()
                  > ALoggerFile.this.fileMaxSize)) {
              try {
                ALoggerFile.this.getWriter().close();
              } catch (Exception ex) {
                ex.printStackTrace();
              } finally {
                ALoggerFile.this.logsCount = 0;
                ALoggerFile.this.writer = null;
                ALoggerFile.this.currentFile = null;
              }
            }
          }
          Thread.sleep(5000); //5 sec
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
      System.out.println("Stopped closer of logger file: "
        + ALoggerFile.this.getFilePath());
    }

    /**
     * <p>Setter for isNeedToRun.</p>
     * @param pIsNeedToRun value
     **/
    public final void setIsNeedToRun(final boolean pIsNeedToRun) {
      this.isNeedToRun = pIsNeedToRun;
    }
  };

  //Customized SGS:
  /**
   * <p>Setter for isNeedToRun.</p>
   * @param pIsNeedToRun value
   **/
  public final synchronized void setIsNeedToRun(final boolean pIsNeedToRun) {
    if (this.closerFile != null) {
      this.closerFile.setIsNeedToRun(pIsNeedToRun);
    }
  }

  //Simple getters and setters:
  /**
   * <p>Setter for isCloseFileAfterRecord.</p>
   * @param pIsCloseFileAfterRecord reference
   **/
  public final synchronized void setIsCloseFileAfterRecord(
    final Boolean pIsCloseFileAfterRecord) {
    this.isCloseFileAfterRecord = pIsCloseFileAfterRecord;
  }

  /**
   * <p>Getter for writer.</p>
   * @return OutputStreamWriter
   **/
  public final synchronized OutputStreamWriter getWriter() {
    return this.writer;
  }

  /**
   * <p>Getter for filePath.</p>
   * @return String
   **/
  public final synchronized String getFilePath() {
    return this.filePath;
  }

  /**
   * <p>Getter for currentFile.</p>
   * @return File
   **/
  public final synchronized File getCurrentFile() {
    return this.currentFile;
  }

  /**
   * <p>Setter for filePath.</p>
   * @param pFilePath reference
   **/
  public final synchronized void setFilePath(final String pFilePath) {
    this.filePath = pFilePath;
  }

  /**
   * <p>Getter for fileMaxSize.</p>
   * @return Integer
   **/
  public final synchronized Integer getFileMaxSize() {
    return this.fileMaxSize;
  }

  /**
   * <p>Setter for fileMaxSize.</p>
   * @param pFileMaxSize reference
   **/
  public final synchronized void setFileMaxSize(final Integer pFileMaxSize) {
    this.fileMaxSize = pFileMaxSize;
  }

  /**
   * <p>Getter for lastWriteTime.</p>
   * @return long
   **/
  public final synchronized long getLastWriteTime() {
    return this.lastWriteTime;
  }

  /**
   * <p>Getter for maxIdleTime.</p>
   * @return long
   **/
  public final synchronized long getMaxIdleTime() {
    return this.maxIdleTime;
  }

  /**
   * <p>Setter for maxIdleTime.</p>
   * @param pMaxIdleTime reference
   **/
  public final synchronized void setMaxIdleTime(final long pMaxIdleTime) {
    this.maxIdleTime = pMaxIdleTime;
  }

  /**
   * <p>Getter for isCloseFileAfterRecord.</p>
   * @return Boolean
   **/
  public final synchronized Boolean getIsCloseFileAfterRecord() {
    return this.isCloseFileAfterRecord;
  }

  /**
   * <p>Getter for CloserFile.</p>
   * @return class
   **/
  public final synchronized CloserFile getCloserFile() {
    return this.closerFile;
  }

  /**
   * <p>Setter for CloserFile.</p>
   * @param pCloserFile reference
   **/
  public final synchronized void setCloserFile(final CloserFile pCloserFile) {
    this.closerFile = pCloserFile;
  }

  /**
   * <p>Getter for logsCount.</p>
   * @return int
   **/
  public final synchronized int getLogsCount() {
    return this.logsCount;
  }


  /**
   * <p>Getter for maxLogsCountToClose.</p>
   * @return int
   **/
  public final synchronized int getMaxLogsCountToClose() {
    return this.maxLogsCountToClose;
  }

  /**
   * <p>Setter for maxLogsCountToClose.</p>
   * @param pMaxLogsCountToClose reference
   **/
  public final synchronized void setMaxLogsCountToClose(
    final int pMaxLogsCountToClose) {
    this.maxLogsCountToClose = pMaxLogsCountToClose;
  }
}
