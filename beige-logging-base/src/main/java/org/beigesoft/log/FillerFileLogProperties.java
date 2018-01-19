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

import java.util.Properties;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

/**
 * <p>Utility that fill file logger properties.</p>
 *
 * @author Yury Demidenko
 */
public class FillerFileLogProperties {

  /**
   * <p>Debug printer.</p>
   **/
  private IDebugPrinter debugPrinter;

  /**
   * <p>Logger files dir, it has higher priority (if settled by client)
   * than property "logDir" from properties file.</p>
   **/
  private String logDir;

  /**
   * <p>it means that application consist of only JAR
   * so it can be invoked by double click, so System property "user.dir"
   * will point to wrong place
   * e.g. beige-accounting-ajetty.jar.</p>
   **/
  private boolean isThereOnlyJar = true;

  /**
   * <p>It fills logger properties from properties file.</p>
   * @param pLog file logger
   * @param pFileBaseName log file base name e.g. "all-logs"
   **/
  public final void fillProperties(final ALoggerFile pLog,
    final String pFileBaseName) {
    String logPropFn = "/" + pFileBaseName + ".properties";
    URL urlSetting = FillerFileLogProperties.class.getResource(logPropFn);
    if (urlSetting != null) {
      this.debugPrinter.println(FillerFileLogProperties.class,
        "Found properties: " + logPropFn);
      InputStream inputStream = null;
      try {
        Properties props = new Properties();
        inputStream = FillerFileLogProperties.class
          .getResourceAsStream(logPropFn);
        props.load(inputStream);
        if (this.logDir == null) { // this.logDir has higher priority
          String lgDir = props.getProperty("logDir");
          if (lgDir != null) {
            this.logDir = lgDir;
          } else {
            String isThereOnlyJarStr = props.getProperty("isThereOnlyJar");
            if (isThereOnlyJarStr != null) {
              this.isThereOnlyJar = Boolean.valueOf(isThereOnlyJarStr);
            }
          }
        }
        String fileMaxSizeStr = props.getProperty("fileMaxSize");
        if (fileMaxSizeStr != null) {
          Integer fileMaxSize = Integer.parseInt(fileMaxSizeStr);
          pLog.setFileMaxSize(fileMaxSize);
        }
        String maxIdleTimeStr = props.getProperty("maxIdleTime");
        if (maxIdleTimeStr != null) {
          Integer maxIdleTime = Integer.parseInt(maxIdleTimeStr);
          pLog.setMaxIdleTime(maxIdleTime);
        }
        String isCloseFileAfterRecordStr = props
          .getProperty("isCloseFileAfterRecord");
        if (isCloseFileAfterRecordStr != null) {
          Boolean isCloseFileAfterRecord = Boolean
            .valueOf(isCloseFileAfterRecordStr);
          pLog.setIsCloseFileAfterRecord(isCloseFileAfterRecord);
        }
        String isShowDebugMessagesStr = props
          .getProperty("isShowDebugMessages");
        if (isShowDebugMessagesStr != null) {
          Boolean isShowDebugMessages = Boolean.valueOf(isShowDebugMessagesStr);
          pLog.setIsShowDebugMessages(isShowDebugMessages);
        }
      } catch (Exception ex) {
        this.debugPrinter.println(FillerFileLogProperties.class, ex);
      } finally {
        if (inputStream != null) {
          try {
            inputStream.close();
          } catch (Exception ex) {
            this.debugPrinter.println(FillerFileLogProperties.class, ex);
          }
        }
      }
    } else {
      this.debugPrinter.println(FillerFileLogProperties.class,
        "There is no properties: " + logPropFn);
    }
    if (this.logDir == null) {
      if (this.isThereOnlyJar) {
        try {
          File jarFl = new File(FillerFileLogProperties.class
            .getProtectionDomain().getCodeSource().getLocation()
              .toURI().getPath());
          this.logDir = jarFl.getParent();
        } catch (Exception ex) {
          this.logDir = System.getProperty("user.dir");
          this.debugPrinter.println(FillerFileLogProperties.class, ex);
        }
      } else {
        this.logDir = System.getProperty("user.dir");
      }
    }
    pLog.setFilePath(this.logDir + File.separator + pFileBaseName);
    this.debugPrinter.println(FillerFileLogProperties.class, "Log file path: "
      + pLog.getFilePath());
  }

  //Simple getters and setters:
  /**
   * <p>Getter for debugPrinter.</p>
   * @return IDebugPrinter
   **/
  public final IDebugPrinter getDebugPrinter() {
    return this.debugPrinter;
  }

  /**
   * <p>Setter for debugPrinter.</p>
   * @param pDebugPrinter reference
   **/
  public final void setDebugPrinter(final IDebugPrinter pDebugPrinter) {
    this.debugPrinter = pDebugPrinter;
  }

  /**
   * <p>Getter for logDir.</p>
   * @return String
   **/
  public final String getLogDir() {
    return this.logDir;
  }

  /**
   * <p>Setter for logDir.</p>
   * @param pLogDir reference
   **/
  public final void setLogDir(final String pLogDir) {
    this.logDir = pLogDir;
  }

  /**
   * <p>Getter for isThereOnlyJar.</p>
   * @return boolean
   **/
  public final boolean getIsThereOnlyJar() {
    return this.isThereOnlyJar;
  }

  /**
   * <p>Setter for isThereOnlyJar.</p>
   * @param pIsThereOnlyJar reference
   **/
  public final void setIsThereOnlyJar(final boolean pIsThereOnlyJar) {
    this.isThereOnlyJar = pIsThereOnlyJar;
  }
}
