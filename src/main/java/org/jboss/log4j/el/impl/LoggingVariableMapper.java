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
 * @author <a href="cdewolf@redhat.com">Carlo de Wolf</a>
 */
public class LoggingVariableMapper extends VariableMapper
{
   private ELResolver resolver = new BeanELResolver(true);

   private LoggingEventContext context;

   private ExpressionFactory expressionFactory;
   private LoggingEvent event;

   public LoggingVariableMapper(LoggingEventContext context)
   {
      this.context = context;

      this.expressionFactory = context.getExpressionFactory();
      this.event = context.getLoggingEvent();
   }

   @Override
   public ValueExpression resolveVariable(String variable)
   {
      Class<?> type = resolver.getType(context, event, variable);
      Object value = resolver.getValue(context, event, variable);
      return expressionFactory.createValueExpression(value, type);
   }

   @Override
   public ValueExpression setVariable(String variable, ValueExpression expression)
   {
      throw new UnsupportedOperationException("logging variables are considered read/only");
   }
}
