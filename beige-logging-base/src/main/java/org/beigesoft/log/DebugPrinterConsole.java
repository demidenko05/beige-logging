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

/**
 * <p>Implementation of debug printer (logger) that log messages
 * to debug logger itself, it print messages into System.out.</p>
 *
 * @author Yury Demidenko
 */
public class DebugPrinterConsole extends ADebugPrinter {

  /**
   * <p>Make debug log.</p>
   * @param pClazz of debudgged bean
   * @param pMsg message
   **/
  @Override
  public final void println(final Class<?> pClazz, final String pMsg) {
    System.out.println(pClazz.getSimpleName() + " DP> " + pMsg);
  }

  /**
   * <p>Make debug log.</p>
   * @param pClazz of debudgged bean
   * @param pExc exception
   **/
  @Override
  public final void println(final Class<?> pClazz, final Throwable pExc) {
    StringBuffer sb = new StringBuffer();
    sb.append(pClazz.getSimpleName() + " DP> ");
    exceptionToString(sb, pExc);
    System.out.println(sb.toString());
  }
}
