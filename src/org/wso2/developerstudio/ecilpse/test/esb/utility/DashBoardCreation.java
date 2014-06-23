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
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.forms.finder.widgets.SWTBotImageHyperlink;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.hamcrest.Matcher;
import org.wso2.developerstudio.eclipse.test.esb.properties.Properties;

/**
 * @author nila
 * 
 */
public class DashBoardCreation {
	/**
	 * @param bot
	 * @return
	 */
	public static boolean openDashBoard(SWTWorkbenchBot bot) {
		bot.menu(Properties.MENU_ITEM_DEVELOPER_STUDIO)
				.menu(Properties.MENU_ITEM_OPEN_DASHBOARD).click();
		bot.cTabItem(Properties.CTAB_ITEM_DEVELOPER_STUDIO_DASHBOARD)
				.activate();
		SWTBotEditor dashBoardEditor = bot.activeEditor();
		dashBoardEditor.setFocus();

		if (dashBoardEditor.getTitle().contentEquals(Properties.VIEW_DASHBOARD)) {
			return true;
		}

		return false;
	}

	/**
	 * @param matcherImageHyperLink
	 * @param bot
	 * @return
	 */
	public static boolean openWizard(Matcher matcherImageHyperLink,
			SWTWorkbenchBot bot) {
		@SuppressWarnings("unchecked")
		ImageHyperlink link = (ImageHyperlink) bot
				.widget(matcherImageHyperLink);
		link.getClass();

		SWTBotImageHyperlink swtbotImageHyperLink = new SWTBotImageHyperlink(
				link);
		swtbotImageHyperLink.click();

		if (swtbotImageHyperLink.isEnabled()) {
			return true;

		}
		return false;
	}
}
