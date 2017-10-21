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

import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * <p>Implementation of Logger adapter with java.util.logging.
 * This logger used in already preconfigured container e.g. Tomcat.</p>
 *
 * @author Yury Demidenko
 */
public class LoggerStandard extends ALogger {

  /**
   * <p>logger.</p>
   **/
  private Logger logger = Logger.getAnonymousLogger();

  /**
   * <p>Make debug log.</p>
   * @param pClazz of debudgged bean
   * @param pMsg message
   **/
  @Override
  public final void debug(final Map<String, Object> pAddParam,
    final Class<?> pClazz, final String pMsg) {
    logger.log(Level.INFO, pClazz.getSimpleName() + ": " + pMsg);
  }

  /**
   * <p>Make debug log.</p>
   * @param pClazz of debudgged bean
   * @param pMsg message
   * @param pThrown thrown
   **/
  @Override
  public final void debug(final Map<String, Object> pAddParam,
    final Class<?> pClazz, final String pMsg, final Throwable pThrown) {
    logger.log(Level.INFO, pClazz.getSimpleName() + ": " + pMsg, pThrown);
  }

  /**
   * <p>Make info log.</p>
   * @param pClazz of bean
   * @param pMsg message
   **/
  @Override
  public final void info(final Map<String, Object> pAddParam,
    final Class<?> pClazz, final String pMsg) {
    logger.log(Level.INFO, pClazz.getSimpleName() + ": " + pMsg);
  }

  /**
   * <p>Make error log.</p>
   * @param pClazz of bean
   * @param pMsg message
   **/
  @Override
  public final void error(final Map<String, Object> pAddParam,
    final Class<?> pClazz, final String pMsg) {
    logger.log(Level.SEVERE, pClazz.getSimpleName() + ": " + pMsg);
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
    logger.log(Level.SEVERE, pClazz.getSimpleName() + ": " + pMsg, pThrown);
  }

  /**
   * <p>Make warn log.</p>
   * @param pClazz of bean
   * @param pMsg message
   **/
  @Override
  public final void warn(final Map<String, Object> pAddParam,
    final Class<?> pClazz, final String pMsg) {
    logger.log(Level.WARNING, pClazz.getSimpleName() + ": " + pMsg);
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
    logger.log(Level.WARNING, pClazz.getSimpleName() + ": " + pMsg, pThrown);
  }

  //Simple getters and setters:
  /**
   * <p>Geter for logger.</p>
   * @return Logger
   **/
  public final Logger getLogger() {
    return this.logger;
  }

  /**
   * <p>Setter for logger.</p>
   * @param pLogger reference
   **/
  public final void setLogger(final Logger pLogger) {
    this.logger = pLogger;
  }
}
