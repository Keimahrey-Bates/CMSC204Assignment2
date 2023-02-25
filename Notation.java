public class Notation
{
	private static MyQueue<String> queue;
	private static MyStack<String> stack;
	static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException
	{
		queue = new MyQueue<String>(infix.length());
		stack = new MyStack<String>(infix.length());
		for(int i = 0; i < infix.length(); i++)
		{
			if(String.valueOf(infix.charAt(i)) == " ")
			{
				continue;
			}
			if(Character.isDigit(infix.charAt(i)) == true)
			{
				queue.enqueue(String.valueOf(infix.charAt(i)));
				continue;
			}
			if(infix.charAt(i) == '(')
			{
				stack.push(String.valueOf(infix.charAt(i)));
				continue;
			}
			if(infix.charAt(i) == '*' || infix.charAt(i) == '/')
			{
				if(stack.isEmpty() == true)
				{
					stack.push(String.valueOf(infix.charAt(i)));
					continue;
				}
				if(stack.top() == "*" || stack.top() == "/")
				{
					queue.enqueue(stack.pop());
				}
				stack.push(String.valueOf(infix.charAt(i)));
				continue;
				
			}
			if(infix.charAt(i) == '+' || infix.charAt(i) == '-')
			{
				if(stack.isEmpty() == true)
				{
					stack.push(String.valueOf(infix.charAt(i)));
					continue;
				}
				if(stack.top() == "*" || stack.top() == "/")
				{
					queue.enqueue(stack.pop());
				}
				else if(stack.top() == "+" || stack.top() == "-")
				{
					queue.enqueue(stack.pop());
				}
				stack.push(String.valueOf(infix.charAt(i)));
				continue;
			}
			if(infix.charAt(i) == ')')
			{
				for(int j = 0; j < stack.size(); j++)
				{
					if(stack.top().equals("("))
					{
						stack.pop();
						break;
					}
					queue.enqueue(stack.pop());
					if(stack.isEmpty() == true)
					{
						throw new InvalidNotationFormatException();
					}
				}
				
				continue;
			}
			while(stack.size() != 0)
			{
				queue.enqueue(stack.pop());
			}
			
		}
		return queue.toString();
	}
	static String convertPostFixToInfix(String postfix) throws InvalidNotationFormatException
	{
		stack = new MyStack<String>(postfix.length());
		
		String stackStr = "", temp;
		String stackOp;
		for(int i = 0; i < postfix.length(); i++)
		{
			if(String.valueOf(postfix.charAt(i)) == " ")
			{
				continue;
			}
			if(Character.isDigit(postfix.charAt(i)) == true)
			{
				stack.push(String.valueOf(postfix.charAt(i)));
				continue;
			}
			if(postfix.charAt(i) == '+' || postfix.charAt(i) == '-' || postfix.charAt(i) == '*' || postfix.charAt(i) == '/')
			{
				if(stack.size() < 2)
				{
					throw new InvalidNotationFormatException();
				}
				stackOp = String.valueOf(postfix.charAt(i));
				stackStr += "(";
				temp = stack.pop();
				stackStr += stack.pop();
				stackStr += stackOp;
				stackStr += temp;
				stackStr += ")";
				stack.push(stackStr);
				stackStr = "";
			}
		}
		if(stack.size() > 1)
		{
			throw new InvalidNotationFormatException();
		}
		return stack.toString();
	}
	static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException
	{
		stack = new MyStack<String>(postfixExpr.length());
		double postVal = 0;
		double temp;
		String valString = "";
		for(int i = 0; i < postfixExpr.length(); i++)
		{
			if(String.valueOf(postfixExpr.charAt(i)) == " ")
			{
				continue;
			}
			if(Character.isDigit(postfixExpr.charAt(i)) == true || postfixExpr.charAt(i) == '(')
			{
				stack.push(String.valueOf(postfixExpr.charAt(i)));
				continue;
			}
			if(postfixExpr.charAt(i) == '+' || postfixExpr.charAt(i) == '-' || postfixExpr.charAt(i) == '*' || postfixExpr.charAt(i) == '/')
			{
				if(stack.size() < 2)
				{
					throw new InvalidNotationFormatException();
				}
				temp = Double.parseDouble(stack.pop());
				if(postfixExpr.charAt(i) == '+')
				{
					postVal = Double.parseDouble(stack.pop()) + temp;
				}
				if(postfixExpr.charAt(i) == '-')
				{
					postVal = Double.parseDouble(stack.pop()) - temp;
				}
				if(postfixExpr.charAt(i) == '*')
				{
					postVal = Double.parseDouble(stack.pop()) * temp;
				}
				if(postfixExpr.charAt(i) == '/')
				{
					postVal = Double.parseDouble(stack.pop()) / temp;
				}
				stack.push(Double.toString(postVal));
			}
		}
		if(stack.size() > 1)
		{
			throw new InvalidNotationFormatException();
		}
		return Double.parseDouble(stack.top());
	}
}