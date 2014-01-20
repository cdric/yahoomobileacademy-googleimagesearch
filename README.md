Google Image Searcher
=====================

*Yahoo, Introduction to Android - Assignment Week 2: Google Image Searcher*

User stories implemented
------------------------
 - User can enter a search query that will display a grid of images from Google Images.
 - User can modify advanced search options such as:
    - Size (small, medium, large, extra-large)
    - Color filter (black, blue, brown, gray, green, etc...)
    - Type (faces, photo, clip art, line art)
    - Site (yahoo.com)
 - User can load more image using a "Load more image" button located in the footer of the GridView

Additional work done
--------------------
 - User settings are persisted through the life of the application so the user doesnt have to re-enter the filter he/she previously selected when returning on the Activity
 - The "Load more image" button automatically disable itself when no more results are available
 - The text within the spinner is align to the right of the view
 - Created a SearchFilters bean to easily manage and pass search filters between activities
 - Disable search button when the query isn't valid
 - Write unit tests to validate utility methods

Key learnings
-------------
 - Learn how to use RelativeLayout to build more complex Activity pages
 - Learn how to load default value of a Spinner through XML Resources
 - Learn how to style the text within a Spinner view.
