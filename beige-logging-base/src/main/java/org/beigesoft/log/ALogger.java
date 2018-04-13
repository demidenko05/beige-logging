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
import java.util.HashMap;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * <p>Abstract Logger with date, show debug message flags
 * and throwable formatter.</p>
 *
 * @author Yury Demidenko
 */
public abstract class ALogger implements ILogger {

  /**
   * <p>Line separator.</p>
   **/
  private String lineSeparator = System.getProperty("line.separator");

  /**
   * <p>Is show debug messages main flag, false default.</p>
   **/
  private boolean isShowDebugMessage = false;

  /**
   * <p>Is show debug messages flags for classes.</p>
   **/
  private Map<Class<?>, Boolean> isShowDebugMessageMap;

  /**
   * <p>Date formatter.</p>
   **/
  private DateFormat dateFormat =
    new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

  /**
   * <p>Client other preferences.
   * It's for ease extend debugging abilities.</p>
   **/
  private Map<String, Object> clientPreferences;

  /**
   * <p>Get preferred detail level.</p>
   **/
  private int detailLevel = 0;

  /**
   * <p>Get preferred detail level, e.g. for suppress unwanted
   * debug messages for deep level #3:
   * <pre>
   *  if (this.logger.getIsShowDebugMessagesFor(this.getClass())
   *  && 3 >= this.logger.getDetailLevel()) {
   *    ... 3-d detail level message
   *  }
   * </pre>
   * </p>
   * @return is show debug messages?
   **/
  @Override
  public final int getDetailLevel() {
    return this.detailLevel;
  }

  /**
   * <p>Set preferred detail level.</p>
   * @param pDetailLevel preferred detail level
   **/
  @Override
  public final void setDetailLevel(final int pDetailLevel) {
    this.detailLevel = pDetailLevel;
  }

  /**
   * <p>Set is show debug messages.</p>
   * @param pIsShowDebugMessage is show debug messages?
   **/
  @Override
  public final void setIsShowDebugMessages(
    final  boolean pIsShowDebugMessage) {
    this.isShowDebugMessage = pIsShowDebugMessage;
  }

  /**
   * <p>Get is show debug messages preference.</p>
   * @return is show debug messages?
   **/
  @Override
  public final boolean getIsShowDebugMessages() {
    return this.isShowDebugMessage;
  }

  /**
   * <p>Set is show debug messages for class preference.</p>
   * @param pClazz of bean
   * @param pIsShowDebugMessage is show debug messages?
   **/
  @Override
  public final void setIsShowDebugMessagesFor(final Class<?> pClazz,
    final  boolean pIsShowDebugMessage) {
    if (this.isShowDebugMessageMap == null) {
      this.isShowDebugMessageMap = new HashMap<Class<?>, Boolean>();
    }
    this.isShowDebugMessageMap.put(pClazz, pIsShowDebugMessage);
  }

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
  @Override
  public final boolean getIsShowDebugMessagesFor(final Class<?> pClazz) {
    Boolean result = null;
    if (this.isShowDebugMessageMap != null) {
      result = this.isShowDebugMessageMap.get(pClazz);
    }
    if (result == null) {
      return this.isShowDebugMessage;
    } else {
      return result;
    }
  }

  /**
   * <p>Get client preferences.
   * It's for ease extend debugging abilities.</p>
   * @return Map<String, Object>
   **/
  @Override
  public final Map<String, Object> getClientPreferences() {
    return this.clientPreferences;
  }

  /**
   * <p>Set client preferences.
   * It's for ease extend debugging abilities.</p>
   * @param pClientPreferences reference
   **/
  @Override
  public final void setClientPreferences(
    final Map<String, Object> pClientPreferences) {
    this.clientPreferences = pClientPreferences;
  }

  //Utils:
  /**
   * <p>Append thrown message to the buffer.</p>
   * @param pBuffer Buffer
   * @param pThrown throwable
   **/
  public final void exceptionToString(final StringBuffer pBuffer,
    final Throwable pThrown) {
    if (pThrown == null) {
        pBuffer.append(" ex-null");
    } else {
      pBuffer.append(pThrown.toString());
      StackTraceElement[] elements = pThrown.getStackTrace();
      for (int i = 0; elements != null && i < elements.length; i++) {
        pBuffer.append(this.lineSeparator + "\tat ");
        pBuffer.append(elements[i].toString());
      }
      for (Throwable suppressed : pThrown.getSuppressed()) {
        pBuffer.append(this.lineSeparator + "Suppressed: ");
        pBuffer.append(suppressed.toString() + "\t|");
      }
      Throwable cause = pThrown.getCause();
      if (cause != null && cause != pThrown) { //2-nd level
        pBuffer.append(this.lineSeparator).append("Caused by: ");
        pBuffer.append(cause);
        elements = cause.getStackTrace();
        for (int i = 0; elements != null && i < elements.length; i++) {
          pBuffer.append(this.lineSeparator + "\tat ");
          pBuffer.append(elements[i].toString());
        }
        for (Throwable suppressed : cause.getSuppressed()) {
          pBuffer.append(this.lineSeparator + "Suppressed: ");
          pBuffer.append(suppressed.toString() + "\t|");
        }
        Throwable cause1 = cause.getCause();
        if (cause1 != null && cause1 != cause) { //3-d level
          pBuffer.append(this.lineSeparator).append("Caused by: ");
          pBuffer.append(cause1);
          elements = cause1.getStackTrace();
          for (int i = 0; elements != null && i < elements.length; i++) {
            pBuffer.append(this.lineSeparator + "\tat ");
            pBuffer.append(elements[i].toString());
          }
          for (Throwable suppressed : cause1.getSuppressed()) {
            pBuffer.append(this.lineSeparator + "Suppressed: ");
            pBuffer.append(suppressed.toString() + "\t|");
          }
        }
      }
    }
    pBuffer.append(this.lineSeparator);
  }

 //Simple getters and setters:
  /**
   * <p>Getter for lineSeparator.</p>
   * @return String
   **/
  public final String getLineSeparator() {
    return this.lineSeparator;
  }

  /**
   * <p>Setter for lineSeparator.</p>
   * @param pLineSeparator reference
   **/
  public final void setLineSeparator(final String pLineSeparator) {
    this.lineSeparator = pLineSeparator;
  }


 /**
   * <p>Getter for dateFormat.</p>
   * @return DateFormat
   **/
  public final DateFormat getDateFormat() {
    return this.dateFormat;
  }

  /**
   * <p>Setter for dateFormat.</p>
   * @param pDateFormat reference
   **/
  public final void setDateFormat(final DateFormat pDateFormat) {
    this.dateFormat = pDateFormat;
  }
  /**
   * <p>Getter for isShowDebugMessageMap.</p>
   * @return Map<Class<?>, Boolean>
   **/
  public final Map<Class<?>, Boolean> getIsShowDebugMessageMap() {
    return this.isShowDebugMessageMap;
  }

  /**
   * <p>Setter for isShowDebugMessageMap.</p>
   * @param pIsShowDebugMessageMap reference
   **/
  public final void setIsShowDebugMessageMap(
    final Map<Class<?>, Boolean> pIsShowDebugMessageMap) {
    this.isShowDebugMessageMap = pIsShowDebugMessageMap;
  }
}
