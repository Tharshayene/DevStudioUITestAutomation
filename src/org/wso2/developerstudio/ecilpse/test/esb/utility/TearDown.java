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

package org.wso2.developerstudio.ecilpse.test.esb.utility;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.wso2.developerstudio.eclipse.test.esb.properties.Properties;

/**
 * @author nila
 * 
 */
public class TearDown {
	/**
	 * @param bot
	 * @return
	 */
	public static boolean projectExplorerCleanUpAfterClass(SWTWorkbenchBot bot) {

		bot.saveAllEditors();
		SWTBotTreeItem[] swtBotTreeItems = bot
				.viewByTitle(Properties.VIEW_PROJECT_EXPLORER).bot().tree()
				.getAllItems();
		for (SWTBotTreeItem tmpSwtBotTreeItem : swtBotTreeItems) {
			tmpSwtBotTreeItem.contextMenu(
					Properties.CONTEXTMENU_ITEM_CLOSE_PROJECT).click();

			tmpSwtBotTreeItem.contextMenu(Properties.CONTEXTMENU_ITEM_DELETE)
					.click();
			bot.shell(Properties.SHELL_DELETE_RESOURCES).activate();
			bot.activeShell().bot().checkBox().click();
			bot.activeShell().bot().button(Properties.BUTTON_OK).click();
			bot.closeAllShells();

		}

		bot.resetWorkbench();
		bot = null;
		return true;
	}

}
