# Appium Autiomation Project
sudo npm install -g appium
To install appium globally

appium : to start appium server

basic capabilities for Android Inspector
{
"appium:app": "/Users/gauravmutreja/Projects/IdeaProjects/AppiumProject/src/test/resources/General-Store.apk",
"appium:deviceName": "GauravEmulator",
"platformName": "android",
"appium:automationName": "UiAutomator2"
}

appium driver list
To list all available drivers

appium driver install uiautomator2 / xcuitest
to install above driver

Appium supports following locators:
Xpath, id, accessibilityID, className, androidUIAutomator

Use AppiumBy. - accessibilityID, androidUIAutomator
use By. - for rest i.e. Xpath, id, className

To check if phone/ emulator is connected:
adb devices

Finding out package and activity name via adb for appium automation
adb shell dumpsys window displays | grep -E mCurrentFocus