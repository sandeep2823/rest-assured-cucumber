@tag
Feature: Delete Post
	Test the delete operation 

  @smoke
  Scenario: Verify DELETE operation after POST
    Given I ensure to perform POST operation for "/posts" with body as
    | id | title						 | author 							|
    | 6  | API Testing again | Sandeep Singh Jassal |
    And I perform DELETE operation for "/posts/{postid}/"
    | postid |
    | 6			 |
    And I perform the GET operation with path parameter for "/posts/{postid}"
    | postid |
    | 6			 |
    Then I should not be able to see the body with title as "API Testing again"
