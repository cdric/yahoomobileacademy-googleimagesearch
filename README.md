yahoomobileacademy-googleimagesearch
====================================

Yahoo, Introduction to Android - Assignment Week 2: Google Image Searcher

This project support the following user stories
 - User can enter a search query that will display a grid of images from Google Images.
 - User can modify advanced search options such as:
    - Size (small, medium, large, extra-large)
    - Color filter (black, blue, brown, gray, green, etc...)
    - Type (faces, photo, clip art, line art)
    - Site (yahoo.com)
 - User can load more image using a "Load more image" button located in the footer of the GridView

Additional work done
 - User settings are persisted through the life of the application so the user doesnt have to re-enter the filter he/she previously selected when returning on the Activity
 - The "Load more image" button automatically disable itself when no more results are available
 - The text within the spinner is align to the right of the view
 - Created a SearchFilters bean to easily manage and pass search filters between activities
- Write unit tests to validate utility methods

Key learnings
 - RelativeLayout take sometime to handle :-)
 - Learn how to style the text within a Spinner view.
 
Open questions
 - Why do I have to override the toString() method of the ImageResult class in order for my debug statements to display each property of the bean rather than only displaying the reference to the object?
 - I have a classpath issue with my test method <code>UtilityClassTest.testBuildGoogleImageSearchQuery</code> that I was not able to resolve. 
 
<block>
java.lang.NoClassDefFoundError: android/net/Uri
	at com.yahoo.mobileacademy.googleimagesearch.helpers.UtilityClass.buildGoogleImageSearchQuery(UtilityClass.java:50)
	at com.yahoo.mobileacademy.googleimagesearch.helpers.UtilityClassTest.testBuildGoogleImageSearchQuery(UtilityClassTest.java:48)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
	at java.lang.reflect.Method.invoke(Method.java:597)
	at junit.framework.TestCase.runTest(TestCase.java:168)
	at junit.framework.TestCase.runBare(TestCase.java:134)
	at junit.framework.TestResult$1.protect(TestResult.java:110)
	at junit.framework.TestResult.runProtected(TestResult.java:128)
	at junit.framework.TestResult.run(TestResult.java:113)
	at junit.framework.TestCase.run(TestCase.java:124)
	at junit.framework.TestSuite.runTest(TestSuite.java:243)
	at junit.framework.TestSuite.run(TestSuite.java:238)
	at org.eclipse.jdt.internal.junit.runner.junit3.JUnit3TestReference.run(JUnit3TestReference.java:130)
	at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:467)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:683)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:390)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:197)
Caused by: java.lang.ClassNotFoundException: android.net.Uri
	at java.net.URLClassLoader$1.run(URLClassLoader.java:202)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.net.URLClassLoader.findClass(URLClassLoader.java:190)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:306)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:301)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:247)
	... 20 more
</block>

