Feature: Service user : answer

  Scenario: Get all answer for users
    Given answers recorded from 2 user 123 and 456
    And I have all this tickets :
      | id | userId | answer      | time                               |
      | 1  | 123    | keep        | 2020-12-21 12:00:00                |
      | 1  | 123    | reimburse   | 2020-12-22 12:00:00                |
      | 2  | 123    | keep        | 2020-12-21 12:00:00                |
      | 2  | 123    | reimburse   | 2020-12-22 12:00:00                |
      | 2  | 123    | keep        | 2020-12-23 12:00:00                |
      | 3  | 123    | reimburse   | 2020-12-21 12:00:00                |
      | 4  | 123    | keep        | 2020-12-21 12:00:00                |
      | 5  | 123    | keep        | 2020-12-21 12:00:00                |
      | 6  | 123    | keep        | 2020-12-21 12:00:00                |
      | 6  | 123    | reimburse   | 2020-12-23 12:00:00                |
      | 7  | 456    | keep        | 2020-12-21 12:00:00                |
      | 8  | 456    | keep        | 2020-12-21 12:00:00                |

    When I list their last answer
    Then I should see :
      | id | userId | answer      | time                               |
      | 1  | 123    | keep        | 2020-12-21 12:00:00                |
      | 1  | 123    | reimburse   | 2020-12-22 12:00:00                |
      | 2  | 123    | keep        | 2020-12-21 12:00:00                |
      | 2  | 123    | reimburse   | 2020-12-22 12:00:00                |
      | 2  | 123    | keep        | 2020-12-23 12:00:00                |
      | 3  | 123    | reimburse   | 2020-12-21 12:00:00                |
      | 4  | 123    | keep        | 2020-12-21 12:00:00                |
      | 5  | 123    | keep        | 2020-12-21 12:00:00                |
      | 6  | 123    | keep        | 2020-12-21 12:00:00                |
      | 6  | 123    | reimburse   | 2020-12-23 12:00:00                |
      | 7  | 456    | keep        | 2020-12-21 12:00:00                |
      | 8  | 456    | keep        | 2020-12-21 12:00:00                |

    #==========================================================================================================

  Scenario: Get all last answer for users
    Given answers recorded from 2 user 123 456
    And I have all this tickets :
      | id | userId | answer      | time                               |
      | 1  | 123    | keep        | 2020-12-21 12:00:00                |
      | 1  | 123    | reimburse   | 2020-12-22 12:00:00                |
      | 2  | 123    | keep        | 2020-12-21 12:00:00                |
      | 2  | 123    | reimburse   | 2020-12-22 12:00:00                |
      | 2  | 123    | keep        | 2020-12-23 12:00:00                |
      | 3  | 123    | reimburse   | 2020-12-21 12:00:00                |
      | 4  | 123    | keep        | 2020-12-21 12:00:00                |
      | 5  | 123    | keep        | 2020-12-21 12:00:00                |
      | 6  | 123    | keep        | 2020-12-21 12:00:00                |
      | 6  | 123    | reimburse   | 2020-12-23 12:00:00                |
      | 7  | 456    | keep        | 2020-12-21 12:00:00                |
      | 8  | 456    | keep        | 2020-12-21 12:00:00                |

    When I list their last answer
    Then I should see :
      | id | userId | answer      | time                               |
      | 1  | 123    | reimburse   | 2020-12-22 12:00:00                |
      | 2  | 123    | keep        | 2020-12-23 12:00:00                |
      | 3  | 123    | reimburse   | 2020-12-21 12:00:00                |
      | 4  | 123    | keep        | 2020-12-21 12:00:00                |
      | 5  | 123    | keep        | 2020-12-21 12:00:00                |
      | 6  | 123    | reimburse   | 2020-12-23 12:00:00                |
      | 7  | 456    | keep        | 2020-12-21 12:00:00                |
      | 8  | 456    | keep        | 2020-12-21 12:00:00                |

    #==========================================================================================================

    Scenario: delete choice
      Given answers recorded from 2 user 123 456
      And I have all this tickets :
        | id | userId | answer      | time                               |
        | 1  | 123    | keep        | 2020-12-21 12:00:00                |
        | 1  | 123    | reimburse   | 2020-12-22 12:00:00                |
        | 2  | 123    | keep        | 2020-12-21 12:00:00                |
        | 2  | 123    | reimburse   | 2020-12-22 12:00:00                |
        | 2  | 123    | keep        | 2020-12-23 12:00:00                |
        | 3  | 123    | reimburse   | 2020-12-21 12:00:00                |
        | 4  | 123    | keep        | 2020-12-21 12:00:00                |
        | 5  | 123    | keep        | 2020-12-21 12:00:00                |
        | 6  | 123    | keep        | 2020-12-21 12:00:00                |
        | 6  | 123    | reimburse   | 2020-12-23 12:00:00                |
        | 7  | 456    | keep        | 2020-12-21 12:00:00                |
        | 8  | 456    | keep        | 2020-12-21 12:00:00                |
      When I delete all choice
      Then all choice should be deleted
