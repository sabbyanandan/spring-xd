/*
 * Copyright 2013-2014 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.springframework.xd.integration.reactor.config;

import org.w3c.dom.Element;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.util.StringUtils;

/**
 * Namespace parser helper for creating {@link BeanDefinitionBuilder} instances that have a reference to a Reactor
 * {@link reactor.core.Environment} as their first constructor parameter.
 *
 * @author Jon Brisbin
 */
public abstract class ReactorNamespaceUtils {

	public static final String REACTOR_ENV_BEAN_NAME = "reactorEnv";

	protected ReactorNamespaceUtils() {
	}

	/**
	 * Create a {@link BeanDefinitionBuilder} that checks for an {@code env} attribute and uses that as the
	 * {@link reactor.core.Environment} bean reference. If not set, then it defaults to {@code reactorEnv},
     * which is the name of the global Reactor {@link reactor.core.Environment}.
	 *
	 * @param componentType the {@code Class} of the bean that the definition is being created for
	 * @param element element to query for an {@code env} attribute
	 *
	 * @return BeanDefinitionBuilder
	 */
	public static BeanDefinitionBuilder createBeanDefinitionBuilder(Class<?> componentType, Element element) {
		String envRef = element.getAttribute("env");
		if (!StringUtils.hasText(envRef)) {
			envRef = REACTOR_ENV_BEAN_NAME;
		}

		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(componentType);
		builder.addConstructorArgReference(envRef);

		return builder;
	}

}
