Feature: Displays orders

  Scenario: Never answered
    Given The user 123
    And I have those tickets :
      | ticketId | price     |
      | 1        | 110       |
      | 2        | 120       |
      | 3        | 130       |
      | 4        | 140       |
      | 5        | 150       |
      | 6        | 160       |

    And I never filled the form

    When I display the form

    Then I should see :
      | ticketId | price     | userId | answer    |
      | 1        | 110       | 123    | undefined |
      | 2        | 120       | 123    | undefined |
      | 3        | 130       | 123    | undefined |
      | 4        | 140       | 123    | undefined |
      | 5        | 150       | 123    | undefined |
      | 6        | 160       | 123    | undefined |

    #==========================================================================================================

  Scenario: after answering once
    Given The user 123
    And I have those tickets :

      | ticketId | price     |
      | 1        | 110       |
      | 2        | 120       |
      | 3        | 130       |
      | 4        | 140       |
      | 5        | 150       |
      | 6        | 160       |

    And I already filled the form once
      | ticketId | price     | userId | answer    |
      | 1        | 110       | 123    | keep      |
      | 2        | 120       | 123    | reimburse |
      | 3        | 130       | 123    | keep      |
      | 4        | 140       | 123    | keep      |
      | 5        | 150       | 123    | reimburse |
      | 6        | 160       | 123    | reimburse |
    When I display the form

    Then I should see :
      | ticketId | price     | userId | answer    |
      | 1        | 110       | 123    | keep      |
      | 2        | 120       | 123    | reimburse |
      | 3        | 130       | 123    | keep      |
      | 4        | 140       | 123    | keep      |
      | 5        | 150       | 123    | reimburse |
      | 6        | 160       | 123    | reimburse |


