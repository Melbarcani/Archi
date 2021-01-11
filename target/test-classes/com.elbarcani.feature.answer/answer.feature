Feature: Answer

  Scenario: When at least an answer is missing
    Given User 123
    And I have tickets :
      | ticketId | price     |
      | 1        | 110       |
      | 2        | 120       |
      | 3        | 130       |
      | 4        | 140       |
      | 5        | 150       |
      | 6        | 160       |

    When I answer to the form as :
      | ticketId | answer    |
      | 1        | keep      |
      | 2        | reimburse |
      | 3        | reimburse |
      | 4        | keep      |
      | 5        | keep      |
      | 6        | undefined |

    Then I see this message : "There is at missing answers in your form"

    #==========================================================================================================

  Scenario: When no answer is missing and sending an mail
      Given User 123
      And I have tickets :
        | ticketId | price     |
        | 1        | 110       |
        | 2        | 120       |
        | 3        | 130       |
        | 4        | 140       |
        | 5        | 150       |
        | 6        | 160       |

      When I answer to the form as :
        | ticketId | answer    |
        | 1        | keep      |
        | 2        | reimburse |
        | 3        | reimburse |
        | 4        | keep      |
        | 5        | keep      |
        | 6        | keep      |

      Then I see this message : "the answers are recorded"
      And an email is sent to the user 123