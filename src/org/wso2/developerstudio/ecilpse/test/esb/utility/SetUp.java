/*
 *Copyright (c) 2005-2010, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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

import java.util.List;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.wso2.developerstudio.eclipse.test.esb.properties.Properties;

/**
 * @author nila
 * 
 */
public class SetUp {
	/**
	 * Use the Properties file to set Preferences: KEYBOARD_STRATEGY,
	 * KEYBOARD_LAYOUT, TIMEOUT
	 * 
	 * @return isSWTBotPreferencesSet
	 */
	public static boolean setSWTBotPreferences() {
		SWTBotPreferences.KEYBOARD_STRATEGY = Properties.KEYBOARD_STRATEGY_ORG_ECLIPSE_SWTBOT_SWT_FINDER_KEYBOARD_SWT_KEYBOARD_STRATEGY;
		SWTBotPreferences.KEYBOARD_LAYOUT = Properties.KEYBOARD_LAYOUT_EN_US;
		SWTBotPreferences.TIMEOUT = Properties.TIME_OUT;
		return true;
	}

	/**
	 * @param bot
	 * @return isUIThread
	 */
	public static boolean isUIThread(SWTWorkbenchBot bot) {
		if (bot.getDisplay().getThread() != Thread.currentThread()) {
			return true;
		}
		return false;
	}

	/**
	 * @param bot
	 * @return isWelcomeClosed
	 */
	public static boolean closeWelcomeView(SWTWorkbenchBot bot) {
		List<SWTBotView> swtBotViews = bot.views();
		for (SWTBotView tmpSwtBotView : swtBotViews) {
			if (tmpSwtBotView.getTitle().contentEquals(Properties.VIEW_WELCOME)) {
				tmpSwtBotView.close();

			}
		}
		return true;
	}

	/**
	 * Sets Java EE perspective, else the default Java perspective could cause
	 * errors due to some missing elements
	 * 
	 * @param bot
	 *            SWTWorkbenchBot is passed to set the perspective
	 * @return isSet the boolean value is returned if set to Java EE
	 */
	public static boolean setJavaEEPerspective(SWTWorkbenchBot bot) {

		if (bot.perspectiveByLabel(Properties.PERSPECTIVE_JAVA_EE).isActive()) {
			return true;
		} else {

			bot.perspectiveByLabel(Properties.PERSPECTIVE_JAVA_EE).activate();
			return true;
		}

	}

}
