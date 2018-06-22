1. user can input only one triangle per program execution
   * it is impossible to know if we encountered the end of input without reading it
   * if we read too much, we need to store it
   * otherwise, complexity would be unnecessarily increased
2. value of each triangle node is a positive integer
   * as with `1`, for the sake of keeping it as simple as possible
   * all examples contain only positive integers
3. minimum possible value of a triangle node is `0`
   * implication of `2`
4. maximum possible value of a triangle node is `9'223'372'036'854'775'807`
   * upper limit of `long` data type, I think it should be enough
5. input consists of only digits (0-9), spaces and new line characters
   * again, to keep the application simple
6. first line of input contains a single number and a new line character
7. each line of input contains one number more than the previous line
8. any two numbers in input line are separated by one space character
9. there is no space character between a number and a new line character in any line of input
10. last line of input does not contain a new line character at the end
11. input contains no empty lines
12. if two or more minimal paths exist in a triangle, either of them is a valid result
    * not required by the task, but code works