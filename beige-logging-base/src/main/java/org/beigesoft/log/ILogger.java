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

/**
 * <p>Abstraction of simple, non-static, OOP and memory friendly Logger.</p>
 *
 * @author Yury Demidenko
 */
public interface ILogger {

  /**
   * <p>Make debug log.</p>
   * @param pAddParam additional param, e.g. report to separate file
   * @param pClazz of debudgged bean
   * @param pMsg message
   **/
  void debug(Map<String, Object> pAddParam,
    Class<?> pClazz, String pMsg);

  /**
   * <p>Make debug log.</p>
   * @param pAddParam additional param
   * @param pClazz of debudgged bean
   * @param pMsg message
   * @param pThrown thrown
   **/
  void debug(Map<String, Object> pAddParam,
    Class<?> pClazz, String pMsg, Throwable pThrown);

  /**
   * <p>Make info log.</p>
   * @param pAddParam additional param
   * @param pClazz of bean
   * @param pMsg message
   **/
  void info(Map<String, Object> pAddParam,
    Class<?> pClazz, String pMsg);

  /**
   * <p>Make error log.</p>
   * @param pAddParam additional param
   * @param pClazz of bean
   * @param pMsg message
   **/
  void error(Map<String, Object> pAddParam,
    Class<?> pClazz, String pMsg);

  /**
   * <p>Make error log.</p>
   * @param pAddParam additional param
   * @param pClazz of bean
   * @param pMsg message
   * @param pThrown thrown
   **/
  void error(Map<String, Object> pAddParam,
    Class<?> pClazz, String pMsg, Throwable pThrown);

  /**
   * <p>Make warn log.</p>
   * @param pAddParam additional param, e.g. report immediately to given email
   * @param pClazz of bean
   * @param pMsg message
   **/
  void warn(Map<String, Object> pAddParam,
    Class<?> pClazz, String pMsg);

  /**
   * <p>Make warn log for cases like "try to open non-exist file".</p>
   * @param pAddParam additional param
   * @param pClazz of bean
   * @param pMsg message
   * @param pThrown thrown
   **/
  void warn(Map<String, Object> pAddParam,
    Class<?> pClazz, String pMsg, Throwable pThrown);

  /**
   * <p>Get is show debug messages.</p>
   * @return is show debug messages?
   **/
  boolean getIsShowDebugMessages();

  /**
   * <p>Get preferred detail level, e.g. for suppress unwanted
   * debug messages for deep level #3:
   * <pre>
   *  if (this.logger.getIsShowDebugMessagesFor(this.getClass())
   *  && this.logger.getDetailLevel() >= 3) {
   *    ... 3-d detail level message
   *  }
   * </pre>
   * </p>
   * @return preferred detail level
   **/
  int getDetailLevel();

  /**
   * <p>Set preferred detail level.</p>
   * @param pDetailLevel preferred detail level
   **/
  void setDetailLevel(int pDetailLevel);

  /**
   * <p>Set is show debug messages preference.</p>
   * @param pIsShowDebugMessage is show debug messages?
   **/
  void setIsShowDebugMessages(boolean pIsShowDebugMessage);

  /**
   * <p>Set is show debug messages for class preference.</p>
   * @param pClazz of bean
   * @param pIsShowDebugMessage is show debug messages?
   **/
  void setIsShowDebugMessagesFor(Class<?> pClazz, boolean pIsShowDebugMessage);

  /**
   * <p>Get is show debug messages preference for class.
   * Use code like is not hit performance:
   * <pre>
   *  if (this.logger.getIsShowDebugMessagesFor(this.getClass())) {
   *    // make some hard job:
   *    String msg = " data for " + formatDate(doc.dt) + " x1, x2"
   *      + Math.round(x1, this.settings.getRoundingMode()) .....
   *    // report message:
   *    this.logger.debug(this.getClass(), msg);
   *  }
   * </pre>
   * Client must itself use this method in this way
   * for performance reason, i.e. logger will report debug message
   * without checking the flag.
   * </p>
   * @param pClazz of bean
   * @return is show debug messages?
   **/
  boolean getIsShowDebugMessagesFor(Class<?> pClazz);

  /**
   * <p>Get client preferences.
   * It's for ease extend debugging abilities.</p>
   * @return Map<String, Object>
   **/
  Map<String, Object> getClientPreferences();

  /**
   * <p>Set client preferences.
   * It's for ease extend debugging abilities.</p>
   * @param pClientPreferences reference
   **/
  void setClientPreferences(
    Map<String, Object> pClientPreferences);
}
