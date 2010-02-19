/*
 * JBoss, Home of Professional Open Source
 * Copyright (c) 2010, JBoss Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.log4j.el.impl;

import org.apache.log4j.spi.LoggingEvent;

import javax.el.*;

/**
 * A per logging even context.
 * 
 * @author <a href="cdewolf@redhat.com">Carlo de Wolf</a>
 */
public class LoggingEventContext extends ELContext
{
   private AppenderContext parent;
   private LoggingEvent event;
   
   private LoggingVariableMapper variableMapper;

   public LoggingEventContext(AppenderContext parent, LoggingEvent event)
   {
      this.parent = parent;
      this.event = event;

      this.variableMapper = new LoggingVariableMapper(this);
   }

   @Override
   public ELResolver getELResolver()
   {
      return parent.getELResolver();
   }

   protected ExpressionFactory getExpressionFactory()
   {
      return parent.getExpressionFactory();
   }

   @Override
   public FunctionMapper getFunctionMapper()
   {
      return parent.getFunctionMapper();
   }

   protected LoggingEvent getLoggingEvent()
   {
      return event;
   }
   
   @Override
   public VariableMapper getVariableMapper()
   {
      return variableMapper;
   }
}
