/*Copyright (c) 2005-2010, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *WSO2 Inc. licenses this file to you under the Apache License,
 *Version 2.0 (the "License"); you may not use this file except
 *in compliance with the License.
 *You may obtain a copy of the License at
 *
 *http://www.apache.org/licenses/LICENSE-2.0
 *
 *Unless required by applicable law or agreed to in writing,
 *software distributed under the License is distributed on an
 *"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *KIND, either express or implied.  See the License for the
 *specific language governing permissions and limitations
 *under the License.
 */

package org.wso2.developerstudio.eclipse.test.esb.utility;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.wso2.developerstudio.eclipse.test.esb.properties.Properties;
import org.wso2.developerstudio.eclipse.test.esb.properties.PropertiesESB;

/**
 * @author nila
 * 
 */

public class EndPointCreation {

	public static final String IMAGE_HYPER_LINK = PropertiesESB.IMAGE_HYPER_LINK_ENDPOINT;
	public static String SHELL_CREATE_NEW_ENDPOINT = Properties.SHELL_CREATE_NEW_ENDPOINT;
	private static String TEXT_WITH_LABEL_ENDPOINT_NAME = PropertiesESB.TEXT_WITH_LABEL_ENDPOINT;
	private static String IMAGE_HYPER_LINK_CREATE_NEW_PROJECT = PropertiesESB.IMAGE_HYPER_LINK_CREATE_NEW_PROJECT;
	private static String ENDPOINT_NAME = PropertiesESB.ARTIFACT_ENDPOINT_NAME;

	/**
	 * @param bot
	 */
	public static void handleCreationWizard(SWTWorkbenchBot bot) {

		bot.activeShell().bot().button(Properties.BUTTON_NEXT).click();

		bot.textWithLabel(TEXT_WITH_LABEL_ENDPOINT_NAME).setText(ENDPOINT_NAME);
		bot.activeShell().bot().link()
				.click(IMAGE_HYPER_LINK_CREATE_NEW_PROJECT);

		SWTBotShell shell = bot.shell(Properties.SHELL_EMPTY_STRING);
		shell.activate();
		bot.button(Properties.BUTTON_NEXT).click();
		bot.textWithLabel(Properties.TEXT_WITH_LABEL_PROJECT_NAME).setText(
				PropertiesESB.ARTIFACT_MY_ESB_CONFIG_PROJECT_FOR_ENDPOINT_TEST);
		bot.button(Properties.BUTTON_FINISH).click();
		bot.textWithLabel(Properties.TEXT_WITH_LABEL_Address).setText(
				PropertiesESB.ARTIFACT_ENDPOINT_URL);
		bot.button(Properties.BUTTON_FINISH).click();

	}

	/**
	 * @param bot
	 * @return
	 */
	public static boolean checkCreatedProject(SWTWorkbenchBot bot) {
		bot.saveAllEditors();
		if (bot.activePerspective().getLabel()
				.contentEquals("WSO2 ESB Graphical")
				&& bot.activeEditor().getTitle()
						.contentEquals(ENDPOINT_NAME + ".xml")) {
			return true;
		}

		return false;
	}

}
