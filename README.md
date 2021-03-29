# conduit-testing

Using tools: Java, TestNG, Gradle, Selenium

Test case:

Preconditions:
   - using API get 2 popular tags
   
Steps:
   - login
   - create article with popular tag
   - go to home page
   - click on popular added tag 
     - should see created article
   - click on 2nd popular tag (not added to article)
     - shouldn't see article on the list
