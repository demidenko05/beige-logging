package org.beigesoft.android.log;

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

import android.util.Log;

import org.beigesoft.log.ALogger;

/**
 * <p>Android Logger adapter.</p>
 *
 * @author Yury Demidenko
 */
public class Logger extends ALogger {

  /**
   * <p>Make debug log.</p>
   * @param pClazz of debudgged bean
   * @param pMsg message
   **/
  @Override
  public final void debug(final Map<String, Object> pAddParam,
    final Class<?> pClazz, final String pMsg) {
    Log.d(pClazz.getSimpleName(), pMsg);
  }

  /**
   * <p>Make debug log.</p>
   * @param pClazz of debudgged bean
   * @param pMsg message
   * @param pThrown thrown
   **/
  @Override
  public final void debug(final Map<String, Object> pAddParam,
    final Class<?> pClazz, final String pMsg,
    final Throwable pThrown) {
    StringBuffer sb = new StringBuffer();
    sb.append(pMsg);
    exceptionToString(sb, pThrown);
    Log.d(pClazz.getSimpleName(), sb.toString());
  }

  /**
   * <p>Make info log.</p>
   * @param pClazz of bean
   * @param pMsg message
   **/
  @Override
  public final void info(final Map<String, Object> pAddParam,
    final Class<?> pClazz, final String pMsg) {
    Log.i(pClazz.getSimpleName(), pMsg);
  }

  /**
   * <p>Make error log.</p>
   * @param pClazz of bean
   * @param pMsg message
   **/
  @Override
  public final void error(final Map<String, Object> pAddParam,
    final Class<?> pClazz, final String pMsg) {
    Log.e(pClazz.getSimpleName(), pMsg);
  }

  /**
   * <p>Make error log.</p>
   * @param pClazz of bean
   * @param pMsg message
   * @param pThrown thrown
   **/
  @Override
  public final void error(final Map<String, Object> pAddParam,
    final Class<?> pClazz, final String pMsg, final Throwable pThrown) {
    StringBuffer sb = new StringBuffer();
    sb.append(pMsg);
    exceptionToString(sb, pThrown);
    Log.e(pClazz.getSimpleName(), sb.toString());
  }

  /**
   * <p>Make warn log.</p>
   * @param pClazz of bean
   * @param pMsg message
   **/
  @Override
  public final void warn(final Map<String, Object> pAddParam,
    final Class<?> pClazz, final String pMsg) {
    Log.w(pClazz.getSimpleName(), pMsg);
  }

  /**
   * <p>Make warn log.</p>
   * @param pClazz of bean
   * @param pMsg message
   * @param pThrown thrown
   **/
  @Override
  public final void warn(final Map<String, Object> pAddParam,
    final Class<?> pClazz, final String pMsg, final Throwable pThrown) {
    StringBuffer sb = new StringBuffer();
    sb.append(pMsg);
    exceptionToString(sb, pThrown);
    Log.w(pClazz.getSimpleName(), sb.toString());
  }
}
