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
 * <p>Simple logger to file.
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
public class LoggerFile extends ALoggerFile {

  /**
   * <p>Make debug log.</p>
   * @param pAddParam additional param
   * @param pClazz of debudgged bean
   * @param pMsg message
   **/
  @Override
  public final synchronized void debug(final Map<String, Object> pAddParam,
    final Class<?> pClazz, final String pMsg) {
    makeRecord(getDateFormat().format(new Date()) + " DEBUG "
    + pClazz.getSimpleName() + " - " + pMsg + getLineSeparator());
  }

  /**
   * <p>Make debug log.</p>
   * @param pAddParam additional param
   * @param pClazz of debudgged bean
   * @param pMsg message
   * @param pThrown thrown
   **/
  @Override
  public final synchronized void debug(final Map<String, Object> pAddParam,
    final Class<?> pClazz, final String pMsg, final Throwable pThrown) {
    StringBuffer sb = new StringBuffer();
    sb.append(getDateFormat().format(new Date()) + " DEBUG "
      + pClazz.getSimpleName() + " - " + pMsg);
    exceptionToString(sb, pThrown);
    makeRecord(sb.toString());
  }
}
