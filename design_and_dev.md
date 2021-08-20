### Design and Development approach:

- There are two services we need to develop. 
  - The first is the evaluation of a mathematical bodmas expression.
  - The second is a service which returns the most used operator for a user since its lifetime

#### Assumptions
- Only +, -, / and * operations are supported, and the result returned is always integer.
- Unary operator expression is not supported
- The algorithm for converting from infix to postfix will correctly parse all valid infix expressions, but doesn't reject all invalid expression. It however rejects expression which have characters other than digits, operator, and parentheses.


#### Evaluation of BODMAS mathematical expression.
- The approach followed
  - Convert the given infix expression to postfix expression. So something like 1 + (3 - 2)translates to "1 3 2 - +". The algorithm used for this process is [Shunting-yard Algorithm](https://en.wikipedia.org/wiki/Shunting-yard_algorithm)
  - It can handle expressions with multiple digits.
  - Next we evaluate the expression using stack.
  - During the calculation of the given expression we also save the user in the database and the number of operators used in their expression.

### Getting the most used operator for a user
- Since we have saved the operators used by a user in the database. We query the database and find the operator whose count is maximum and return that operator.


### Additional improvements that can be done
- To make the service correctly result in evaluating expression with unary operator also
- To make the service support more operators such as exponentiation.
- To make the service support double numerals and evaluate them correctly.
- We can introduce a cache so that for the second service if the user is not present in cache we get the result from DB and cache it.

  