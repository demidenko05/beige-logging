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

/**
 * <p>Abstraction of debug printer (logger) that log messages
 * to debug logger itself, usually it print messages to System.out for
 * standard Java or Log.i for Android.</p>
 *
 * @author Yury Demidenko
 */
public interface IDebugPrinter {

  /**
   * <p>Make debug log.</p>
   * @param pClazz of debudgged bean
   * @param pMsg message
   **/
  void println(Class<?> pClazz, String pMsg);

  /**
   * <p>Make debug log.</p>
   * @param pClazz of debudgged bean
   * @param pExc exception
   **/
  void println(Class<?> pClazz, Throwable pExc);
}
