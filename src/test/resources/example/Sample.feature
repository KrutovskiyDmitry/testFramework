@TC-1

Feature: Example

  Scenario Outline: Пример сценария
    Given Открываю страницу https://the-internet.herokuapp.com/inputs
    Then Проверяю что отображается поле ввода
    When Ввожу в поле ввода <input>
    Then Проверяю что в поле ввода число больше <output>
    Examples:
      | input | output |
      | 10    | 5      |
      | 12    | 20     |
