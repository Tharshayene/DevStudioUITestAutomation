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

package org.wso2.developerstudio.eclipse.test.esb;

import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.allOf;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.widgetOfType;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.hamcrest.Matcher;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wso2.developerstudio.eclipse.test.esb.properties.Properties;
import org.wso2.developerstudio.eclipse.test.esb.properties.PropertiesESB;
import org.wso2.developerstudio.eclipse.test.esb.utility.DashBoardCreation;
import org.wso2.developerstudio.eclipse.test.esb.utility.ESBConfigProjectCreation;
import org.wso2.developerstudio.eclipse.test.esb.utility.SetUp;
import org.wso2.developerstudio.eclipse.test.esb.utility.TearDown;

/**
 * @author nila
 * 
 */

@RunWith(SWTBotJunit4ClassRunner.class)
public class ESBConfigProjectTestCase {

	private static SWTWorkbenchBot bot;

	@BeforeClass
	public static void beforeClass() throws Exception {
		bot = new SWTWorkbenchBot();

		assertTrue(SetUp.setSWTBotPreferences());
		assertTrue(Properties.ERROR_MSG_RUNNING_ON_UI_THREAD,
				SetUp.isUIThread(bot));
		assertTrue(Properties.ERROR_MSG_ERROR_WHILE_CLOSING_THE_WELCOME_VIEW,
				SetUp.closeWelcomeView(bot));
		assertTrue(Properties.ERROR_MSG_JAVA_EE_PERPECTIVE_WASNT_SET_PROPERLY,
				SetUp.setJavaEEPerspective(bot));
	}

	@Test
	public void testESBConfigProjectCreation() throws Exception {

		assertTrue(Properties.ERROR_OPEN_DASHBOARD,
				DashBoardCreation.openDashBoard(bot));

		@SuppressWarnings({ "rawtypes", "unchecked" })
		Matcher matcherImageHyperLink = allOf(
				widgetOfType(ImageHyperlink.class),
				withText(ESBConfigProjectCreation.IMAGE_HYPER_LINK));

		assertTrue(Properties.ERROR_OPEN_CREATION_WIZARD,
				DashBoardCreation.openWizard(matcherImageHyperLink, bot));

		SWTBotShell shell = bot.shell(Properties.SHELL_EMPTY_STRING);
		shell.activate();

		ESBConfigProjectCreation.handleCreationWizard(bot);

		SWTBotTreeItem swtBotTreeItem = ESBConfigProjectCreation
				.checkCreatedProject(bot);

		

		int count = ESBConfigProjectCreation.getArtifactCount(bot,
				swtBotTreeItem);

		assertEquals(
				PropertiesESB.ERROR_MSG_ESB_CONFIG_PROJECT_MISSING_ITS_ARTIFACT,
				count, PropertiesESB.ARITFACT_COUNT_4);
	}

	/**
	 * reverting the changes done to the workBench. Note that 'Welcome' view
	 * isn't opened again.
	 * 
	 * @throws IOException
	 */
	@AfterClass
	public static void cleanUp() throws IOException {
		assertTrue(Properties.ERROR_MSG_ERROR_WHILE_CLEANING_UP,
				TearDown.projectExplorerCleanUpAfterClass(bot));
	}

}
