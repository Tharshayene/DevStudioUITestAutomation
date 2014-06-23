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

import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.wso2.developerstudio.eclipse.test.esb.properties.Properties;
import org.wso2.developerstudio.eclipse.test.esb.properties.PropertiesESB;

/**
 * @author nila
 * 
 */

public class ESBConfigProjectCreation {

	public static final String IMAGE_HYPER_LINK = PropertiesESB.IMAGE_HYPER_LINK_ESB_CONFIG_PROJECT;

	/**
	 * @param bot
	 */
	public static void handleCreationWizard(SWTWorkbenchBot bot) {
		bot.button(Properties.BUTTON_NEXT).click();
		bot.textWithLabel(Properties.TEXT_WITH_LABEL_PROJECT_NAME).setText(
				PropertiesESB.ARTIFACT_MY_ESB_CONFIG_PROJECT_VIA_DASH_BOARD);
		bot.button(Properties.BUTTON_NEXT).click();
		bot.button(Properties.BUTTON_FINISH).click();

	}

	/**
	 * @param bot
	 * @return
	 */
	public static SWTBotTreeItem checkCreatedProject(SWTWorkbenchBot bot) {
		bot.saveAllEditors();
		SWTBotTreeItem swtBotTreeItem = bot
				.viewByTitle(Properties.VIEW_PROJECT_EXPLORER)
				.bot()
				.tree()
				.getTreeItem(
						PropertiesESB.ARTIFACT_MY_ESB_CONFIG_PROJECT_VIA_DASH_BOARD);
		assertTrue(PropertiesESB.ERROR_MSG_ESB_CONFIG_PROJECT_NOT_CREATED,
				swtBotTreeItem.isVisible());
		return swtBotTreeItem;
	}

	/**
	 * @param bot
	 * @param swtBotTreeItem
	 * @return
	 */
	public static int getArtifactCount(SWTWorkbenchBot bot,
			SWTBotTreeItem swtBotTreeItem) {

		swtBotTreeItem.select();
		java.util.List<String> swtBotTreeItemsStrings = bot
				.viewByTitle(Properties.VIEW_PROJECT_EXPLORER)
				.bot()
				.tree()
				.expandNode(
						PropertiesESB.ARTIFACT_MY_ESB_CONFIG_PROJECT_VIA_DASH_BOARD,
						PropertiesESB.TREE_ITEM_SRC,
						PropertiesESB.TREE_ITEM_MAIN,
						PropertiesESB.TREE_ITEM_SYNAPSE_CONFIG).select()
				.getNodes();

		int count = 0;
		String tmpString = null;
		Iterator<String> iterator = swtBotTreeItemsStrings.iterator();
		while (iterator.hasNext()) {
			tmpString = iterator.next();

			if (tmpString.contentEquals(PropertiesESB.TREE_ITEM_ENDPOINTS)
					|| tmpString
							.contentEquals(PropertiesESB.TREE_ITEM_LOCAL_ENTRIES)
					|| tmpString
							.contentEquals(PropertiesESB.TREE_ITEM_PROXY_SERVICES)
					|| tmpString
							.contentEquals(PropertiesESB.TREE_ITEM_SEQUENCES)) {
				count += 1;
			}

		}
		return count;
	}

}
