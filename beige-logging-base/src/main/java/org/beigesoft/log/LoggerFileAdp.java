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

import java.util.Date;
import java.util.Map;

/**
 * <p>Simple logger to file.
 * It's used by programs that not respect "check is debug", i.e. didn't this:
   * <pre>
   *  if (this.logger.getIsShowDebugMessagesFor(this.getClass())) {
   *    // make some hard job:
   *    String msg = " data for " + formatDate(doc.dt) + " x1, x2"
   *      + Math.round(x1, this.settings.getRoundingMode()) .....
   *    // report message:
   *    this.logger.debug(this.getClass(), msg);
   *  }
   * </pre>
 * </p>
 *
 * @author Yury Demidenko
 */
public class LoggerFileAdp extends ALoggerFile {

  /**
   * <p>Make debug log.</p>
   * @param pAddParam additional param
   * @param pClazz of debudgged bean
   * @param pMsg message
   **/
  @Override
  public final synchronized void debug(final Map<String, Object> pAddParam,
    final Class<?> pClazz, final String pMsg) {
    if (getIsShowDebugMessages()) {
      makeRecord(getDateFormat().format(new Date()) + " DEBUG "
      + pClazz.getSimpleName() + " - " + pMsg + getLineSeparator());
    }
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
    if (getIsShowDebugMessages()) {
      StringBuffer sb = new StringBuffer();
      sb.append(getDateFormat().format(new Date()) + " DEBUG "
        + pClazz.getSimpleName() + " - " + pMsg);
      exceptionToString(sb, pThrown);
      makeRecord(sb.toString());
    }
  }
}
