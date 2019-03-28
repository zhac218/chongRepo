README:

A. Framework
   a. Src Folder
      Description: Contains Framework and Actual Library (nothing to do website - business application)
      Configuration, etc.

   b. Test Folder
      Description: Contains codes related to the Website (Remember the Milk application)
      Page Object (pages), Business User Cases (business workflow), and Actual Tests (tests).
      SmokeTest.java: contains all smoke test cases
      Other Tests contains empty; I used for my own tests and then deleted after.

Assumption: no other tasks exist previously.

To Do:
Still Many area to improve other than more test cases;
Business Work Flow:
- Reduce redundant codes
- Organize codes in fluent style.
Edge Cases - what if contains more than 1 task.
Framework - need log files;
Move assertion method from businessworkflow to tests

Design Pattern: Factory Design Pattern
- DriverManager: manage different kinds of drivers.

Test Cases: Ping_Test_Cases.xlsx


Current Automated Test Cases:
1. Authentication: a user can log in with valid credentials.
2. Create Task: a user can create a single new tasks.
3. Read Task: a user can read newly created task from the previous step.
4. Update Task: a user can update the task: assign task to due date: today
   and check if it contains in the Due Folder.
5. Delete Task: a user can delete a single task from the previous step.
