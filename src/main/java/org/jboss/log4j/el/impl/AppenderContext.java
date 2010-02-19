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

import javax.el.*;

/**
 * A context which has the same lifecycle as an appender.
 * 
 * @author <a href="cdewolf@redhat.com">Carlo de Wolf</a>
 */
public class AppenderContext extends ELContext
{
   private ExpressionFactory expressionFactory;

   private ELResolver resolver = new BeanELResolver(true);
   private FunctionMapper functionMapper = new LoggingFunctionMapper();

   public AppenderContext(ExpressionFactory factory)
   {
      this.expressionFactory = factory;
   }

   @Override
   public ELResolver getELResolver()
   {
      // TODO: make configurable and use CompositeELResolver
      return resolver;
   }

   protected ExpressionFactory getExpressionFactory()
   {
      return expressionFactory;
   }

   @Override
   public FunctionMapper getFunctionMapper()
   {
      return functionMapper;
   }

   @Override
   public VariableMapper getVariableMapper()
   {
      throw new UnsupportedOperationException("variables must be managed per event");
   }
}
