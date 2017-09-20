package org.beigesoft.log;

/*
 * Copyright (c) 2015-2017 Beigesoft ™
 *
 * Licensed under the GNU General Public License (GPL), Version 2.0
 * (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 */

import java.util.Date;
import java.util.Map;

/**
 * <p>Simple logger to console.
 * Useful for debug purposes.</p>
 *
 * @author Yury Demidenko
 */
public class LoggerSimple extends ALogger {

  /**
   * <p>Make debug log.</p>
   * @param pAddParam additional param
   * @param pClazz of debudgged bean
   * @param pMsg message
   **/
  @Override
  public final void debug(final Map<String, Object> pAddParam,
    final Class<?> pClazz, final String pMsg) {
    System.out.println(getDateFormat().format(new Date()) + " DEBUG "
      + pClazz.getSimpleName() + " - " + pMsg);
  }

  /**
   * <p>Make debug log.</p>
   * @param pAddParam additional param
   * @param pClazz of debudgged bean
   * @param pMsg message
   * @param pThrown thrown
   **/
  @Override
  public final void debug(final Map<String, Object> pAddParam,
    final Class<?> pClazz, final String pMsg, final Throwable pThrown) {
    StringBuffer sb = new StringBuffer();
    sb.append(getDateFormat().format(new Date()) + " DEBUG "
      + pClazz.getSimpleName() + " - " + pMsg + getLineSeparator());
    exceptionToString(sb, pThrown);
    System.out.println(sb.toString());
  }

  /**
   * <p>Make info log.</p>
   * @param pAddParam additional param
   * @param pClazz of bean
   * @param pMsg message
   **/
  @Override
  public final void info(final Map<String, Object> pAddParam,
    final Class<?> pClazz, final String pMsg) {
    System.out.println(getDateFormat().format(new Date()) + " INFO "
      + pClazz.getSimpleName() + " - " + pMsg);
  }

  /**
   * <p>Make error log.</p>
   * @param pAddParam additional param
   * @param pClazz of bean
   * @param pMsg message
   **/
  @Override
  public final void error(final Map<String, Object> pAddParam,
    final Class<?> pClazz, final String pMsg) {
    System.err.println(getDateFormat().format(new Date()) + " ERROR "
        + pClazz.getSimpleName() + " - " + pMsg);
  }

  /**
   * <p>Make error log.</p>
   * @param pAddParam additional param
   * @param pClazz of bean
   * @param pMsg message
   * @param pThrown thrown
   **/
  @Override
  public final void error(final Map<String, Object> pAddParam,
    final Class<?> pClazz, final String pMsg, final Throwable pThrown) {
    StringBuffer sb = new StringBuffer();
    sb.append(getDateFormat().format(new Date()) + " ERROR "
      + pClazz.getSimpleName() + " - " + pMsg + getLineSeparator());
    exceptionToString(sb, pThrown);
    System.out.println(sb.toString());
  }

  /**
   * <p>Make warn log.</p>
   * @param pAddParam additional param
   * @param pClazz of bean
   * @param pMsg message
   **/
  @Override
  public final void warn(final Map<String, Object> pAddParam,
    final Class<?> pClazz, final String pMsg) {
    System.out.println(getDateFormat().format(new Date()) + " WARN "
      + pClazz.getSimpleName() + " - " + pMsg);
  }

  /**
   * <p>Make warn log.</p>
   * @param pAddParam additional param
   * @param pClazz of bean
   * @param pMsg message
   * @param pThrown thrown
   **/
  @Override
  public final void warn(final Map<String, Object> pAddParam,
    final Class<?> pClazz, final String pMsg, final Throwable pThrown) {
    StringBuffer sb = new StringBuffer();
    sb.append(getDateFormat().format(new Date()) + " WARN "
      + pClazz.getSimpleName() + " - " + pMsg + getLineSeparator());
    exceptionToString(sb, pThrown);
    System.out.println(sb.toString());
  }
}
